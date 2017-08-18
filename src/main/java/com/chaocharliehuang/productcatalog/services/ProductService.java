package com.chaocharliehuang.productcatalog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chaocharliehuang.productcatalog.models.Product;
import com.chaocharliehuang.productcatalog.repositories.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public Product getProductById(Long id) {
		return productRepository.findOne(id);
	}
	
	public List<Product> getAllProducts() {
		return (List<Product>) productRepository.findAll();
	}
	
	public void createProduct(Product product) {
		productRepository.save(product);
	}
	
	public void deleteProduct(Long id) {
		productRepository.delete(id);
	}

}
