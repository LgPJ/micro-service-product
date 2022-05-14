package com.microservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.entity.Product;
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
	
	
	
	

}
