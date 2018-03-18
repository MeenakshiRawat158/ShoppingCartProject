package com.shoppingcart.BackEnd;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitDemoTestCase {
	
	private JUnitDemo jud;
	
	@Before
	public void beforeTesting() {
		jud = new JUnitDemo();
	}
	
	@Test
	public void testAdd() {
		
		int flash = jud.sum(2, 9);
		assertEquals("add test case", 11 , flash);
	}
	
	@Test
	public void testDiff() {
		
		int flash = jud.diff(2, 9);
		assertEquals("diff test case", 7 , flash);
	}
	
	@Test
	public void testDiffAnother() {
		
		int flash = jud.diff(9, 2);
		assertEquals("diff test case", 7 , flash);
	}
	
	
	
	

}
