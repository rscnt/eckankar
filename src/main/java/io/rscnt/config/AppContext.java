package io.rscnt.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@ComponentScan(basePackages = { "io.rscnt" })
@EnableAutoConfiguration
public class AppContext extends WebMvcConfigurerAdapter {

	public AppContext() {
		// TODO Auto-generated constructor stub
	}

	@Bean
	ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
		resolver.setSuffix(".html");
		resolver.setTemplateMode("HTML5");
		return resolver;
	}

	@Bean
	SpringTemplateEngine engine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver());
		return engine;
	}

	@Bean
	ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(engine());
		return viewResolver;
	}

	// Adding multipart to the configuration
	@Bean
	MultipartConfigElement multipartConfigElement() {
		return new MultipartConfigElement("");
	}

	public static void main(String[] args) {
		SpringApplication.run(AppContext.class, args);

	}

}
