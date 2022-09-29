package com.serve.api.comm.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @version V1.0
 * @Title: Swagger配置类
 * @ClassName: com.newcapec.config.swagger.Swagger2Configuration.java
 * @Description:
 */
@Configuration
@EnableSwagger2
@Profile({"qa", "dev", "prd"})
public class SwaggerConfig {
    @Autowired
    private AppConfig appConfig;

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .select()
                // 要扫描的API(Controller)基础包
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * @param
     * @return springfox.documentation.service.ApiInfo
     * @Title: 构建API基本信息
     * @methodName: buildApiInfo
     * @Description:
     * @author: kevin
     * @date: 2017-12-11  8:44
     */
    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title(appConfig.getAppName() + "API文档")
                .description("除了查看接口功能外，还提供了调试测试功能")
                .version("1.0")
                .build();
    }
}