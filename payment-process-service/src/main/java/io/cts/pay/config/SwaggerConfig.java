package io.cts.pay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



/**
 * The Class SwaggerConfig.
 * @author Karthi
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	/**
	 * Api Docket Method.
	 *
	 * @return the docket
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex("/api/payment.*")).build();
	}

	/**
	 * Api info method.
	 *
	 * @return the api info
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("PAYMENT PROCESS SERVICE REST API")
				.description("API for Payment processing and balance maintanance.").version("1.0").build();
	}

}