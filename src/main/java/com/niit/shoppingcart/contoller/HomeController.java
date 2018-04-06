package com.niit.shoppingcart.contoller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CartDAO;
import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Cart;
import com.niit.shoppingcart.domain.Category;
import com.niit.shoppingcart.domain.Product;
import com.niit.shoppingcart.domain.Supplier;

@Controller
public class HomeController {
	//http://localhost:8080/shoppingcarts
	@Autowired
	private CategoryDAO categoryDAO;
	@SuppressWarnings("unused")
	@Autowired
	private Category category;
	@Autowired
	private SupplierDAO supplierDAO;
	@SuppressWarnings("unused")
	@Autowired
	private Supplier supplier;
	@SuppressWarnings("unused")
	@Autowired
	private Product product;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	HttpSession httpsession;
	@SuppressWarnings("unused")
	@Autowired
	private Cart cart;
	@Autowired
	private CartDAO cartDAO;
	private static String imageDirectory="D:\\divs\\divs_wrksp\\ShoppingCartFrontEnd\\src\\main\\webapp\\resources\\images";
	
	@GetMapping("/")
public ModelAndView home(HttpServletRequest request)
{
	ModelAndView mv=new ModelAndView("home");
List<Category> categories = categoryDAO.list();
httpsession.setAttribute("categories", categories);
List<Supplier> suppliers=supplierDAO.list();
httpsession.setAttribute("suppliers", suppliers);
List<Product> products=productDAO.list();
httpsession.setAttribute("products", products);
List<Cart> cartList=cartDAO.list("emailID");
httpsession.setAttribute("cartList",cartList);
//we need to fetch all the categories
//Autowire CategoryDAO and categories
httpsession.setAttribute("categories", categories);
httpsession.setAttribute("imageDirectory", imageDirectory);
String root =request.getContextPath();
String imageFolder =  root + File.separator +"src" + File.separator + 
		"main" +File.separator +
		"webapp"+File.separator +
		"resources"+File.separator;	
httpsession.setAttribute("imageFolder", imageFolder);
return mv;

}
@GetMapping("/login")
public ModelAndView login()
{
	ModelAndView mv =new ModelAndView("home");
	mv.addObject("isUserClickedLogin",true);
	return mv;
}
@GetMapping("/logout")
public ModelAndView logout()
{
	//at the time of login, we add user id in http session
	//at the time of logout, we need to remove user id from httpsession.
	ModelAndView mv = new ModelAndView("home");
	
	httpsession.invalidate();
	
	mv.addObject("logoutMessage", "You are successfully logged out");
	return mv;
	
}
@GetMapping("/registration")
public ModelAndView registration()
{
	ModelAndView mv =new ModelAndView("home");
	mv.addObject("isUserClickedRegister",true);
	return mv;
}
}

