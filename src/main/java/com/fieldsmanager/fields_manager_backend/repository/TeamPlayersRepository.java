package com.fieldsmanager.fields_manager_backend.repository;

import com.fieldsmanager.fields_manager_backend.entity.Team;
import com.fieldsmanager.fields_manager_backend.entity.TeamPlayers;
import com.fieldsmanager.fields_manager_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamPlayersRepository extends JpaRepository<TeamPlayers, Integer> {
    List<TeamPlayers> findByUser(User user);
    List<TeamPlayers> findByTeam(Team team);
    boolean existsByTeam_IdAndUser_Id(Integer teamId, Integer userId);
    void deleteByTeam_IdAndUser_Id(Integer teamId, Integer userId);
}
