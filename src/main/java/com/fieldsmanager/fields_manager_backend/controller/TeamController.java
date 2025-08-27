package com.fieldsmanager.fields_manager_backend.controller;

import com.fieldsmanager.fields_manager_backend.dto.PlayerResponse;
import com.fieldsmanager.fields_manager_backend.dto.TeamRequest;
import com.fieldsmanager.fields_manager_backend.dto.TeamResponse;
import com.fieldsmanager.fields_manager_backend.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth/api/teams")
public class TeamController {

    private final TeamService teamService;
    public TeamController(TeamService teamService) { this.teamService = teamService; }

    // create a team (creator is automatically added as leader)
    @PostMapping("/create")
    public ResponseEntity<TeamResponse> createTeam(@RequestBody TeamRequest req) {
        TeamResponse dto = teamService.createTeam(req);
        return ResponseEntity.ok(dto);
    }

    // list teams current user belongs to
    @GetMapping("/my")
    public ResponseEntity<List<TeamResponse>> getMyTeams() {
        return ResponseEntity.ok(teamService.getMyTeams());
    }

    // join current user to a team
    @PostMapping("/{teamId}/join")
    public ResponseEntity<PlayerResponse> joinTeam(@PathVariable Integer teamId) {
        return ResponseEntity.ok(teamService.joinTeam(teamId));
    }

    // leave team
    @Transactional
    @DeleteMapping("/{teamId}/leave")
    public ResponseEntity<Void> leaveTeam(@PathVariable Integer teamId) {
        teamService.leaveTeam(teamId);
        return ResponseEntity.noContent().build();
    }

    // list players of a team
    @GetMapping("/{teamId}/players")
    public ResponseEntity<List<PlayerResponse>> getPlayers(@PathVariable Integer teamId) {
        return ResponseEntity.ok(teamService.getPlayers(teamId));
    }
}
