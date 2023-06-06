package com.learner.shoppinghub.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learner.shoppinghub.models.Category;
import com.learner.shoppinghub.repository.CategoryRepo;
import com.learner.shoppinghub.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepo catrepo;

	@Override
	public void addCategory(Category category) {
		catrepo.save(category);
	}

//gettting all category list
	@Override
	public List<Category> getAll() {
		List<Category> categories = catrepo.findAll();
		for (Category category : categories) {
			System.out.println(category.getName());
		}
		return catrepo.findAll();
	}

//deleting the category by id 
	@Override
	public void deleteCategory(int id) {
		catrepo.deleteById(id);
	}
//update the category by id 
//	@Override
//	public void updateCategoryById(int id) {
//		
//		catrepo.save();
//	}

	@Override
	public Category findCategoryById(int id) {
		System.out.println((catrepo.findById(id)));
		return catrepo.getOne(id);
		
		
	}

}
