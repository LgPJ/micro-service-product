package com.microservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.commons.entity.Product;
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
	
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public Product saveProduct(@RequestBody Product product) {
		
		return productService.save(product);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteProduct(@PathVariable Long id) {
		
		productService.delete(id);
		return "Eliminado";
	}
	
	@PutMapping("/edit/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product editProduct(@RequestBody Product product, @PathVariable Long id) {
		
		Product result = productService.findById(id);
		
		result.setName(product.getName());
		result.setQty(product.getQty());
		
		return productService.save(result);
		
	}
	
	
	

}
