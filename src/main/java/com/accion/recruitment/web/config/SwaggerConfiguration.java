package com.accion.recruitment.web.config;


import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/30/16 00:11 AM#$
 */
@EnableSwagger2

public class SwaggerConfiguration {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.accion.recruitment.web.controller"))
                .paths(PathSelectors.ant("/**"))
                .build().apiInfo(apiInfo());

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Hooked On Talent")
                .description("Rest API'S.")
                .build();
    }
}