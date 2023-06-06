package com.learner.shoppinghub.service;

import java.util.List;

import com.learner.shoppinghub.models.Product;

public interface ProductService {
	public List<Product> displayProducts();
	public void saveProduct(Product product);
	public void deleteProduct(int id);
	public void updateProduct(int id);
	public Product findById(int id);
}
