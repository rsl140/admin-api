package com.serve.api.user.entity;

import com.serve.api.comm.model.BaseEntity;
import com.serve.api.user.enums.UserType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@ApiModel(value = "用户组织关联表")
public class UserOrganization extends BaseEntity {
    @ApiModelProperty("用户ID")
    @Column(columnDefinition = "int(11) not null")
    private int userId;

    @ApiModelProperty("组织ID")
    @Column(columnDefinition = "int(11) not null")
    private int organizationId;

    @ApiModelProperty("用户类型")
    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    private UserType userType;
}
