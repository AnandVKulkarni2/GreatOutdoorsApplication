package com.cg.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.Address;
import com.cg.entities.Product;
import com.cg.entities.Retailer;
import com.cg.service.ProductService;
import com.cg.service.RetailService;

@RestController
public class GreatOutdoorsController {

	@Autowired
	private ProductService pService;
	
	@Autowired
	private RetailService rService;
	
	private Logger logger= Logger.getLogger(GreatOutdoorsController.class);

	@GetMapping("/find/{prodid}")
	public ResponseEntity<Product> findProduct(@PathVariable("prodid") Integer prodId) {
		logger.info("Finding the product with id: "+prodId);
		System.out.println("Finding product for id:" + prodId);
		Product product = pService.findProduct(prodId);
		logger.info("Product found for id: "+prodId);

		return new ResponseEntity<Product>(product, HttpStatus.OK);

	}

	@PostMapping("/addproduct")
	public ResponseEntity<Product> addProduct(@RequestBody Product prod) {
		logger.info("Adding product: "+prod);
		Product product = pService.addProduct(prod);
		logger.info("Product added: "+prod);
		return new ResponseEntity<Product>(product, HttpStatus.OK);

	}

	@PutMapping("/updateproduct") // Only one call will happen if we try multiple times(PUT is idempotent)
	public ResponseEntity<Product> updateProduct(@RequestBody Product prod) {
		logger.info("Updating product: "+prod);
		Product product = pService.updateProduct(prod);
		if (product == null) {
			return new ResponseEntity("Sorry we can't update the product with id" + prod.getProdId(), HttpStatus.OK);
		}
		logger.info("Product updated: "+prod);
		return new ResponseEntity<Product>(product, HttpStatus.OK);

	}

	@GetMapping("/deleteproduct/{prodid}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("prodid") Integer prodId) {
		logger.info("Deleting product with id: "+prodId);
		pService.deleteProduct(prodId);
		logger.info("Product deleted with id: "+prodId);
		return new ResponseEntity("Product deleted successfully", HttpStatus.OK);
	}

	
	@PostMapping("/addaddress")
	public ResponseEntity<Address> addAddress(@RequestBody Address addr){
		logger.info("Adding address: "+addr);
		Address address= rService.addAddress(addr);
		logger.info("Address added: "+addr);
		return new ResponseEntity<Address>(address, HttpStatus.OK);
		
	}
	
	@PutMapping("/updateaddress")
	public ResponseEntity<Address> updateAddress(@RequestBody Address addr){
		logger.info("Updating address: "+addr);
		Address address= rService.updateAddress(addr);
		if(address==null) {
			return new ResponseEntity("Sorry, we  can't update the address with pincode "+addr.getPinCode(),HttpStatus.OK);
		}
		logger.info("Address updated: "+addr);
		return new ResponseEntity<Address>(address, HttpStatus.OK);
		
	}
	
	@GetMapping("/findretailer/{id}")
	public ResponseEntity<Retailer> findRetailer(@PathVariable("id") Integer retailerId){
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
	public ResponseEntity<Retailer> deleteRetailer(@PathVariable("retailer_id") int retailerId){
		logger.info("Deleting retailer with id: "+retailerId);
		rService.deleteRetailer(retailerId);
		logger.info("Retailer deleted for id: "+retailerId);
		return new ResponseEntity("Retailer deleted successfully",HttpStatus.OK);
		
		
	}
	
	@PutMapping("/updateretailer")
	public ResponseEntity<Retailer> updateRetailer(@RequestBody Retailer ret){
		logger.info("Updating retailer: "+ret);
		Retailer retailer= rService.updateRetailer(ret);
		logger.info("Retailer updated: "+ret);
		return new ResponseEntity("Retailer updated successfully",HttpStatus.OK);
		
		
	}
	
	@GetMapping("/findretailer/address/{id}")
	public ResponseEntity<Address> findRetailerAddress(@PathVariable("id") Integer retailerId){
		logger.info("Fiding retailer address with id: "+retailerId);
		Retailer retailer= rService.findRetailer(retailerId);
		Address addr= retailer.getRetailerAddress();
		logger.info("Found retailer address with id: "+retailerId);
		return new ResponseEntity<Address>(addr, HttpStatus.OK);
		
	}
	
}
