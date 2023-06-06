package com.learner.shoppinghub.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.learner.shoppinghub.models.Category;
import com.learner.shoppinghub.models.Product;
import com.learner.shoppinghub.service.CategoryService;
import com.learner.shoppinghub.service.ProductService;

@Controller
public class AdminController {

	@Autowired
	CategoryService categoryservice;
	@Autowired
	ProductService productservice;

	@GetMapping("/admin")
	public String adminPage() {
		return "adminHome";
	}

	@GetMapping("/admin/categories")
	public String adminPageCategories(Model model) {
		List<Category> category = categoryservice.getAll();
		model.addAttribute("categories", category);
		return "categories";
	}

//rendering to category page
	@GetMapping("/admin/categories/add")
	public String adminPageAddCategories(@ModelAttribute("category") Category category) {
		return "categoriesAdd";

	}

// adding new category 
	@PostMapping("/admin/categories/add")
	public String adminPageSaveCategories(@ModelAttribute("category") Category category) {
		System.out.println("adding category");
		categoryservice.addCategory(category);
		return "redirect:/admin/categories";

	}

// deleting the category
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable("id") int id) {
		categoryservice.deleteCategory(id);
		return "redirect:/admin/categories";
	}

	// updating the category by id
	@GetMapping("/admin/categories/update/{id}")
	public String updateCategory(@PathVariable("id") int id, Model model) {

		model.addAttribute("category", categoryservice.findCategoryById(id));

		return "categoriesAdd";

	}

	// Product section
//get the products from the db and display in product page
	@GetMapping("/admin/products")
	public String adminPageProducts(Model model) {
		List<Product> products = productservice.displayProducts();
		model.addAttribute("products", products);	
		return "products";
	}

	@GetMapping("/admin/products/add")
	public String getproductPage(@ModelAttribute("productdto") Product productdto, Model model) {
//		now to find all the categories
		List<Category> category = categoryservice.getAll();
		model.addAttribute("categories", category);
		return "productsAdd";
	}

	@PostMapping("/admin/products/add")
	public String addProduct(@ModelAttribute("productdto") Product productdto,
			@RequestParam("productimage") MultipartFile image) throws IOException {
		if (!image.isEmpty()) {
			System.err.println("hello budddddddy");
			System.out.println(image.getBytes());

			// save the image under the folder path with original name
			FileOutputStream fout = new FileOutputStream(
					"src/main/resources/static/productimages/" + image.getOriginalFilename());
			System.out.println(image.getBytes());
			fout.write(image.getBytes());
			String imagename = image.getOriginalFilename();
//			getting the image name and saving to product table
			productdto.setImageName(imagename);
			productservice.saveProduct(productdto);
			System.out.println(productdto.getCategory());
			fout.close();
			return "redirect:/admin/products";
		}
		return "productsAdd";
	}

	// deleting product by id
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable("id") int id) {
		productservice.deleteProduct(id);
		return "redirect:/admin/products";
	}

//update product by id
	@GetMapping("/admin/product/update/{id}")
	public String updateProduct(@PathVariable("id") int id, Model model) {
		Product p = productservice.findById(id);
		List<Category> category = categoryservice.getAll();
		model.addAttribute("categories", category);
		model.addAttribute("productdto", p);
//		productservice.updateProduct(id);
		return "productsAdd";
	}
}
