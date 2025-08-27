package com.fieldsmanager.fields_manager_backend.service;

import com.fieldsmanager.fields_manager_backend.dto.PlayerResponse;
import com.fieldsmanager.fields_manager_backend.dto.TeamRequest;
import com.fieldsmanager.fields_manager_backend.dto.TeamResponse;
import com.fieldsmanager.fields_manager_backend.entity.Team;
import com.fieldsmanager.fields_manager_backend.entity.TeamPlayers;
import com.fieldsmanager.fields_manager_backend.entity.User;
import com.fieldsmanager.fields_manager_backend.repository.TeamPlayersRepository;
import com.fieldsmanager.fields_manager_backend.repository.TeamRepository;
import com.fieldsmanager.fields_manager_backend.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamPlayersRepository teamPlayersRepository;
    private final UserRepository userRepository;

    public TeamService(TeamRepository teamRepository,
                       TeamPlayersRepository teamPlayersRepository,
                       UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.teamPlayersRepository = teamPlayersRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (email == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No authenticated user");
        }
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));
    }

    public TeamResponse createTeam(TeamRequest req) {
        User me = getCurrentUser();
        Team team = new Team(req.getName());
        team = teamRepository.save(team);

        // create TeamPlayers entry: creator is leader
        TeamPlayers leader = new TeamPlayers(me, team, true);
        teamPlayersRepository.save(leader);

        TeamResponse resp = new TeamResponse(team.getId(), team.getName());
        resp.getPlayers().add(new PlayerResponse(me.getId(), me.getName(), true));
        return resp;
    }

    public List<TeamResponse> getMyTeams() {
        User me = getCurrentUser();
        List<TeamPlayers> memberships = teamPlayersRepository.findByUser(me);
        // for each membership, build a TeamResponseDTO populated with players
        List<Integer> teamIds = memberships.stream()
                .map(m -> m.getTeam().getId())
                .collect(Collectors.toList());

        List<Team> teams = teamRepository.findAllById(teamIds);
        List<TeamResponse> result = new ArrayList<>();
        for (Team t : teams) {
            TeamResponse dto = new TeamResponse(t.getId(), t.getName());
            List<TeamPlayers> players = teamPlayersRepository.findByTeam(t);
            for (TeamPlayers p : players) {
                User u = p.getUser();
                dto.getPlayers().add(new PlayerResponse(u.getId(), u.getName(), p.isLeader()));
            }
            result.add(dto);
        }
        return result;
    }

    public PlayerResponse joinTeam(Integer teamId) {
        User me = getCurrentUser();
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));

        if (teamPlayersRepository.existsByTeam_IdAndUser_Id(teamId, me.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already a member");
        }

        TeamPlayers tp = new TeamPlayers(me, team, false);
        teamPlayersRepository.save(tp);
        return new PlayerResponse(me.getId(), me.getName(), false);
    }

    public void leaveTeam(Integer teamId) {
        User me = getCurrentUser();
        if (!teamPlayersRepository.existsByTeam_IdAndUser_Id(teamId, me.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not a member");
        }
        teamPlayersRepository.deleteByTeam_IdAndUser_Id(teamId, me.getId());
    }

    public List<PlayerResponse> getPlayers(Integer teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));
        List<TeamPlayers> players = teamPlayersRepository.findByTeam(team);
        List<PlayerResponse> out = new ArrayList<>();
        for (TeamPlayers p : players) {
            User u = p.getUser();
            out.add(new PlayerResponse(u.getId(), u.getName(), p.isLeader()));
        }
        return out;
    }
}
