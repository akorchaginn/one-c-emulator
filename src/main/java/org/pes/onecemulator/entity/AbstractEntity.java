package org.pes.onecemulator.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.ZonedDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    private UUID id;

    @Column(name = "created_time")
    @CreationTimestamp
    private ZonedDateTime createdTime;

    @Column(name = "updated_time")
    @UpdateTimestamp
    private ZonedDateTime updatedTime;

    @Version
    private Long version;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ZonedDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(ZonedDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public ZonedDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(ZonedDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
