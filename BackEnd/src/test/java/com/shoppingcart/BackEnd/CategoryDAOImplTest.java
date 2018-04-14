package com.shoppingcart.BackEnd;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shoppingcart.BackEnd.dao.CategoryDAO;
import com.shoppingcart.BackEnd.daoimpl.CategoryDAOImpl;
import com.shoppingcart.BackEnd.domain.Category;

public class CategoryDAOImplTest {

	private Category category;
	private CategoryDAO categoryDAO;
	private AnnotationConfigApplicationContext appContext;
	private static String id= "plant" +(int) (Math.random() * 1000);

	@Before
	public void setUp() {
		category = new Category();
		categoryDAO = new CategoryDAOImpl();
		appContext = new AnnotationConfigApplicationContext();
		appContext.scan("com.shoppingcart.BackEnd");
		appContext.refresh();
	
		categoryDAO = appContext.getBean("categoryDAO", CategoryDAO.class);
	}

	@Test
	public void createUserTestCase() {
		System.out.println(id);
		category.setId(id);
		
		
		category.setName("plant");
		category.setDescription("plant");
		
		boolean status = categoryDAO.save(category);
		assertEquals("testing CATEGORY save", true, status);
	}
}