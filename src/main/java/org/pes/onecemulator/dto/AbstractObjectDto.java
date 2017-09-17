package org.pes.onecemulator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Calendar;
import java.util.UUID;

public class AbstractObjectDto {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("createdTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-dd-MM hh:mm:ss")
    private Calendar createdTime;

    @JsonProperty("updatedTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-dd-MM hh:mm:ss")
    private Calendar updatedTime;

    @JsonProperty("deleted")
    private Boolean deleted;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Calendar getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Calendar createdTime) {
        this.createdTime = createdTime;
    }

    public Calendar getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Calendar updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
