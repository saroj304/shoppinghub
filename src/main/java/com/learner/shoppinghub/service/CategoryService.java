package com.learner.shoppinghub.service;

import java.util.List;
import java.util.Optional;

import com.learner.shoppinghub.models.Category;

public interface CategoryService {
	public void addCategory(Category category);

	public List<Category> getAll();

	public void deleteCategory(int id);

	public Category findCategoryById(int id);
//
//	public void updateCategoryById(int id);
}
