package uol.compass.avaliacao.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("uol.compass.avaliacao"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.apiInfo(apiInfo())
				.globalOperationParameters(Arrays.asList());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("API Avaliação 03")
				.description("Documentação da API da avaliação da terceira sprint do programa de bolsas da compasso.")
				.version("1.0")
				.build();
		}
}