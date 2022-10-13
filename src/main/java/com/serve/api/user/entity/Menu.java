package com.serve.api.user.entity;

import com.serve.api.comm.enums.EnableStatus;
import com.serve.api.comm.model.BaseEntity;
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
@ApiModel(value = "菜单表")
public class Menu extends BaseEntity {
    @ApiModelProperty(value = "父ID")
    @Column(columnDefinition = "int(10) default 0 ")
    protected Integer pId;

    @ApiModelProperty(value = "父级路径")
    @Column(columnDefinition = "varchar(128) DEFAULT '' ")
    private String pPath;

    @ApiModelProperty(value = "路由名称")
    @Column(columnDefinition = "varchar(64) DEFAULT '' ")
    private String name;

    @ApiModelProperty(value = "标题")
    @Column(columnDefinition = "varchar(64) DEFAULT '' ")
    private String title;

    @ApiModelProperty(value = "路径")
    @Column(columnDefinition = "varchar(64) DEFAULT '' ")
    private String path;

    @ApiModelProperty(value = "组件")
    @Column(columnDefinition = "varchar(128) DEFAULT '' ")
    private String component;

    @ApiModelProperty(value = "图标")
    @Column(columnDefinition = "varchar(255) DEFAULT '' ")
    private String icon;

    @ApiModelProperty(value = "排序")
    @Column(columnDefinition = "int(3) default 0 ")
    private String sort;

    @ApiModelProperty(value = "状态")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(64) DEFAULT 'ENABLE' ")
    private EnableStatus status;

    @ApiModelProperty(value = "meta")
    @Column(columnDefinition = "varchar(500) DEFAULT '' ")
    private String meta;
}
