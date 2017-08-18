package com.chaocharliehuang.productcatalog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/")
public class ProductCatalog {

	private ProductService productService;
	
	public ProductCatalog(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "index.jsp";
	}
	
	@GetMapping("products")
	@ResponseBody
	public List<Product> allProducts() {
		return productService.getAllProducts();
	}
	
	@PostMapping("products")
	public String postProduct(@RequestBody Product product) {
		productService.createProduct(product);
		return "redirect:/products";
	}
	
	@GetMapping("products/{id}")
	@ResponseBody
	public Product getProduct(@PathVariable("id") Long id) {
		return productService.getProductById(id);
	}
	
	@PutMapping("products/{id}")
	@ResponseBody
	public Product updateProduct(
			@PathVariable("id") Long id,
			@Valid @RequestBody Product product) {
		product.setId(id);
		productService.createProduct(product);
		return product;
	}
	
	@DeleteMapping("products/{id}")
	@ResponseBody
	public List<Product> deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
		return productService.getAllProducts();
	}
}
