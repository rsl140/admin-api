package com.serve.api.user.entity;

import com.serve.api.comm.enums.EnableStatus;
import com.serve.api.comm.model.BaseEntity;
import com.serve.api.user.enums.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@ApiModel(value = "User用户表")
public class User extends BaseEntity {
    @ApiModelProperty(value = "用户名")
    @Column(columnDefinition = "varchar(64) DEFAULT '' ")
    private String name;

    @ApiModelProperty(value = "手机号")
    @Column(columnDefinition = "varchar(20) DEFAULT '' ")
    private String mobile;

    @ApiModelProperty(value = "邮箱号")
    @Column(columnDefinition = "varchar(100) DEFAULT '' ")
    private String email;

    @ApiModelProperty(value = "头像")
    @Column(columnDefinition = "varchar(500) DEFAULT '' ")
    private String headUrl;

    @ApiModelProperty(value = "生日：格式：2019-09-12")
    protected Date birthday;

    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    private Gender gender;

    @ApiModelProperty(value = "登录时间：格式：2019-09-12")
    protected Date loginTime;

    @Column(columnDefinition = "int(11) default 0 ")
    protected Integer loginCount;

    @ApiModelProperty(value = "状态")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(64) DEFAULT 'ENABLE' ")
    private EnableStatus status;

    /***********以下为额外属性，不参与映射数据库*****************/
    @ApiModelProperty(value = "是否新注册")
    @Transient
    private boolean newUser;

    @ApiModelProperty(value = "用户第三方关联信息")
    @Transient
    private UserSecurity security;

    @ApiModelProperty(value = "用户角色")
    @Transient
    private List<Role> roles;

//    @ApiModelProperty(value = "用户权限")
//    @Transient
//    private List<Permission> permissions;

}
