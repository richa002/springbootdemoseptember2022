package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.*;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket mySwaggerbean() {


//       return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
//                .paths(PathSelectors.any()).build();


        return new Docket(DocumentationType.SWAGGER_2) .select()
                //.apis(RequestHandlerSelectors.any())   // if we want to include every package
                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))  // to specify any apescific package
               // .paths(PathSelectors.ant("/customers/*"))  // to specify specific path
               // .paths(PathSelectors.ant("/dummy/*"))
                .paths(PathSelectors.any())   // to select all paths
                .build();
    }



}
