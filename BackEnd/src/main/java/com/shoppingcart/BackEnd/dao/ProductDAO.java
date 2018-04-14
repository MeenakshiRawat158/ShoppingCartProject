package com.shoppingcart.BackEnd.dao;

import java.util.List;

import com.shoppingcart.BackEnd.domain.Product;



public interface ProductDAO {
public boolean save(Product product);
	
	public boolean update(Product product);
	
	public Product get(String id);
	
	public boolean delete(String id);
	
	public List<Product> list();
	
	public Product getProductById(String id);

}
