package com.product.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.product.api.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
	public Product findById(int id);

}
