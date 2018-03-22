package com.niit.shoppingcart.contoller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.domain.User;
@Controller
public class UserController {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private User user;
	@Autowired
	HttpSession httpsession;
//will send user id and password from jsp to controller
 //it should validate the credentials
  //it should return name ----valid credentials
  //it should return error message----invalid credentials
 
	@PostMapping("/validate")
	public ModelAndView validate(@RequestParam("uname")String name,@RequestParam("psw")String password)
	{
		ModelAndView mv=new ModelAndView("home");
		user=userDAO.validate(name, password);
		if(user==null)
		{
			//invalid credentials
			mv.addObject("errorMessage","Invalid credentials,pl try again ");
		}
		else
		{
			//valid credentials.
			mv.addObject("welcomMessage", "welcome "+user.getName());
			if(user.getRole()=='A')
			{
				mv.addObject("isAdmin",true);
			}
		}
				
				return mv;
	}
}
