package com.xboot.config.swagger2;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.*;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger API相关配置
 */
//@Configuration
//@EnableSwagger2
public class Swagger2Config {

	
    @Bean
    public Docket createRestApi() {
        SwaggerProperties swaggerProperties = SwaggerProperties.builder()
                .apiBasePackage("com.macro.mall.tiny.modules")
                .title("XBoot项目骨架")
                .description("XBoot项目骨架相关接口文档")
                .contactName("sing")
                .version("1.0")
                .enableSecurity(true)
                .build();
        
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(makeApiInfo(swaggerProperties))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xboot"))  //此处指明被扫描的包
                .paths(PathSelectors.any())
                .build();
//        if (swaggerProperties.isEnableSecurity()) {
//            docket.securitySchemes(securitySchemes()).securityContexts(securityContexts());
//        }
        return docket;
    }


    private ApiInfo makeApiInfo(SwaggerProperties swaggerProperties) {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .contact(new Contact(swaggerProperties.getContactName(), swaggerProperties.getContactUrl(), swaggerProperties.getContactEmail()))
                .version(swaggerProperties.getVersion())
                .build();
    }





    

}





