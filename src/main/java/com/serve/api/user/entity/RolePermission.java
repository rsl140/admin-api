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
@ApiModel(value = "User角色权限表")
public class RolePermission extends BaseEntity {
    @ApiModelProperty(value = "权限id")
    @Column(columnDefinition = "int(11) default 0 ")
    private Integer permissionId;

    @ApiModelProperty(value = "角色id")
    @Column(columnDefinition = "int(11) default 0 ")
    private Integer roleId;
}
