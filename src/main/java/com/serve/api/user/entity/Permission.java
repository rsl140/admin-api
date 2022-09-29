package com.serve.api.user.entity;

import com.serve.api.comm.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@ApiModel(value = "User权限表")
public class Permission extends BaseEntity {
    @ApiModelProperty(value = "权限名")
    @Column(columnDefinition = "varchar(64) DEFAULT '' ")
    private String name;

    @ApiModelProperty(value = "备注")
    @Column(columnDefinition = "varchar(500) DEFAULT '' ")
    private String description;
}
