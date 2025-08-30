// BookingRequest.java
package com.fieldsmanager.fields_manager_backend.dto;

public class BookingRequest {
    private Integer userId;
    private Integer teamId;
    private Integer fieldSlotId;

    // Getters and setters
    public Integer getTeamId() {
        return teamId;
    }
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
    public Integer getFieldSlotId() {
        return fieldSlotId;
    }
    public void setFieldSlotId(Integer fieldSlotId) {
        this.fieldSlotId = fieldSlotId;
    }

    public Integer getUserId() {
        return userId;
    }
    public void serUserId( Integer userId)
    {
        this.userId = userId;
    }
}
