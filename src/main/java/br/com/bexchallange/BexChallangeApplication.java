package br.com.bexchallange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.bexchallange.service.BexRoutesServices;

@SpringBootApplication
public class BexChallangeApplication {

	@Autowired
	private BexRoutesServices bexRoutesServices;

	public static void main(String[] args) {
		SpringApplication.run(BexChallangeApplication.class, args);
	}
	
	@Bean
	public void init() {
		bexRoutesServices.loadFileRoutes();
	}

}
