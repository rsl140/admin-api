package com.serve.api.comm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 所有实体类的基类
 */

@MappedSuperclass
@Data
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(columnDefinition = "datetime DEFAULT CURRENT_TIMESTAMP")
    protected Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(columnDefinition = "datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    protected Date updatedAt;

    @PrePersist
    private void beforeSave() {
        Date currentDate = new Date();
        this.createdAt = currentDate;
        this.updatedAt = currentDate;
    }

    @PreUpdate
    public void beforeUpdate() {
        this.updatedAt = new Date();
    }
}
