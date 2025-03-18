package com.example.Awx_automation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication( exclude = {DataSourceAutoConfiguration.class}) // if database is not specified error occurs you may use it
public class AwxAutomationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwxAutomationApplication.class, args);
	}
	
	@Bean // if you got error like Url attribute is not Specified you may use this and exclude  in above .
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
