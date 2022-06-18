package com.microservice.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.entity.Product;
import com.microservice.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private Environment environment;
	
	@Value("${server.port}")
	private Integer port;
	
	@GetMapping("/find")
	public List<Product> find(){
		
		return productService.findAll().stream().map(product -> {
			product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
			//product.setPort(port);
			return product;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public Product findById(@PathVariable Long id){
		
		Product product = productService.findById(id);
		product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		//product.setPort(port);
		
		//EL hilo de ejecucion se duerme para poder activar el hystrix
		/*try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		return product;
		
	}

}
