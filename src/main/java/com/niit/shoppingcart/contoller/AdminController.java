package com.niit.shoppingcart.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	@GetMapping("/managecategories")
	public ModelAndView adminClickedCategories()
	{
		ModelAndView mv=new ModelAndView("home");
		mv.addObject("is")
		
	}

}
