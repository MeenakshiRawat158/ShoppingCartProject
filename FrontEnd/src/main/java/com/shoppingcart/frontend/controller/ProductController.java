package com.shoppingcart.frontend.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shoppingcart.BackEnd.dao.CategoryDAO;
import com.shoppingcart.BackEnd.dao.ProductDAO;
import com.shoppingcart.BackEnd.dao.SupplierDAO;
import com.shoppingcart.BackEnd.domain.Category;
import com.shoppingcart.BackEnd.domain.Product;
import com.shoppingcart.BackEnd.domain.Supplier;

@Controller
public class ProductController {

	@Autowired
	private Product product;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	Supplier supplier;
	@Autowired
	SupplierDAO supplierDAO;

	@Autowired
	Category category;

	@Autowired
	CategoryDAO categoryDAO;

	@RequestMapping(value = "/manage_product_create", method = RequestMethod.POST)
	public String createProductManagement(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("price") Double price, @RequestParam("category") String category_id,
			@RequestParam("supplier") String supplier_id, @RequestParam("description") String description, Model model,
			RedirectAttributes redir) throws InterruptedException {

		if (productDAO.getProductById(id) == null) {
			// ModelAndView mv = new ModelAndView("redirect:/manage_product_create");
			product.setId(id);
			product.setName(name);
			product.setCategoryId(category_id);
			product.setSupplierId(supplier_id);
			product.setDescription(description);
			product.setPrice(price);

			productDAO.save(product);
			redir.addFlashAttribute("success", "Product Added Successfully");
			redir.addFlashAttribute("createProduct", "true");
		} else {
			// model.addAttribute("error","This Product ID is already exist");
			redir.addFlashAttribute("error", "Product ID is already exist");
		}
		return "redirect:/manage_product";
	}

	@RequestMapping(value = "/manage_product_edit", method = RequestMethod.GET)
	public String manageProductEdit(Model model) {
		List<Product> productList = productDAO.list();

		model.addAttribute("isUserClickedProduct", true);
		model.addAttribute("isAdminClickedProduct", "true");
		model.addAttribute("createProduct", "true");
		model.addAttribute("productList", productList);
		model.addAttribute("product", product);
		model.addAttribute("supplierList", supplierDAO.list());
		model.addAttribute("supplier", supplier);
		model.addAttribute("categoryList", categoryDAO.list());
		model.addAttribute("category", category);

		return "/home";

	}

	@GetMapping("/delete_product/{id}")
	public String deleteCategory(@PathVariable("id") String id, Model model, RedirectAttributes redir) {
		if (productDAO.delete(id)) {
			redir.addFlashAttribute("message", "Successfully delete the product");

		} else {
			redir.addFlashAttribute("message", "Note able delete the product please contact administrator");

		}

		return "redirect:/manage_product";

	}

	@RequestMapping(value = "/manage_product_edit/{id}", method = RequestMethod.GET)
	public String editCategory(@PathVariable("id") String id, Model model) {

		product = productDAO.getProductById(id);
		// List<Category> categoryList = categoryDAO.list();
		return "redirect:/manage_product_edit";

	}

	@RequestMapping(value = "/manage_product_update")
	public String updateCategory(@RequestParam("name") String name, @RequestParam("price") Double price,
			@RequestParam("category") String category_id, @RequestParam("supplier") String supplier_id,
			@RequestParam("description") String description, Model model)
			throws InterruptedException {
		// category.setId(id);

		product.setName(name);
		product.setCategoryId(category_id);
		product.setSupplierId(supplier_id);
		product.setPrice(price);
		product.setDescription(description);
		productDAO.update(product);

		return "redirect:/manage_product";
	}

	@RequestMapping(value = "/display_product/get/{id}", method = RequestMethod.GET)
	public String showProductById(@PathVariable("id") String id, Model mv) {
		mv.addAttribute("userClickedProduct", true);
		mv.addAttribute("mainproduct", productDAO.getProductById(id));
		return "home";

	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public ModelAndView getProduct(@PathVariable("id") String id) {
		ModelAndView mv = new ModelAndView("/home");
		Category category = categoryDAO.getCategoryById(id);
		mv.addObject("userClickedGetProductList", true);
		mv.addObject("categoryName", category.getName());
		Set<Product> products = category.getProducts();
		mv.addObject("productsList", products);
		return mv;
	}

}
