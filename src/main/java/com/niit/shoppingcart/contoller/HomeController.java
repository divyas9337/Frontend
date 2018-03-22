package com.niit.shoppingcart.contoller;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.domain.Category;

@Controller
public class HomeController {
	//http://localhost:8080/shoppingcarts
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private Category category;
	@Autowired
	HttpSession httpsession;
	
	
@RequestMapping(value="/",method=RequestMethod.GET)
public ModelAndView home()
{
	ModelAndView mv=new ModelAndView("home");
List<Category> categories = categoryDAO.list();
httpsession.setAttribute("categories", categories);
//we need to fetch all the categories
//Autowire CategoryDAO and categories
return mv;

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

