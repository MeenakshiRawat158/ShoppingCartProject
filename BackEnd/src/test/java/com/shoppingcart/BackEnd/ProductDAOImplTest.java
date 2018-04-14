package com.shoppingcart.BackEnd;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shoppingcart.BackEnd.dao.ProductDAO;
import com.shoppingcart.BackEnd.daoimpl.ProductDAOImpl;
import com.shoppingcart.BackEnd.domain.Product;

public class ProductDAOImplTest {

	private Product product;
	private ProductDAO productDAO;
	private AnnotationConfigApplicationContext appContext;
	private static String id= "pp01" +(int) (Math.random() * 1000);

	@Before
	public void setUp() {
		product = new Product();
		productDAO = new ProductDAOImpl();
		appContext = new AnnotationConfigApplicationContext();
		appContext.scan("com.shoppingcart.BackEnd");
		appContext.refresh();
	
		productDAO = appContext.getBean("productDAO", ProductDAO.class);
	}

	@Test
	public void createProductTestCase() {
		System.out.println(id);
		product.setId(id);
		
		
		product.setName("rose");
		product.setDescription("it is a shrub");
		
		boolean status = productDAO.save(product);
		assertEquals("testing product save", true, status);
	}
}