package com.shoppingcart.frontend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoppingcart.BackEnd.dao.CartDAO;
import com.shoppingcart.BackEnd.dao.UserDAO;
import com.shoppingcart.BackEnd.domain.User;

@Controller
public class SpringSecurity {
	@Autowired
	private HttpSession session;

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private UserDAO userDAO;

	@RequestMapping("user_home")
	public ModelAndView validation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("/home");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String name = auth.getName();
		User user = userDAO.getUserByEmail(name);
		session.setAttribute("user", user);
		session.setAttribute("user_id", name);
		session.setAttribute("userName", name);
		if (request.isUserInRole("ROLE_ADMIN")) {
			mv.addObject("isAdmin", "true");

			session.setAttribute("userRole", "ROLE_ADMIN");
		} else {
			int count = cartDAO.list(name).size();
			session.setAttribute("cartSize", count);
			session.setAttribute("userRole", "ROLE_CUSTOMER");
			mv.addObject("isAdmin", "false");
		}
		session.setAttribute("isUserLoggedIn", true);
		return mv;
	}

}
