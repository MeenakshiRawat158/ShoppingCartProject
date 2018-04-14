package com.shoppingcart.BackEnd.dao;

import java.util.List;

import com.shoppingcart.BackEnd.domain.Supplier;



public interface SupplierDAO {
	
public boolean save(Supplier supplier);
	
	public boolean update(Supplier supplier);
	
	public Supplier get(String id);
	
	public boolean delete(String id);
	
	public List<Supplier> list();
	
	public Supplier getSupplierById(String id);

}
