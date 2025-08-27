package com.fieldsmanager.fields_manager_backend.dto;

public class PlayerResponse {
    private Integer userId;
    private String userName;
    private boolean isLeader;

    public PlayerResponse() {}

    public PlayerResponse(Integer userId, String userName, boolean isLeader) {
        this.userId = userId;
        this.userName = userName;
        this.isLeader = isLeader;
    }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public boolean isLeader() { return isLeader; }
    public void setLeader(boolean leader) { isLeader = leader; }
}
