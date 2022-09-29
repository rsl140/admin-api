package com.serve.api.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@ApiModel(value = "账户安全信息表")
public class UserSecurity extends BaseEntity {
    private int userId;
    @JsonIgnore
    private String mixPassword;
    @JsonIgnore
    @Column(length = 16)
    private String salt;

    @ApiModelProperty("微信APP登陆")
    @Column(length = 64)
    private String weChatOpenId;
    @ApiModelProperty("苹果登陆")
    @Column(length = 64)
    private String appleOpenId;
    @ApiModelProperty("绑定微信公众号后的OpenId")
    private String wechatGzhopenid;
    @ApiModelProperty("极光")
    @Column(length = 64)
    private String jpushId;
}
