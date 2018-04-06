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

import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Supplier;

@Controller
public class SupplierController {
	
	@Autowired
	private SupplierDAO supplierDAO;
	@Autowired
	private Supplier supplier;
	@Autowired 
	HttpSession httpSession;
	@RequestMapping(name = "/supplier/get/",method = RequestMethod.POST)
	public ModelAndView getSupplier(@RequestParam("id") String id) {
		
		// based on id, fetch the details from categoryDAO
		supplier= supplierDAO.get(id);

		// navigate to home page
		ModelAndView mv = new ModelAndView("redirect:/managesupplier");
		mv.addObject("supplier", supplier);
		return mv;

	}
	@PostMapping("/supplier/save/")
	public ModelAndView saveSupplier(@RequestParam("id") String id,
	@RequestParam("name") String name,
	@RequestParam("address") String address) {
		ModelAndView mv=new ModelAndView("redirect:/managesupplier");
		supplier.setId(id);
		supplier.setAddress(address);
		supplier.setName(name);
		if(supplierDAO.save(supplier)==true) {
			mv.addObject("successMessage", "supplier saved successfully");
		}
		else
		{
			mv.addObject("errorMessage", "could not save the supplier");
		}
		return mv;
		
	}
	@PutMapping("/supplier/update/")
	public ModelAndView updateSupplier(@ModelAttribute Supplier supplier) {
		// navigate to home page
		ModelAndView mv = new ModelAndView("home");

		
		if (supplierDAO.update(supplier) == true) {
			// add success message
			mv.addObject("successMessage", "The supplier updated successfully");
		} else {
			// add failure message
			mv.addObject("errorMessage", "Could not update the supplier.");

		}
		return mv;

	}

	@GetMapping("/supplier/delete/")
	public ModelAndView deleteSupplier(@RequestParam("id") String id) {
		
		ModelAndView mv=new ModelAndView("redirect:/managesupplier");
		if(supplierDAO.delete(id)==true) {
			mv.addObject("successMessage","supplier is deleted successfully");
		}
		else
		{
			mv.addObject("errorMessage","could not delete the supplier");
		}
		return mv;
	}
	@PostMapping("/suppliers")
	public ModelAndView getAllSuppliers(@RequestBody Supplier supplier) {
		ModelAndView mv=new ModelAndView("home");
		List<Supplier> suppliers=supplierDAO.list();
		mv.addObject("suppliers", suppliers);
		
		return mv;
	}
	@GetMapping("/supplier/edit")
	 public ModelAndView editProduct(@RequestParam String id)
	 {
		 ModelAndView mv=new ModelAndView("redirect:/managesupplier");
		supplier=supplierDAO.get(id);
		 
		 httpSession.setAttribute("selectedSupplier", supplier);
		 return mv;
		 
	 }

	 

}
