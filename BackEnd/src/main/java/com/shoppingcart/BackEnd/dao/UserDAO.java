package com.shoppingcart.BackEnd.dao;

import java.util.List;

import com.shoppingcart.BackEnd.domain.User;

public interface UserDAO {
	
	public boolean save(User user);
	
	public boolean update(User user);
	
	public User get(String email);
	
	public boolean delete(String email);
	
	public User validate(String email, String password);
	
	public List<User> list();
}
