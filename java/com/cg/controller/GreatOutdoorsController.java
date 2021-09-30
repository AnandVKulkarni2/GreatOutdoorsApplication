package com.cg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.Address;
import com.cg.entities.Product;
import com.cg.entities.Retailer;
import com.cg.entities.UserDetails;
import com.cg.exception.InvalidAccessException;
import com.cg.service.ILoginService;
import com.cg.service.ProductService;
import com.cg.service.RetailService;

@RestController
public class GreatOutdoorsController {

	@Autowired
	private ProductService pService;
	
	@Autowired
	private RetailService rService;
	
	@Autowired
	private ILoginService lService;
	
	private Logger logger= Logger.getLogger(GreatOutdoorsController.class);
	
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PostMapping("/login")
	public String login(@RequestBody UserDetails userDetails, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Retailer rDetails= lService.login(userDetails);
		session.setAttribute("username", rDetails.getRetailerId());
		session.setAttribute("role", rDetails.getRole());
		return "Login Successful.......Welcome " + rDetails.getRetailerId() + " ->" + rDetails.getRole();
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PostMapping("/logout")
	public String logout(@RequestBody UserDetails userDetails, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (userDetails.getUsername().equals(session.getAttribute("username"))) {
			session.invalidate();
			return "You have successfully logged out " + userDetails.getUsername();
		}
		return "Not logged off";
}
	

	@GetMapping("/find/{prodid}")
	public ResponseEntity<Product> findProduct(@PathVariable("prodid") Integer prodId,HttpServletRequest request) {
		HttpSession session= request.getSession();
		if(session.getAttribute("username")==null) {
			throw new InvalidAccessException("Please login first");
		}
		logger.info("Finding the product with id: "+prodId);
		Product product = pService.findProduct(prodId);
		logger.info("Product found for id: "+prodId);

		return new ResponseEntity<Product>(product, HttpStatus.OK);

	}

	@PostMapping("/addproduct")
	public ResponseEntity<Product> addProduct(@RequestBody Product prod,HttpServletRequest request) {
		HttpSession session= request.getSession();
		if(session.getAttribute("username")==null) {
			throw new InvalidAccessException("Please login first");
		}
		if(!session.getAttribute("role").equals("Admin")) {
			throw new InvalidAccessException("You don't have access ->"+session.getAttribute("role"));
		}
		logger.info("Adding product: "+prod);
		Product product = pService.addProduct(prod);
		logger.info("Product added: "+prod);
		return new ResponseEntity<Product>(product, HttpStatus.OK);

	}

	@PutMapping("/updateproduct")
	public ResponseEntity<Product> updateProduct(@RequestBody Product prod,HttpServletRequest request) {
		HttpSession session= request.getSession();
		if(session.getAttribute("username")==null) {
			throw new InvalidAccessException("Please login first");
		}
		if(!session.getAttribute("role").equals("Admin")) {
			throw new InvalidAccessException("You don't have access ->"+session.getAttribute("role"));
		}
		logger.info("Updating product: "+prod);
		Product product = pService.updateProduct(prod);
		if (product == null) {
			return new ResponseEntity("Sorry we can't update the product with id" + prod.getProdId(), HttpStatus.OK);
		}
		logger.info("Product updated: "+prod);
		return new ResponseEntity<Product>(product, HttpStatus.OK);

	}

	@GetMapping("/deleteproduct/{prodid}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("prodid") Integer prodId,HttpServletRequest request) {
		HttpSession session= request.getSession();
		if(session.getAttribute("username")==null) {
			throw new InvalidAccessException("Please login first");
		}
		if(!session.getAttribute("role").equals("Admin")) {
			throw new InvalidAccessException("You don't have access ->"+session.getAttribute("role"));
		}
		logger.info("Deleting product with id: "+prodId);
		pService.deleteProduct(prodId);
		logger.info("Product deleted with id: "+prodId);
		return new ResponseEntity("Product deleted successfully", HttpStatus.OK);
	}

	
	@PostMapping("/addaddress")
	public ResponseEntity<Address> addAddress(@RequestBody Address addr,HttpServletRequest request){
		HttpSession session= request.getSession();
		if(session.getAttribute("username")==null) {
			throw new InvalidAccessException("Please login first");
		}
		if(!session.getAttribute("role").equals("User")) {
			throw new InvalidAccessException("You don't have access ->"+session.getAttribute("role"));
		}
		logger.info("Adding address: "+addr);
		Address address= rService.addAddress(addr);
		logger.info("Address added: "+addr);
		return new ResponseEntity<Address>(address, HttpStatus.OK);
		
	}
	
	@PutMapping("/updateaddress")
	public ResponseEntity<Address> updateAddress(@RequestBody Address addr,HttpServletRequest request){
		HttpSession session= request.getSession();
		if(session.getAttribute("username")==null) {
			throw new InvalidAccessException("Please login first");
		}
		if(!session.getAttribute("role").equals("User")) {
			throw new InvalidAccessException("You don't have access ->"+session.getAttribute("role"));
		}
		logger.info("Updating address: "+addr);
		Address address= rService.updateAddress(addr);
		if(address==null) {
			return new ResponseEntity("Sorry, we  can't update the address with pincode "+addr.getPinCode(),HttpStatus.OK);
		}
		logger.info("Address updated: "+addr);
		return new ResponseEntity<Address>(address, HttpStatus.OK);
		
	}
	
	@GetMapping("/findretailer/{id}")
	public ResponseEntity<Retailer> findRetailer(@PathVariable("id") String retailerId,HttpServletRequest request){
		HttpSession session= request.getSession();
		if(session.getAttribute("username")==null) {
			throw new InvalidAccessException("Please login first");
		}
		if(!session.getAttribute("role").equals("Admin")) {
			throw new InvalidAccessException("You don't have access ->"+session.getAttribute("role"));
		}
		logger.info("Fiding retailer with id: "+retailerId);
		Retailer retailer= rService.findRetailer(retailerId);
		logger.info("Found retailer with id: "+retailerId);
		return new ResponseEntity<Retailer>(retailer, HttpStatus.OK);
		
	}
	
	@PostMapping("/addretailer")
	public ResponseEntity<Retailer> addRetailer(@RequestBody Retailer ret){
		logger.info("Adding retailer: "+ret);
		Retailer retailer= rService.addRetailer(ret);
		logger.info("Retailer added: "+ret);
		return new ResponseEntity<Retailer>(retailer, HttpStatus.OK);
		
	}
	
	@GetMapping("/deleteretailer/{retailer_id}")
	public ResponseEntity<Retailer> deleteRetailer(@PathVariable("retailer_id") String retailerId,HttpServletRequest request){
		HttpSession session= request.getSession();
		if(session.getAttribute("username")==null) {
			throw new InvalidAccessException("Please login first");
		}
		if(!session.getAttribute("role").equals("Admin")) {
			throw new InvalidAccessException("You don't have access ->"+session.getAttribute("role"));
		}
		logger.info("Deleting retailer with id: "+retailerId);
		rService.deleteRetailer(retailerId);
		logger.info("Retailer deleted for id: "+retailerId);
		return new ResponseEntity("Retailer deleted successfully",HttpStatus.OK);
		
		
	}
	
	@PutMapping("/updateretailer")
	public ResponseEntity<Retailer> updateRetailer(@RequestBody Retailer ret,HttpServletRequest request){
		HttpSession session= request.getSession();
		if(session.getAttribute("username")==null) {
			throw new InvalidAccessException("Please login first");
		}
		logger.info("Updating retailer: "+ret);
		Retailer retailer= rService.updateRetailer(ret);
		logger.info("Retailer updated: "+ret);
		return new ResponseEntity("Retailer updated successfully",HttpStatus.OK);
		
		
	}
	
	@GetMapping("/findretailer/address/{id}")
	public ResponseEntity<Address> findRetailerAddress(@PathVariable("id") String retailerId,HttpServletRequest request){
		HttpSession session= request.getSession();
		if(session.getAttribute("username")==null) {
			throw new InvalidAccessException("Please login first");
		}
		if(!session.getAttribute("role").equals("Admin")) {
			throw new InvalidAccessException("You don't have access ->"+session.getAttribute("role"));
		}
		logger.info("Fiding retailer address with id: "+retailerId);
		Retailer retailer= rService.findRetailer(retailerId);
		Address addr= retailer.getRetailerAddress();
		logger.info("Found retailer address with id: "+retailerId);
		return new ResponseEntity<Address>(addr, HttpStatus.OK);
		
	}
	
}
