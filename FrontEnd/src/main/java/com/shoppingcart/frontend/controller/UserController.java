package com.shoppingcart.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shoppingcart.BackEnd.dao.UserDAO;
import com.shoppingcart.BackEnd.domain.User;

@Controller
public class UserController {

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView loginPage() {

		ModelAndView mv = new ModelAndView("login");

		return mv;
	}

	@RequestMapping(value = "registration", method = RequestMethod.GET)
	public ModelAndView registrationPage() {

		ModelAndView mv = new ModelAndView("registration");

		return mv;
	}

	@RequestMapping(value = "validate", method = RequestMethod.GET)
	public ModelAndView sendToLoginPage() {
		ModelAndView mv = new ModelAndView("login");

		return mv;
	}

	@PostMapping("validate")
	public ModelAndView loginPagePost(@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password) {
		ModelAndView mv;
		System.out.println(userDAO);
		User user = userDAO.validate(email, password);
		if (user == null) {
			mv = new ModelAndView("login");
			mv.addObject("errorMessage", "Either email or password is invalid. Please try again.");

		} else {
			mv = new ModelAndView("home");
			mv.addObject("isUserLoggedIn",true);
			mv.addObject("welcomeMessage", "Welcome Mr./Ms " + user.getName());
		}
		return mv;
	}

	@RequestMapping(value = "createuser", method = RequestMethod.GET)
	public ModelAndView sendToRegistrationPage() {
		ModelAndView mv = new ModelAndView("registration");

		return mv;
	}

	@PostMapping("createuser")
	public ModelAndView createUser(@RequestParam(value = "name") String name,
			@RequestParam(value = "mobile") String mobile, @RequestParam(value = "email") String email,
			@RequestParam(value = "password1") String password1, @RequestParam(value = "password2") String password2,
			@RequestParam(value = "address") String address) {
		ModelAndView mv;

		mv = new ModelAndView("registration");
		mv.addObject("name", name);
		mv.addObject("email", email);
		mv.addObject("address", address);
		mv.addObject("mobile", mobile);
		if (name.isEmpty()) {
			mv.addObject("errorMessage", "Invalid name");
		} else if (mobile.isEmpty()) {
			mv.addObject("errorMessage", "Invalid phone no");

		} else if (email.isEmpty()) {
			mv.addObject("errorMessage", "Invalid email Address");
		} else if (address.isEmpty()) {
			mv.addObject("errorMessage", "Invalid Address");
		} else if (password1.isEmpty() || password2.isEmpty() || !password1.equals(password2)) {
			mv.addObject("errorMessage", "Invalid password");
		} else {
			User user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setAddress(address);
			user.setPassword(password1);
			user.setMobile(mobile);
			User user2 = userDAO.get(email);
			
			if (user2 == null) {
				boolean save = userDAO.save(user);
				if (!save) {
					mv.addObject("errorMessage", "Error while creating user account.");
				} else {
					mv = new ModelAndView("home");
					mv.addObject("welcomeMessage", "Welcome Mr/Mrs. " + name);
				}
			}else {
				mv.addObject("errorMessage", "Email address already in use. Please choose different email address");
			}
		}

		return mv;

	}
}