package com.microservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservice.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
