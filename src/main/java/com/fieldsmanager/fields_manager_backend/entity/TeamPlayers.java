package com.fieldsmanager.fields_manager_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "teamplayers")
public class TeamPlayers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Column(name = "is_leader", nullable = false)
    private boolean isLeader = false;

    public TeamPlayers() {}

    public TeamPlayers(User user, Team team, boolean isLeader) {
        this.user = user;
        this.team = team;
        this.isLeader = isLeader;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }

    public boolean isLeader() { return isLeader; }
    public void setLeader(boolean leader) { isLeader = leader; }
}
