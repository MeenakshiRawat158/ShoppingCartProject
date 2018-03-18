package com.shoppingcart.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	

	@RequestMapping(value = "/")
	public ModelAndView homepage() {
 
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isUserLoggedIn",false);
		
		return mv;
	}
	
}
