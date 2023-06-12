package com.springboot.jpa2.data.repository;

import com.springboot.jpa2.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }
