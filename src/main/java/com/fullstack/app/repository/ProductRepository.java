package com.fullstack.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.app.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
