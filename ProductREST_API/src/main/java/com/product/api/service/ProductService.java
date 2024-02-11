package com.product.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;


import com.product.api.entity.Product;
import com.product.api.repository.ProductRepository;



@Component
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	/*
	 * private static List<Product>list=new ArrayList<>();
	 * 
	 * static { list.add(new Product(101,"Amit","Perfumes",300.00f)); list.add(new
	 * Product(102,"John","TShirts",800.00f)); list.add(new
	 * Product(103,"Peter","Jeans",5000.00f)); list.add(new
	 * Product(104,"Vijay","Watch",3000.00f));
	 * 
	 * 
	 * }
	 */
	 
	//get All Products
	public List<Product> getAllProducts(){
		List<Product> list=(List<Product>)this.productRepository.findAll();
		return list;
		
		
	}
//get  single Product by Id;
	public Product getProductById(int id){
		Product prod=null;
		try {
			prod=this.productRepository.findById(id);
			//prod=list.stream().filter(e->e.getId()==id).findFirst().get();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return prod;
	}
	
	// Add new Product in the List
	public Product addProduct(Product p) {
		//list.add(p);
		Product result=this.productRepository.save(p);
		return result;
		
	}
	
	//deleting a product
	public void deleteProduct(int pId) {
		//list=list.stream().filter(p->p.getId()!=pId).collect(Collectors.toList());
		this.productRepository.deleteById(pId);
	}
	
	//updating the product
	
	
	public void updateProduct( Product pro, int Pid) {
		
		/*
		 * list=list.stream().map(p->{ if(p.getId()==Pid) { p.setDesc(pro.getDesc());
		 * p.setName(pro.getName()); p.setPrice(pro.getPrice()); } return p;
		 * }).collect(Collectors.toList());
		 */
		 
		pro.setId(Pid);
		productRepository.save(pro);
		
	}
	
}
