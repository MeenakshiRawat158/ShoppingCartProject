package com.shoppingcart.frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shoppingcart.BackEnd.dao.SupplierDAO;
import com.shoppingcart.BackEnd.domain.Supplier;

@Controller
public class SupplierController {

	@Autowired
	private Supplier supplier;

	@Autowired
	private SupplierDAO supplierDAO;

	@RequestMapping("/manage_supplier_edit")
	public String manageSupplier(Model model) {
		List<Supplier> supplierList = supplierDAO.list();

		model.addAttribute("isAdminClickedSupplier", "true");
		model.addAttribute("supplierList", supplierList);
		model.addAttribute("supplier", supplier);

		return "/home";
	}

	@PostMapping("/manage_supplier_create")
	public ModelAndView createSupplierManagement(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("description") String description,@RequestParam("address") String address) {

		ModelAndView mv = new ModelAndView("redirect:/manage_supplier");
		supplier.setId(id);
		supplier.setName(name);
		supplier.setDescription(description);
		supplier.setAddress(address);
		supplierDAO.save(supplier);

		return mv;
	}

	@GetMapping("/manage_supplier_delete/{id}")
	public ModelAndView deleteSupplier(@PathVariable("id") String id) {

		ModelAndView mv = new ModelAndView("redirect:/manage_supplier");

		if (supplierDAO.delete(id)) {
			mv.addObject("message", "Successfully delete the supplier");
		} else {
			mv.addObject("message", "Note able delete the supplier please contact administrator");
		}

		return mv;

	}

	@RequestMapping(value = "/manage_supplier_edit/{id}", method = RequestMethod.GET)
	public String editCategory(@PathVariable("id") String id, Model model) {

		supplier = supplierDAO.getSupplierById(id);
		
		return "redirect:/manage_supplier_edit";

	}

	@RequestMapping(value = "/manage_supplier_update")
	public String updateSupplier(@RequestParam("name") String name, @RequestParam("description") String description,@RequestParam("address") String address,
			Model model) {
		// category.setId(id);
		supplier.setName(name);
		supplier.setDescription(description);
		supplier.setDescription(address);
		supplierDAO.update(supplier);

		return "redirect:/manage_supplier";
	}

}
