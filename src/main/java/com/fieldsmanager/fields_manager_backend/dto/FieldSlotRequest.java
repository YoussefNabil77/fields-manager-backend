package com.fieldsmanager.fields_manager_backend.dto;

import com.fieldsmanager.fields_manager_backend.entity.WeekDay;
import java.math.BigDecimal;

public class FieldSlotRequest{

    private Integer fieldId;
    private WeekDay weekDay;
    private String fromTime;
    private String toTime;
    private BigDecimal pricePerHour;

    public FieldSlotRequest() {}

    public FieldSlotRequest(Integer fieldId, WeekDay weekDay, String fromTime, String toTime, BigDecimal pricePerHour) {
        this.fieldId = fieldId;
        this.weekDay = weekDay;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.pricePerHour = pricePerHour;
    }

    public Integer getFieldId() { return fieldId; }
    public void setFieldId(Integer fieldId) { this.fieldId = fieldId; }

    public WeekDay getWeekDay() { return weekDay; }
    public void setWeekDay(WeekDay weekDay) { this.weekDay = weekDay; }

    public String getFromTime() { return fromTime; }
    public void setFromTime(String fromTime) { this.fromTime = fromTime; }

    public String getToTime() { return toTime; }
    public void setToTime(String toTime) { this.toTime = toTime; }

    public BigDecimal getPricePerHour() { return pricePerHour; }
    public void setPricePerHour(BigDecimal pricePerHour) { this.pricePerHour = pricePerHour; }
}
