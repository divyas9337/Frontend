package com.niit.shoppingcart.contoller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.domain.Category;



@Controller
public class CategoryController {

	// we need to call CategoryDAO methods
	// get,save,update,delete,list

	// 1. inject the CategoryDAO and Category
	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private Category category;
	@Autowired
	HttpSession httpSession;

	// http://localhost:8080/shoppingcart/category/get/cate_001
	// @GetMapping("/category/get/{id}")
	@RequestMapping(name = "/category/get/{id}",method = RequestMethod.GET)
	public ModelAndView getCategory(@RequestParam("id") String id) {
		
		// based on id, fetch the details from categoryDAO
		category = categoryDAO.get(id);

		// navigate to home page
		ModelAndView mv = new ModelAndView("redirect:/managecategories");
		mv.addObject("category", category);
		return mv;

	}

	@PostMapping("/category/save/")
	/*
	 * public ModelAndView saveCategory(@RequestParam("id") String id,
	 * 
	 * @RequestParam("id") String name,
	 * 
	 * @RequestParam("id") String description)
	 */
	public ModelAndView saveCategory(@RequestParam("id") String id,
			@RequestParam("name") String name,
			@RequestParam("description") String description){
		// navigate to home page
		
		ModelAndView mv = new ModelAndView("redirect:/managecategories");
		category.setId(id);
		category.setName(name);
		category.setDescription(description);

		// call save method of categoryDAO
		if (categoryDAO.save(category) == true) {
			// add success message
			mv.addObject("successMessage", "The category saved successfully");
		} else {
			// add failure message
			mv.addObject("errorMessage", "Could not save the category.");

		}
		return mv;

	}

	@PutMapping("/category/update/")
	public ModelAndView updateCategory(@ModelAttribute Category category) {
		// navigate to home page
		ModelAndView mv = new ModelAndView("redirect:/managecategories");

		// call save method of categoryDAO
		if (categoryDAO.update(category) == true) {
			// add success message
			mv.addObject("successMessage", "The category updated successfully");
		} else {
			// add failure message
			mv.addObject("errorMessage", "Could not update the category.");

		}
		return mv;

	}

	 @GetMapping("/category/delete/")
	public ModelAndView deleteCategory(@RequestParam String id) {
		System.out.println("Delete category operation starts");
		// navigate to home page
		ModelAndView mv = new ModelAndView("redirect:/managecategories");

		// based on id, fetch the details from categoryDAO
		if (categoryDAO.delete(id) == true) {
			// add success message
			mv.addObject("successMessage", "The category deleted successfully");

		} else {
			// add failure message
			mv.addObject("errorMessage", "Could not delete the category.");

		}
		System.out.println("Category Id has been deleted successfully");
		return mv;	
	}
	 @GetMapping("/category/edit")
	 public ModelAndView editCategory(@RequestParam String id)
	 {
		 ModelAndView mv=new ModelAndView("redirect:/managecategories");
		 category=categoryDAO.get(id);
		 //mv.addObject("selectedCategory",category);
		 httpSession.setAttribute("selectedCategory", category);
		 return mv;
		 
	 }
	 

	@GetMapping("/categories")
	public ModelAndView getAllCategories(@RequestBody Category category) {
		ModelAndView mv = new ModelAndView("redirect:/managecategories");
	List<Category> categories = categoryDAO.list();
		mv.addObject("categories", categories);
		return mv;
	}

}
