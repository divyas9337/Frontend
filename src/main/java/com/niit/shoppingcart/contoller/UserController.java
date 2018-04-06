package com.niit.shoppingcart.contoller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CartDAO;
import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.domain.Cart;
import com.niit.shoppingcart.domain.User;
@Controller
public class UserController {
	@Autowired
	private UserDAO userDAO;
	/*@Autowired
	private User user;*/
	@Autowired
	HttpSession httpsession;
	@SuppressWarnings("unused")
	@Autowired
	private Cart cart;
	@Autowired
	private CartDAO cartDAO;
//will send user id and password from jsp to controller
 //it should validate the credentials
  //it should return name ----valid credentials
  //it should return error message----invalid credentials
 
	@SuppressWarnings("unused")
	@RequestMapping(value="Validate",method=RequestMethod.POST)
	public ModelAndView validate(@RequestParam("uname")String name,@RequestParam("psw")String password)
	{
		ModelAndView mv=new ModelAndView("home");
		User user=new User();
		user=userDAO.validate(name, password);
		System.out.println(user.getEmailID());
		if(user==null)
		{
			//invalid credentials
			httpsession.setAttribute("errorMessage","Invalid credentials,pl try again");
		}
		else
		{
			//valid credentials.
			httpsession.setAttribute("welcomMessage", "welcome "+user.getName());
			httpsession.setAttribute("LoggedInUserID",user.getEmailID());
			httpsession.setAttribute("isLoggedIn",true);
			List<Cart> carts = cartDAO.list(user.getEmailID());
			httpsession.setAttribute("size", carts.size());
			
			
			httpsession.setAttribute("carts", carts);
			if(user.getRole()=='A')
			{
				httpsession.setAttribute("isAdmin",true);
			}
		}
				
				return mv;
	}
}
