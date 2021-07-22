package com.townscript.goodreadsapi;

import com.townscript.goodreadsapi.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@Import(SwaggerConfiguration.class)
public class GoodreadsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodreadsApiApplication.class, args);
	}


}
