package com.fieldsmanager.fields_manager_backend.dto;

public class TeamRequest {
    private String name;
    public TeamRequest() {}
    public TeamRequest(String name) { this.name = name; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
