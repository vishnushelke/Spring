/******************************************************************************
*  Purpose: This is a configuration class of swagger.
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   04-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.note.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * purpose: this method will create an object of Docket and return it.
	 * @return Docket object
	 */
	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.bridgelabz.fundoo.note.controller")).build();
	}
}
