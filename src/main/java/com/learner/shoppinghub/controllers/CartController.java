package com.learner.shoppinghub.controllers;

import com.learner.shoppinghub.models.Product;
import com.learner.shoppinghub.service.ProductService;
import com.learner.shoppinghub.utilities.GlobalStaticData;
import com.mysql.cj.conf.MemorySizePropertyDefinition;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    private ProductService productService;

    @GetMapping("/addToCart/{id}")
    public String addProductToCart(@PathVariable int id, Model model) {
        GlobalStaticData.cartProduct.add(productService.findById(id));
        return "redirect:/shop";
    }

    @GetMapping("/cart")
    public String viewCartItems(Model model) {
        model.addAttribute("productsInCart", GlobalStaticData.cartProduct);
        model.addAttribute("totalPrice", GlobalStaticData.cartProduct.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("totalProductInCart", GlobalStaticData.cartProduct.size());

        return "cart";
    }

    @GetMapping("/cart/removeItem/{id}")
    public String removeCartItem(@PathVariable int id) {
        GlobalStaticData.cartProduct.remove(id);
        return "redirect:/cart";
    }

}
