package br.com.davi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI().info(constructApiInfo());
	}
	
	private static Info constructApiInfo() {
		var inf = new Info();
		inf.setTitle("Simple weather api");
		inf.setDescription("Simple api that consumes another api (open weather map) to query the weather in any city."
				+ " For study and practice porpuses. To use this api, you need to create an account in open weather map and get a api key");
		inf.version("v1");
		return inf;
	}
}