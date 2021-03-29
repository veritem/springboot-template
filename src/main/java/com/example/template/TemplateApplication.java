package com.example.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class TemplateApplication {
	public static void main(String[] args) {
		SpringApplication.run(TemplateApplication.class, args);
	}

	@Bean
	public Docket swaggerDocket(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.template"))
				.build()
				.apiInfo(apiDetials());
	}

	private ApiInfo apiDetials(){
		return new ApiInfo(
				"Spring boot Template Project template" ,
				"An example of application running spring boot",
				"1.0.0",
				"Springboot Project",
				new Contact("Makuza Mugabo Verite","https://veritem.me/","mugaboverite@gmail.com"),
				"MIT",
				"https://github.com/makuzaverite/springboot-project-template/blob/main/LICENSE",
				Collections.emptyList()
		);
	}
}
