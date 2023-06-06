package com.learner.shoppinghub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learner.shoppinghub.models.Product;
import com.learner.shoppinghub.repository.ProductRepo;
import com.learner.shoppinghub.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepo productrepo;

	@Override
	public List<Product> displayProducts() {
		List<Product> products = productrepo.findAll();
		return products;
	}

	@Override
	public void saveProduct(Product product) {
		productrepo.save(product);
		
	}

	@Override
	public void deleteProduct(int id) {
		
		productrepo.deleteById(id);
	}

	@Override
	public void updateProduct(int id) {// TODO Auto-generated method stub
		
	}

	@Override
	public Product findById(int id) {
		return productrepo.getOne(id);		  
	}

}
