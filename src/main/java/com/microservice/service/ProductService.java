package com.microservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.commons.entity.Product;
import com.microservice.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public List<Product> findAll(){
		
		return (List<Product>) productRepository.findAll();
		
	}
	
	@Transactional(readOnly = true)
	public Product findById(Long id){
		
		return productRepository.findById(id).orElseThrow(null);
		
	}
	
	/*No se coloca el readOnly a los metodos de guardado de informacion*/
	@Transactional()
	public Product save(Product product) {
		
		return productRepository.save(product);
	}
	
	@Transactional()
	public void delete(Long id) {
		
		productRepository.deleteById(id);
	}
	
	
	
	
	
	

}
