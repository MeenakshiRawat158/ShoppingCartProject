package com.shoppingcart.frontend.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shoppingcart.BackEnd.dao.CartDAO;
import com.shoppingcart.BackEnd.dao.ProductDAO;
import com.shoppingcart.BackEnd.domain.MyCart;
import com.shoppingcart.BackEnd.domain.Product;
import com.shoppingcart.BackEnd.domain.User;

@Controller
public class CartController {


	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private MyCart myCart;

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private HttpSession session;


	@RequestMapping(value = "/cart/add/{id}", method = RequestMethod.GET)
	public String saveCart(@PathVariable("id") String id, Model model, RedirectAttributes redir) {
		try {
			User user = (User) session.getAttribute("user");
			String user_id = user.getEmail();
			Product product = productDAO.getProductById(id);
			String product_name = product.getName();
			Double price = product.getPrice();

			Long d = System.currentTimeMillis();
			Date today = new Date(d);
			myCart.setUser_id(user_id);
			myCart.setProduct_name(product_name);
			myCart.setPrice(price);
			myCart.setQuantity(1);
			myCart.setStatus('N');
			myCart.setDate_added(today);
			cartDAO.save(myCart);
			redir.addFlashAttribute("msg", "ITEM ADDED SUCCESSFULLY");
			return "redirect:/myCart";
		} catch (Exception e) {
			redir.addFlashAttribute("errorMessage", "Could not add product to the cart.");
			return "redirect:/home";
		}
	}

	@RequestMapping(value = "/myCart")
	public ModelAndView showCart(HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("home");
		User user = (User) session.getAttribute("user");
		String user_id = user.getEmail();
		List<MyCart> cart_list = (List<MyCart>) cartDAO.list(user_id);
		int count = cart_list.size();
		session.setAttribute("cartSize", count);
		Double total_amount = cartDAO.getTotalAmount(user_id);
		mv.addObject("cart", cart_list);
		mv.addObject("TotalAmount", total_amount);
		mv.addObject("userClickedCart", true);
		return mv;
	}

	@RequestMapping(value = "/cart")
	public String ViewCart(Model model) {
		
		model.addAttribute("userClickedCart", true);
		return "home";
	}

	@RequestMapping("/delete_cart/{id}")
	public ModelAndView deleteCart(@PathVariable("id") String id) {

		ModelAndView mv = new ModelAndView("redirect:/myCart");
		myCart = cartDAO.get(id);
		cartDAO.delete(myCart);
		mv.addObject("cartDelete", "Item deleted");
		return mv;

	}

	@RequestMapping("/cart_checkout")
	public String cart_Checkout(Model model) {

		User user = (User) session.getAttribute("user");
		String user_id = user.getEmail();

		List<MyCart> cart_list = (List<MyCart>) cartDAO.list(user_id);
		int count = cart_list.size();
		session.setAttribute("cartSize", count);
		Double total_amount = cartDAO.getTotalAmount(user_id);
		// mv.addObject("myCart",cartDAO.get(user_id));
		model.addAttribute("cart", cart_list);

		model.addAttribute("TotalAmount", total_amount);

		model.addAttribute("userClickedCheckout", true);
		return "home";

	}

	@RequestMapping("/checkout_complete")
	public String checkoutComplete(Model model) {

		User user = (User) session.getAttribute("user");
		String user_id = user.getEmail();

		List<MyCart> cart_list = cartDAO.list(user_id);

		for (MyCart p : cart_list) {
			System.out.println("Cart ID is" + p.getId());

			myCart.setId(p.getId());
			myCart.setStatus('C');
			myCart.setDate_added(p.getDate_added());
			myCart.setPrice(p.getPrice());

			myCart.setProduct_name(p.getProduct_name());
			myCart.setQuantity(p.getQuantity());
			myCart.setUser_id(user_id);
			cartDAO.update(myCart);
			session.setAttribute("cartSize", 0);

		}
		model.addAttribute("userclickedPlaced", true);
		return "home";

	}
}
