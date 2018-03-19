package com.niit.shoppingcart.contoller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.domain.Category;
import com.niit.shoppingcart.domain.Product;

public class CategoryController {

	// we need to call CategoryDAO methods
	// get,save,update,delete,list

	// 1. inject the CategoryDAO and Category
	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private Category category;

	// http://localhost:8080/shoppingcart/category/get/cate_001
	// @GetMapping("/category/get/{id}")
	@RequestMapping(name = "/category/get/{id}", method = RequestMethod.GET)
	public ModelAndView getCategory(@RequestParam("id") String id) {
		// based on id, fetch the details from categoryDAO
		category = categoryDAO.get(id);

		// navigate to home page
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("category", category);
		return mv;

	}

	@PutMapping("/category/save/")
	/*
	 * public ModelAndView saveCategory(@RequestParam("id") String id,
	 * 
	 * @RequestParam("id") String name,
	 * 
	 * @RequestParam("id") String description)
	 */
	public ModelAndView saveCategory(@RequestBody Category category) {
		// navigate to home page
		ModelAndView mv = new ModelAndView("home");

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
	public ModelAndView updateCategory(@RequestBody Category category) {
		// navigate to home page
		ModelAndView mv = new ModelAndView("home");

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

	@DeleteMapping("/category/delete/{id}")
	public ModelAndView deleteCategory(@RequestParam("id") String id) {
		// navigate to home page
		ModelAndView mv = new ModelAndView("home");

		// based on id, fetch the details from categoryDAO
		if (categoryDAO.delete(id) == true) {
			// add success message
			mv.addObject("successMessage", "The category deleted successfully");

		} else {
			// add failure message
			mv.addObject("errorMessage", "Could not delete the category.");

		}

		return mv;

	}

	@GetMapping("/categories")
	public ModelAndView getAllCategories() {
		ModelAndView mv = new ModelAndView("home");
		Set<Product> categories = categoryDAO.set();
		mv.addObject("categories", categories);
		return mv;
	}

}