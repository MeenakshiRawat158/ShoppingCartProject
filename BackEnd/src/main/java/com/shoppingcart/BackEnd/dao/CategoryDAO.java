package com.shoppingcart.BackEnd.dao;

import java.util.List;

import com.shoppingcart.BackEnd.domain.Category;

public interface CategoryDAO {
	
public boolean save(Category category);
	
	public boolean update(Category category);
	
	public Category get(String id);
	
	public boolean delete(String id);
	
	public List<Category> list();
	
	public Category getCategoryById(String id) ;
	
	public Category getCategoryByName(String name);

}
