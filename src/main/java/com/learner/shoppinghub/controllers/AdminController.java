package com.learner.shoppinghub.controllers;

import com.learner.shoppinghub.models.Category;
import com.learner.shoppinghub.models.Product;
import com.learner.shoppinghub.models.User;
import com.learner.shoppinghub.repository.RoleRepository;
import com.learner.shoppinghub.service.CategoryService;
import com.learner.shoppinghub.service.ProductService;
import com.learner.shoppinghub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @RequestMapping("/admin_page")
    public String adminPage() {
        return "adminHome"; // Return the view for the admin home page
    }

    @GetMapping("/categories")
    public String adminPageCategories(Model model) {
        List<Category> categories = categoryService.getAll(); // Fetch all categories
        model.addAttribute("categories", categories); // Add categories to the model
        return "categories"; // Return the view for categories
    }

    @GetMapping("/categories/add")
    public String adminPageAddCategories(@ModelAttribute("category") Category category) {
        return "categoriesAdd";
    }

    // adding new category
    @PostMapping("/categories/add")
    public String adminPageSaveCategories(@ModelAttribute("category") Category category) {
        System.out.println("adding category");
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    // deleting the category
    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") int id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }

    // updating the category by id
    @GetMapping("/categories/update/{id}")
    public String updateCategory(@PathVariable("id") int id, Model model) {
        model.addAttribute("category", categoryService.findCategoryById(id));
        return "categoriesAdd";

    }

    // Product section
//get the products from the db and display in product page
    @GetMapping("/products")
    public String adminPageProducts(Model model) {
        List<Product> products = productService.displayProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/add")
    public String getproductPage(@ModelAttribute("productdto") Product productdto, Model model) {
//		now to find all the categories
        List<Category> category = categoryService.getAll();
        model.addAttribute("categories", category);
        return "productsAdd";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute("productdto") Product productdto,
                             @RequestParam("productimage") MultipartFile image) throws IOException {
        if (!image.isEmpty()) {
            System.out.println(image.getBytes());

            // save the image under the folder path with original name
            FileOutputStream fout = new FileOutputStream(
                    "src/main/resources/static/productimages/" + image.getOriginalFilename());
            System.out.println(image.getBytes());
            fout.write(image.getBytes());
            String imagename = image.getOriginalFilename();
//			getting the image name and saving to product table
            productdto.setImageName(imagename);
            productService.saveProduct(productdto);
            System.out.println(productdto.getCategory());
            fout.close();
            return "redirect:/admin/products";
        }
        return "productsAdd";
    }

    // deleting product by id
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    //update product by id
    @GetMapping("/product/update/{id}")
    public String updateProduct(@PathVariable("id") int id, Model model) {
        Product p = productService.findById(id);
        List<Category> category = categoryService.getAll();
        model.addAttribute("categories", category);
        model.addAttribute("productdto", p);
//		productservice.updateProduct(id);
        return "productsAdd";
    }

    //manage users
    @GetMapping("/fetch_users")
    public String fetchUser(Model m) {
        List<User> u = userService.getUser();
        m.addAttribute("user", u);
        return "users";
    }

    @GetMapping("/user/edit/{userId}")
    public String editUser(@PathVariable int userId, Model model) {
        Optional<User> userEntity = userService.findById(userId);
        userEntity.orElseThrow(() -> new UsernameNotFoundException("user with the id" + userId + " not found"));
        model.addAttribute("user", userEntity);
        return "editUser";
    }

    @GetMapping("/user/delete/{userId}")
    public String deleteUser(@PathVariable int userId) {
        try {
            userService.deleteById(userId);
        } catch (Exception e) {
            throw new UsernameNotFoundException("user with the id " + userId + " not found");
        }
        return "redirect:/admin/users";
    }

}
