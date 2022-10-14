package com.serve.api.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.serve.api.comm.model.BaseEntity;
import com.serve.api.user.entity.UserRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@ApiModel(value = "User角色表")
public class Role extends BaseEntity {
    @ApiModelProperty(value = "角色名")
    @Column(columnDefinition = "varchar(64) DEFAULT '' ")
    private String name;

    @ApiModelProperty(value = "备注")
    @Column(columnDefinition = "varchar(500) DEFAULT '' ")
    private String description;

    @OneToMany(targetEntity = UserRole.class, mappedBy = "role")
    @JsonIgnore
    private List<UserRole> userRoles;
}
