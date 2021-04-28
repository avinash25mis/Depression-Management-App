package com;


import com.configuration.DefaultProfileConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class ApplicationStarter extends SpringBootServletInitializer{


	  public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
		}


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		// set a default to use when no profile is configured.
		DefaultProfileConfig.addDefaultProfile(application.application());
		return application.sources(ApplicationStarter.class);
	}

}
