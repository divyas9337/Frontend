package com.niit.shoppingcart.contoller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CartDAO;
import com.niit.shoppingcart.domain.Cart;

@Controller
public class CartController {
	Logger log = LoggerFactory.getLogger(CartController.class);
	@Autowired
	private Cart cart;
	@Autowired
	private CartDAO cartDAO;
	@Autowired
	HttpSession httpsession;
	@PostMapping("/product/cart/add/")
	public ModelAndView addToCart(@RequestParam String productName,
			@RequestParam int price,
			@RequestParam int quantity) {
		ModelAndView mv=new ModelAndView("home");
		String LoggedInUserID=(String)httpsession.getAttribute("LoggedInUserID");
		if(LoggedInUserID==null) {
			mv.addObject("errorMessage","please login to add any product to cart");
			return mv;
		}
		cart.setEmailID(LoggedInUserID);
		cart.setPrice(price);
		cart.setQuantity(quantity);
		if(cartDAO.save(cart)) {
			mv.addObject("successMessage","The product add to cart successfully");
		}
		else
		{
			mv.addObject("errorMessage","could not add the product to the cart...Please try after sometime");
		}
		
		return mv;
	}
	@GetMapping("/mycart")
	public ModelAndView  getMyCartDetails()
	{
		log.debug("Starting of the method getMyCartDetails");
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isUserClickedMyCart", true);
		//it will return all the products which are added to cart
		//??
		String loggedInUserID = (String)httpsession.getAttribute("loggedInUserID");
		log.info("Logged in user id : " + loggedInUserID);
		if(loggedInUserID==null)
		{
		  mv.addObject("errorMessage", "Please login to see your cart details");
		  return mv;
		}
		 List<Cart> cartList = cartDAO.list(loggedInUserID);
		 mv.addObject("cartList", cartList);
		 log.debug("not of products in cart " + cartList.size());
		 log.debug("Ending of the method getMyCartDetails");
		 return mv;
	}
	

}
