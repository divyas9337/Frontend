package com.niit.shoppingcart.contoller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.domain.Product;
import com.niit.util.FileUtil;

@Controller
public class ProductController {
	@Autowired
	private Product product;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	HttpSession httpSession;
	@Autowired
	FileUtil fileUtil;
	//private static final String imageDirectory = "ShoppingCartImages";
	//private static String rootPath = System.getProperty("catalina.home");

	/*@RequestMapping(name = "/product/get/{id}",method = RequestMethod.POST)
	public ModelAndView getCategory(@RequestParam("id") String id) {
	
		product = productDAO.get(id);
		ModelAndView mv = new ModelAndView("redirect:/manageproducts");
		mv.addObject("product",product);
		return mv;

	}*/
	@GetMapping("/product/get/{id}")
	public ModelAndView getSelectedProduct(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/");
		redirectAttributes.addFlashAttribute("selectedProduct",  productDAO.get(id));
		redirectAttributes.addFlashAttribute("isUserSelectedProduct",  true);
		//redirectAttributes.addFlashAttribute("selectedProductImage", rootPath +File.separator +imageDirectory +File.separator +id + ".PNG");
		return mv;

	}
	@PostMapping("/product/save/")
	
	public ModelAndView saveProduct(@RequestParam("id") String id,
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam("price") String price,
			@RequestParam("categoryID") String categoryID,
			@RequestParam("supplierID") String supplierID,
			@RequestParam("file") MultipartFile file){
		
		ModelAndView mv = new ModelAndView("redirect:/manageproducts");
		product.setId(id);
		product.setDescription(description);
		price = price.replace(",","");
		product.setName(name);
		product.setPrice(Integer.parseInt(price));
		product.setCategoryID(categoryID);
		product.setSupplierID(supplierID);

		
		if (productDAO.save(product) == true) {
			
			
			mv.addObject("productsuccessMessage", "The product saved successfully");
			if(FileUtil.fileCopyNIO(file,id+".PNG")) {
				mv.addObject("uploadMessage", "product image successfully uploaded");
			}
			else {
				mv.addObject("uploaderrorMessage", "product image not successfully uploaded");
			}
		} 
		else {
			
			mv.addObject("producterrorMessage", "Could not save the product.");

		}
		return mv;

	}
	@PutMapping("/product/update/")
	public ModelAndView updateProduct(@ModelAttribute Product product) {
		
		ModelAndView mv = new ModelAndView("redirect:/manageproducts");

		
		if (productDAO.update(product) == true) {
			
			mv.addObject("successMessage", "The product updated successfully");
		}
		else 
		{
			
			mv.addObject("errorMessage", "Could not update the product.");

		}
		return mv;

	}
	 @GetMapping("/product/delete")
		public ModelAndView deleteProduct(@RequestParam String id) {
			
			ModelAndView mv = new ModelAndView("redirect:/manageproducts");

			
			if (productDAO.delete(id) == true) {
				
				mv.addObject("successMessage", "The product deleted successfully");

			}
			else {
				
				mv.addObject("errorMessage", "Could not delete the product.");

			}
			
			return mv;	
		}
	 
	 @GetMapping("/products")
		public ModelAndView getAllCategories() {
			ModelAndView mv = new ModelAndView("home");
			List<Product> categories = productDAO.list();
			mv.addObject("products", categories);
			return mv;
		}
	 @GetMapping("/product/edit/")
	 public ModelAndView editProduct(@RequestParam String id)
	 {
		 ModelAndView mv=new ModelAndView("redirect:/manageproducts");
		 product=productDAO.get(id);
		 
		 httpSession.setAttribute("selectedProduct", product);
		 return mv;
		 
	 }

	 
	 
	 
}
