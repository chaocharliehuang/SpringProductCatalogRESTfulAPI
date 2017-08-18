package com.chaocharliehuang.productcatalog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaocharliehuang.productcatalog.models.Product;
import com.chaocharliehuang.productcatalog.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductCatalog {

	private ProductService productService;
	
	public ProductCatalog(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("")
	@ResponseBody
	public List<Product> allProducts() {
		return productService.getAllProducts();
	}
	
	@PostMapping("")
	public String postProduct(@RequestBody Product product) {
		productService.createProduct(product);
		return "redirect:/products";
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Product getProduct(@PathVariable("id") Long id) {
		return productService.getProductById(id);
	}
	
	@PutMapping("/{id}")
	public String updateProduct(
			@PathVariable("id") Long id,
			@Valid @RequestBody Product product) {
		product.setId(id);
		productService.createProduct(product);
		return "redirect:/products/" + id;
	}
	
	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
		return "redirect:/products";
	}
}
