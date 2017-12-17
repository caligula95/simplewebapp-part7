package com.javamaster.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javamaster.dao.CategoryDao;
import com.javamaster.entity.Category;

public class CategoryDaoImpl implements CategoryDao {
	
	private QueryExecutor executor = QueryExecutor.getInstance();

	public int createCategory(Category category) {
		int entityId = 0;
		Object[] args = { category.getName() };
		entityId = executor.executeStatement("insert into category name values(?)", args);
		return entityId;
	}

	public void deleteCategory(Category entity) {
		executor.executeStatement("remove from category where id=?", entity.getId());
	}

	public int editCategory(Category category) {
		int entityId = 0;
		Object[] args = { category.getName() };
		entityId = executor.executeStatement("update category set name=?", args);
		return entityId;
	}

	public List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<Category>();
		Category category = null;
		try {
			ResultSet rs = executor.getResultSet("select id, name from category");
			while(rs.next()){
				category = createEntity(rs);
				categories.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categories;
	}
	
	/**
	 * Creates entity from result set
	 * 
	 * @param rs
	 * @return entity
	 */
	private Category createEntity(ResultSet rs) {
		Category category = new Category();
		try {
			category.setId(rs.getInt("id"));
			category.setName(rs.getString("name"));
		} catch (SQLException e) {
		//	LOGGER.error("SQL exception1 " + e.getMessage());
			e.printStackTrace();
		}
		return category;
	}

}
