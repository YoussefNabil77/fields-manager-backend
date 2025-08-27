package com.fieldsmanager.fields_manager_backend.dto;

import java.util.ArrayList;
import java.util.List;

public class TeamResponse {
    private Integer id;
    private String name;
    private List<PlayerResponse> players = new ArrayList<>();

    public TeamResponse() {}
    public TeamResponse(Integer id, String name) { this.id = id; this.name = name; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<PlayerResponse> getPlayers() { return players; }
    public void setPlayers(List<PlayerResponse> players) { this.players = players; }
}
