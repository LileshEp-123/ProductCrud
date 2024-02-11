package com.product.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.entity.Product;
import com.product.api.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	//  get Product Handler
	@GetMapping("/products")
	public ResponseEntity <List<Product>> getProducts() {
		List<Product>list= this.productService.getAllProducts();
		if(list.size()<=0) {
			return ResponseEntity.status(HttpStatus.CREATED).body(list);
		}
		 return ResponseEntity.of(Optional.of(list));
	}
	
	// get Product By Id handler
	@GetMapping("/products/{id}")
	public ResponseEntity getProduct(@PathVariable("id") int id) {
		Product pro= this.productService.getProductById(id);
		if(pro==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		  return ResponseEntity.of(Optional.of(pro));
	} 
	
	
	// adding the product Handler
	@PostMapping("/products")
	public String saveProduct(@RequestBody Product product) {
		 Product p=null;
		 String message = "";
		 try {
			 if(product.getName().isBlank()){
				 System.out.println("Product name must not be empty");
				 message = "Failed to save product - Product name must not be empty";
				 return message;
			 }

			 if(product.getPrice() < 0){
				 System.out.println("Price should not be negative");
				 message = "Failed to save product - Price should not be negative";
				 return message;
			 }

				p= this.productService.addProduct(product);
				System.out.println(product);
				return "Product Added Successfully...";
		 }
		 catch(Exception e) {
			 e.printStackTrace();
			 message = "Failed to save Product";
			 return  e.getMessage() + message;
			 }
	}
	
	//deleting the Product handler
	@DeleteMapping("/products/{proId}")
	public ResponseEntity <Void> deleteProduct(@PathVariable("proId") int proId) {
		try {
		this.productService.deleteProduct(proId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	//updating the Book handler
	@PutMapping("/products/{pId}")
    public ResponseEntity <Product> updateProduct(@RequestBody Product pro, @PathVariable("pId") int pId) {
		try {
    	this.productService.updateProduct(pro,pId);
    	return ResponseEntity.ok().body(pro);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
    	
    }

}


