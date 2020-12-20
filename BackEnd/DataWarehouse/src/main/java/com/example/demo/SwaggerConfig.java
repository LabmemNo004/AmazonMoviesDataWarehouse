package com.example.demo;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket applicationApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("fakerswe")
                .select()  // 选择哪些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller")) //这里写的是API接口所在的包位置
                .apis(RequestHandlerSelectors.any())
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .build()
                .apiInfo(apiInfo());//用来创建该Api的基本信息(这些基本信息会展现在文档页面中)
    }



    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DCC API接口")//文档说明
                .version("1.0.0")//文档版本说明
                .build();
    }
}