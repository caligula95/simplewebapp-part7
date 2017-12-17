package com.javamaster.dao;

import java.util.List;

import com.javamaster.entity.Category;

public interface CategoryDao {

	int createCategory(Category category);
	void deleteCategory(Category category);
	int editCategory(Category category);
	
	List<Category> getAllCategories();
}
