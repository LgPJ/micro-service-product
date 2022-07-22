package com.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient //Notacion para definir el microservicio como cliente eureka
@SpringBootApplication
@EntityScan({"com.microservice.commons.entity"})
public class MicroproductApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroproductApplication.class, args);
	}

}
