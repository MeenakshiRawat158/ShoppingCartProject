package com.shoppingcart.BackEnd;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shoppingcart.BackEnd.dao.UserDAO;
import com.shoppingcart.BackEnd.daoimpl.UserDAOImpl;
import com.shoppingcart.BackEnd.domain.User;

public class UserDAOImplTest {

	private User user;
	private UserDAO userDao;
	private AnnotationConfigApplicationContext appContext;
	private static String email= "meenakshi" +(int) (Math.random() * 1000) + "@gmail.com";

	@Before
	public void setUp() {
		user = new User();
		userDao = new UserDAOImpl();
		appContext = new AnnotationConfigApplicationContext();
		appContext.scan("com.shoppingcart.BackEnd");
		appContext.refresh();
	
		userDao = appContext.getBean("userDAO", UserDAO.class);
	}

	@Test
	public void createUserTestCase() {
		System.out.println(email);
		user.setEmail(email);
		user.setAddress("Noida");
		user.setMobile("9989343243");
		user.setName("meeankshi");
		user.setPassword("1234");
		boolean status = userDao.save(user);
		assertEquals("testing user save", true, status);
	}
}