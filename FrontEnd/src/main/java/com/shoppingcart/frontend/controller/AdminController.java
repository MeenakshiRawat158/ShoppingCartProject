package com.shoppingcart.frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shoppingcart.BackEnd.dao.CategoryDAO;
import com.shoppingcart.BackEnd.dao.ProductDAO;
import com.shoppingcart.BackEnd.dao.SupplierDAO;
import com.shoppingcart.BackEnd.domain.Category;
import com.shoppingcart.BackEnd.domain.Product;
import com.shoppingcart.BackEnd.domain.Supplier;

@Controller
public class AdminController {
	
	@Autowired
	private Category category;

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private  Supplier supplier;
	
	@Autowired
	private  SupplierDAO supplierDAO;

	@RequestMapping(value="/manage_category", method = RequestMethod.GET)
	public String manageCategories(Model model) {

		List<Category> categoryList = categoryDAO.list();
		model.addAttribute("isAdminClickedCategories", "true");
		model.addAttribute("categoryList", categoryList);
		
		return "/home";
	}
	@RequestMapping(value="/manage_product",method = RequestMethod.GET)
	public String manageProduct(Model model) {
		List<Product> productList = productDAO.list();
		model.addAttribute("isAdminClickedProduct", "true");
		model.addAttribute("productList", productList);
		model.addAttribute("supplierList", supplierDAO.list());
		model.addAttribute("supplier",supplier);
		model.addAttribute("categoryList", categoryDAO.list());
		model.addAttribute("category",category);
		
		return "/home";

	}
	
	@RequestMapping("/manage_supplier")
	public String manageSupplier(Model model) {
		List<Supplier> supplierList = supplierDAO.list();
		model.addAttribute("isAdminClickedSupplier", "true");
		model.addAttribute("supplierList", supplierList);

		return "/home";
	}

}
