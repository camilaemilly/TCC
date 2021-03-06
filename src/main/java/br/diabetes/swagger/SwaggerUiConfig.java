package br.diabetes.swagger;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.diabetes.Diabetes20Application;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerUiConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(Diabetes20Application.class.getPackage().getName()))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	@SuppressWarnings("rawtypes")
	private ApiInfo apiInfo() {
		return new ApiInfo("REST API ",
				"Aqui você possui todas as informações necessárias para interagir com essa API.", "v1.0", "",
				new Contact("Equipe Diabetes 2.0", "http://diabetes20.io/", ""), "Todos os direitos reservados",
				"", Collections.<VendorExtension>emptyList());
	}
}