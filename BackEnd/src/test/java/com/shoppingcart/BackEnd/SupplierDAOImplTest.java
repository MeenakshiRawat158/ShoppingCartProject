package com.shoppingcart.BackEnd;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shoppingcart.BackEnd.dao.SupplierDAO;
import com.shoppingcart.BackEnd.daoimpl.SupplierDAOImpl;
import com.shoppingcart.BackEnd.domain.Supplier;

public class SupplierDAOImplTest {

	private Supplier supplier;
	private SupplierDAO supplierDao;
	private AnnotationConfigApplicationContext appContext;
	private static String id="sp01";

	@Before
	public void setUp() {
		supplier = new Supplier();
		supplierDao = new SupplierDAOImpl();
		appContext = new AnnotationConfigApplicationContext();
		appContext.scan("com.shoppingcart.BackEnd");
		appContext.refresh();
	
		supplierDao = appContext.getBean("supplierDAO", SupplierDAO.class);
	}

	@Test
	public void createSupplierTestCase() {
		System.out.println(id);
		supplier.setId(id);
		supplier.setAddress("Noida");
		
		supplier.setName("hummingbird nursery");
		
		boolean status = supplierDao.save(supplier);
		assertEquals("testing supplier save", true, status);
	}
}