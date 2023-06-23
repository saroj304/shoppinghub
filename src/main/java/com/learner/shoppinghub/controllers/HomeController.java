package com.learner.shoppinghub.controllers;

import java.util.List;
import java.lang.Math;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learner.shoppinghub.models.Category;
import com.learner.shoppinghub.models.CustomUserDetails;
import com.learner.shoppinghub.models.Product;
import com.learner.shoppinghub.models.User;
import com.learner.shoppinghub.service.CategoryService;
import com.learner.shoppinghub.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	ProductService productservice;

	@Autowired
	CategoryService categoryservice;

	@GetMapping({ "/", "/home" })
	public String homePage(Model model,User u) {
		model.addAttribute("categories", categoryservice.getAll());
		model.addAttribute("products", productservice.displayProducts());
	    System.out.println("my name is "+u.getFname());
//	    / Retrieve the authenticated user's information
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String name = ((CustomUserDetails) authentication.getPrincipal()).getFname();
	    model.addAttribute("name", name);

//	      List<Product>product= productservice.displayProducts();
//	      float noofproduct=(float)(product.size());
//	      int noofslide= (int) ((noofproduct/4.0f)+Math.ceil((noofproduct/4.0f)- (int)(noofproduct/4)));
//	     //sending integer noof slide from controller to view
//	      model.addAttribute("data",noofslide);
		return "index";
	}

	@GetMapping("/shop")
	public String shopPage(Model model) {
		model.addAttribute("categories", categoryservice.getAll());
		model.addAttribute("products", productservice.displayProducts());
		return "shop";
	}

//displaying product based on category
	@GetMapping("/shop/category/{id}")
	public String getProduct(@PathVariable("id") int id, Model model) {
		Category category = categoryservice.findCategoryById(id);
//getting all the product based on the clicked category
		List<Product> products = category.getProduct();
		model.addAttribute("categories", categoryservice.getAll());
		model.addAttribute("products", products);
		return "shop";
	}
//viewProduct
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(@PathVariable("id")int id,Model model) {
     Product product=  productservice.findById(id);
     model.addAttribute("product", product);
		return "viewProduct";

	}

}
