package com.serve.api.comm.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "appconfig")
@Data
public class AppConfig {
    private String appName;
    private String basePackage;
    @ApiModelProperty("服务端域名")
    private String apiDomainName;
    @ApiModelProperty("web端域名")
    private String webDomainName;
    @ApiModelProperty("后端域名")
    private String adminDomainName;
}
