package com.shoppingcart.BackEnd;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestConfiguration {
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
		context.scan("com.shoppingcart.BackEnd");
		
		context.refresh();
		
		context.getBean("user");
		
	}

}
