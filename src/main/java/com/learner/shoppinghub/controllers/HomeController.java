package com.learner.shoppinghub.controllers;

import java.security.Principal;
import java.util.List;
import java.lang.Math;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learner.shoppinghub.models.Category;
import com.learner.shoppinghub.models.CustomUserDetails;
import com.learner.shoppinghub.models.Product;
import com.learner.shoppinghub.models.User;
import com.learner.shoppinghub.service.CategoryService;
import com.learner.shoppinghub.service.ProductService;

@Controller
public class HomeController {
    @Autowired
    private ProductService productservice;

    @Autowired
    private CategoryService categoryservice;


    public String getAuthenticatedUserName() {
        //	    / Retrieve the authenticated user's information
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName()!="anonymousUser"?((CustomUserDetails) authentication.getPrincipal()).getFname():null;
    }

    @GetMapping({"/", "/home"})
    public String homePage(Model model, User u) {
        model.addAttribute("categories", categoryservice.getAll());
        model.addAttribute("products", productservice.displayProducts());
        model.addAttribute("name", getAuthenticatedUserName());
        return "shop";
    }

    @GetMapping("/shop")
    public String shopPage(Model model) {
        model.addAttribute("categories", categoryservice.getAll());
        model.addAttribute("products", productservice.displayProducts());
        model.addAttribute("name", getAuthenticatedUserName());
        return "shop";
    }

    //displaying product based on category
    @GetMapping("/shop/category/{id}")
    public String getProductByCategory(@PathVariable("id") int id, Model model) {
        Category category = categoryservice.findCategoryById(id);
        //getting all the product based on the clicked category
        List<Product> products = category.getProduct();
        model.addAttribute("categories", categoryservice.getAll());
        model.addAttribute("products", products);
        return "shop";
    }

    //viewProduct
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(@PathVariable("id") int id, Model model) {
        Product product = productservice.findById(id);
        model.addAttribute("product", product);
        return "viewProduct";

    }

}
