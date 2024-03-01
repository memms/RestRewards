//package com.Antra.RestRewards.swagger;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.Collections;
//
//import static springfox.documentation.builders.PathSelectors.regex;
//
//@EnableSwagger2
//@Configuration
//public class SwaggerConfig {
//    @Bean
//    public Docket transactionApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.antra"))
//                .paths(regex("/api.*"))
//                .build().apiInfo(metaInfo());
//
//    }
//    private ApiInfo metaInfo() {
//
//        ApiInfo apiInfo=new ApiInfo("Rewards Api", "Rewards Api methods", "1.0", "Terms of Service", new Contact("Antra Inc","http://www.antra.net","abc@gmail.com"), "License for User Details ", "Url of user", Collections.EMPTY_LIST);
//
//        return apiInfo;
//    }
//}
