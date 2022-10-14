package com.serve.api.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.serve.api.auth.entity.Role;
import com.serve.api.comm.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@ApiModel(value = "User角色表")
public class UserRole extends BaseEntity {
    @ApiModelProperty(value = "用户id")
    @Column(columnDefinition = "int(11) default 0 ")
    private Integer userId;

    @ApiModelProperty(value = "角色id")
    @Column(columnDefinition = "int(11) default 0 ")
    private Integer roleId;

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "roleId", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    @NotFound(action = NotFoundAction.IGNORE)
    private Role role;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    @NotFound(action = NotFoundAction.IGNORE)
    private User user;
}