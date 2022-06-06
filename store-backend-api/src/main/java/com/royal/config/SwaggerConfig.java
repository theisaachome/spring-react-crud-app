package com.royal.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

/**
 *
 *@author Isaachome
 */
@Configuration
public class SwaggerConfig {

	public static final String AUTHORIZATION_HEADER="Authorization";
	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}
	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Spring boot Store REST APIs",
				"Spring boot Store REST APIs documentation",
				"1", 
				"Term of Service", 
				new Contact("isaachome", "www.highway65.net", "isaachome.burma@gmail.com"),
				"License of APIs", 
				"API license url",
				Collections.emptyList());
	}
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.OAS_30)
				.apiInfo(apiInfo())
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.royal.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	
	private SecurityContext securityContext() {
		return SecurityContext
				.builder()
				.securityReferences(defaultAuth())
				.build();
	}
	
	private List<SecurityReference> defaultAuth(){
		AuthorizationScope authorizationScope = 
				new AuthorizationScope("global", "accessEverythin");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0]=authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}
}
