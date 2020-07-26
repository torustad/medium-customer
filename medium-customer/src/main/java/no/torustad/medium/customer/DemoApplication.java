package no.torustad.medium.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Lære Spring Boot",version = "0.1",description = "Je prøve å lære spring boot da ma"))
public class DemoApplication {

	// http://localhost:10000/swagger-ui/index.html?configUrl=/api-docs/swagger-config

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
