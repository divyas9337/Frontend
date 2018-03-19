package com.niit.shoppingcart.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	//http://localhost:8080/shoppingcarts
	
@RequestMapping("/")
public String home()
{
return "home";
//we need to fetch all the categories
//Autowire CategoryDAO and categories

}
@GetMapping("/login")
public ModelAndView login()
{
	ModelAndView mv =new ModelAndView("login");
	mv.addObject("isUserClickedLogin",true);
	return mv;
}
@GetMapping("/registration")
public ModelAndView registration()
{
	ModelAndView mv =new ModelAndView("registration");
	mv.addObject("isUserClickedRegister",true);
	return mv;
}
}

