package com.learner.shoppinghub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learner.shoppinghub.models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
