package com.shoppingcart.frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shoppingcart.BackEnd.dao.CategoryDAO;
import com.shoppingcart.BackEnd.domain.Category;

@Controller
public class CategoryController {

	@Autowired
	private Category category;

	@Autowired
	private CategoryDAO categoryDAO;

	@RequestMapping(value = "/manage_category_edit", method = RequestMethod.GET)

	public String manageCategoriesEdit(Model model) {

		List<Category> categoryList = categoryDAO.list();

		// ModelAndView mv = new ModelAndView("/Admin/AdminHome");
		// mv.addObject("isUserClickedCategory", true);
		// mv.addObject("categoryList", categoryList);
		// mv.addObject("category", category);
		// return mv;

		model.addAttribute("isAdminClickedCategories", "true");
		model.addAttribute("admminCreateCategory", true);
		// model.addAttribute("isUserClickedCategory", true);
		model.addAttribute("category", category);
		model.addAttribute("categoryList", categoryList);

		return "/home";
	}

	@RequestMapping("/manage_category_create")
	public String createCategory(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("description") String description,
			RedirectAttributes redir) throws InterruptedException {

		if (categoryDAO.getCategoryById(id) == null) {
			category.setId(id);
			category.setName(name);
			category.setDescription(description);

			categoryDAO.save(category);

			redir.addFlashAttribute("admminCreateCategory", true);
			redir.addFlashAttribute("success", "Category created successfully");
		} else {
			redir.addFlashAttribute("error", "Category ID is already exist");
		}

		return "redirect:/manage_category";

	}

	@RequestMapping("/manage_category_delete/{id}")
	public ModelAndView deleteCategory(@PathVariable("id") String id) {
		System.out.println("id is" + id);
		ModelAndView mv = new ModelAndView("redirect:/manage_category");

		if (categoryDAO.delete(id)) {
			mv.addObject("message", "Successfully delete the category");
		} else {
			mv.addObject("message", "Note able delete the category pl contact administrator");
		}

		return mv;

	}

	@RequestMapping(value = "/manage_category_edit/{id}", method = RequestMethod.GET)
	public String editCategory(@PathVariable("id") String id, Model model) {

		System.out.println("Edit category started");

		category = categoryDAO.getCategoryById(id);
		// List<Category> categoryList = categoryDAO.list();

		// return "forward:/category_manage";

		// model.addAttribute("isUserClickedCategory", true);
		// model.addAttribute("category", category);

		return "redirect:/manage_category_edit";

	}

	@RequestMapping(value = "/manage_category_update")
	public String updateCategory(@RequestParam("name") String name, @RequestParam("description") String description,
			Model model, RedirectAttributes redir) throws InterruptedException {
		// category.setId(id);
		category.setName(name);
		category.setDescription(description);

		categoryDAO.update(category);
		redir.addFlashAttribute("success", "Category Updated");

		return "redirect:/manage_category";
	}

}
