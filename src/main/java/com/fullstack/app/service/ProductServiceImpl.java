package com.fullstack.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.app.entity.Product;
import com.fullstack.app.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;


	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> getProductById(Long id) {
		Optional<Product> byId = productRepository.findById(id);
		return byId;
	}

	@Override
	public Product updateProduct(Long id, Product productDetails) {
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
	   
		if (productDetails.getName() != null) {
	        product.setName(productDetails.getName());
	    }

	    if (productDetails.getPrice() > 0) {  
	        product.setPrice(productDetails.getPrice());
	    }

	    if (productDetails.getDescription() != null) {
	        product.setDescription(productDetails.getDescription());
	    }
        return productRepository.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
