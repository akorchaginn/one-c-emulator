package org.pes.onecemulator.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

@MappedSuperclass
public class AbstractObject implements Serializable {

    @Id
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private UUID id;
/*
    @Version
    @Column(name = "version", nullable = false)
    private Long version;
*/
    @Column(name = "deleted", nullable = false)
    private Boolean deleted = Boolean.FALSE;

    @Column(name = "created_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdTime;

    @Column(name = "updated_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar updatedTime;

    @PrePersist
    @PreUpdate
    protected void updateBaseFields() {
        Calendar current = GregorianCalendar.getInstance();

        if (createdTime == null) {
            createdTime = current;
        }

        updatedTime = current;

        if (deleted == null) {
            deleted = false;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(getClass().getSimpleName());
        result.append(":");

        result.append("id=");
        result.append(getId());

        //result.append(", ");
        //result.append("version=");
        //result.append(version);

        result.append(", ");
        result.append("deleted=");
        result.append(deleted);
        if (createdTime != null) {
            result.append(", ");
            result.append("createdTime=");
            result.append(createdTime.getTime());
        }

        if (updatedTime != null) {
            result.append(", ");
            result.append("updatedTime=");
            result.append(updatedTime.getTime());
        }

        return result.toString();
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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
}
