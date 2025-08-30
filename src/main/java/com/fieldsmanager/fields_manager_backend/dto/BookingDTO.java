package com.fieldsmanager.fields_manager_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookingDTO {
    private Integer id;
    private Integer userId;
    private Integer teamId;
    private Integer fieldSlotId;
    private LocalDate date;
    private BigDecimal price;
    private String status;

    public BookingDTO(Integer id, Integer userId, Integer teamId, Integer fieldSlotId,
                      LocalDate date, String status) {
        this.id = id;
        this.userId = userId;
        this.teamId = teamId;
        this.fieldSlotId = fieldSlotId;
        this.date = date;
        this.status = status;
    }

    public BookingDTO() {

    }

    // Getters & setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getTeamId() { return teamId; }
    public void setTeamId(Integer teamId) { this.teamId = teamId; }

    public Integer getFieldSlotId() { return fieldSlotId; }
    public void setFieldSlotId(Integer fieldSlotId) { this.fieldSlotId = fieldSlotId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

