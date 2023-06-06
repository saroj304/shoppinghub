package com.learner.shoppinghub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learner.shoppinghub.models.Category;
@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
