package com.shoppingcart.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shoppingcart.BackEnd.dao.UserDAO;
import com.shoppingcart.BackEnd.domain.User;

@Controller
public class UserController {

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registrationPage() {

		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isUserClickedRegister", "true");
		return mv;
	}

	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public ModelAndView sendToLoginPage() {
		ModelAndView mv = new ModelAndView("login");

		return mv;
	}

	@RequestMapping(value = "/createuser", method = RequestMethod.GET)
	public ModelAndView sendToRegistrationPage() {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isUserClickedRegister", "true");

		return mv;
	}

	@PostMapping("/createuser")
	public ModelAndView createUser(@RequestParam(value = "name") String name,
			@RequestParam(value = "mobile") String mobile, @RequestParam(value = "email") String email,
			@RequestParam(value = "password1") String password1, @RequestParam(value = "password2") String password2,
			@RequestParam(value = "address") String address, RedirectAttributes redir) {

		ModelAndView mv = new ModelAndView("home");
		User inUser = userDAO.get(email);
		if (inUser.getEmail().isEmpty()) {
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
				mv.addObject("isUserClickedLogin", "true");
			}
		} else {
			System.out.println("pe" + userDAO.get(email).getEmail());
			mv.addObject("errorMessage", "Email address already registered");
			mv.addObject("isUserClickedRegister", "true");
		}

		return mv;

	}
	
		@RequestMapping(value = "/loginError", method = RequestMethod.GET)
		public String loginError(Model model) {

			// log.debug("Starting of the method loginError");
			model.addAttribute("errorMessage", "INVALID CREDENTIALS PLEASE TRY AGAIN.");
			// log.debug("Ending of the method loginError");
			return "home";

		}

		@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
		public String accessDenied(Model model, RedirectAttributes redir) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			redir.addFlashAttribute("errorMessage", "YOU ARE NOT AUTHORIZED TO ACCESS THIS PAGE ");
			return "home";

		}
}