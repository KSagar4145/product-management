package com.fullstack.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.app.entity.Product;
import com.fullstack.app.service.ProductService;

//@CrossOrigin(origins = "http://localhost:3000") // Allow requests from React frontend
@CrossOrigin(origins = "http://product-management-frontend.s3-website.ap-south-1.amazonaws.com")
@RestController
@RequestMapping("/api/products")
public class ProductController {

	 @Autowired
	    private ProductService productService;

	    @PostMapping
	    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
	        Product createdProduct = productService.createProduct(product);
	        return ResponseEntity.status( HttpStatus.CREATED).body(createdProduct);
	    }

	    @GetMapping
	    public List<Product> getAllProducts() {
	        return productService.getAllProducts();
	    }
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
//	        Optional<Product> product = productService.getProductById(id);
//	        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	        
	        Product product = productService.getProductById(id)
	                .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
	        return ResponseEntity.ok(product);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
	        Product updatedProduct = productService.updateProduct(id, productDetails);
	        return ResponseEntity.ok(updatedProduct);
	    }
	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
	        productService.deleteProduct(id);
	        return ResponseEntity.noContent().build();
	    }
	    
	    @GetMapping("/test")
	    public String testing() {
	        return ">>>>>>>>>>>>>>>>>>";
	    }
	
}
