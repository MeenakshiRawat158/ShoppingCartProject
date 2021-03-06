package com.shoppingcart.frontend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shoppingcart.BackEnd.dao.CategoryDAO;
import com.shoppingcart.BackEnd.dao.ProductDAO;
import com.shoppingcart.BackEnd.dao.SupplierDAO;
import com.shoppingcart.BackEnd.domain.Category;
import com.shoppingcart.BackEnd.domain.Product;
import com.shoppingcart.BackEnd.domain.Supplier;

@Controller
public class HomeController {

	@Autowired
	private HttpSession session;

	@Autowired
	private Category category;

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private Supplier supplier;
	@Autowired
	private SupplierDAO supplierDAO;

	@Autowired
	private Product product;
	@Autowired
	private ProductDAO productDAO;

	@RequestMapping("/home")
	public String reDirectToHome() {
		session.setAttribute("category", category);
		session.setAttribute("supplier", supplier);
		session.setAttribute("product", product);

		session.setAttribute("categoryList", categoryDAO.list());
		session.setAttribute("supplierList", supplierDAO.list());
		session.setAttribute("productList", productDAO.list());
		return "home";
	}

	@RequestMapping(value = { "/" })
	public ModelAndView defaultPage() {
		ModelAndView mv = new ModelAndView("/home");
		session.setAttribute("category", category);
		session.setAttribute("supplier", supplier);
		session.setAttribute("product", product);

		session.setAttribute("categoryList", categoryDAO.list());
		session.setAttribute("supplierList", supplierDAO.list());
		session.setAttribute("productList", productDAO.list());
		return mv;
	}

	@RequestMapping("/login")
	public ModelAndView showMessage() {
		session.setAttribute("category", category);
		session.setAttribute("supplier", supplier);
		session.setAttribute("product", product);

		session.setAttribute("categoryList", categoryDAO.list());
		session.setAttribute("supplierList", supplierDAO.list());
		session.setAttribute("productList", productDAO.list());
		ModelAndView mv = new ModelAndView("/home");
		mv.addObject("isUserClickedLogin", "true");
		return mv;
	}

	@RequestMapping("/j_spring_security_logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redir) {

		CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(
				AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		cookieClearingLogoutHandler.logout(request, response, null);
		securityContextLogoutHandler.logout(request, response, null);
		session.removeAttribute("isUserLoggedIn");
		session.removeAttribute("userRole");
		session.removeAttribute("userName");
		session.removeAttribute("user");
		session.removeAttribute("cartSize");

		redir.addFlashAttribute("msg", "YOU HAVE BEEN LOGGED OUT");
		return "redirect:home";
	}

}
