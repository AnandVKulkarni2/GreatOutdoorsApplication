package com.cg.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.ProductDao;
import com.cg.entities.Product;
import com.cg.exception.ProductAlreadyExistsException;
import com.cg.exception.ProductNotPresentException;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	
	@Autowired
	private ProductDao pDao;
	
	private Logger logger= Logger.getLogger(ProductServiceImpl.class);

	
	//Read spring AOP
	@Override
	public Product findProduct(Integer prodId) {
		//java.util.Optional<T> class 
		//Optional is a container object used to contain not-null objects.
		//Optional object is used to represent null with absent value.
		//This class has various utility methods to facilitate code to handle values as ‘available’ or ‘not available’ instead of checking null values
		Optional<Product> op= pDao.findById(prodId);//Optional is a container.
		if(!op.isPresent()) {
			logger.error("Product is not present for id "+prodId);
			throw new ProductNotPresentException("Product is not present for id "+prodId);
			}
		return op.get();
	}


	@Override
	public Product addProduct(Product prod) {
		boolean exists=pDao.existsById(prod.getProdId());
        if(exists){
        	logger.error("product already exists for id="+prod.getProdId());
            throw new ProductAlreadyExistsException("product already exists for id="+prod.getProdId());
        }
        prod= pDao.save(prod);
		return prod;
	}


	@Override
	public Product updateProduct(Product prod) {
		boolean exists=pDao.existsById(prod.getProdId());
        if(!exists){
        	logger.error("product doesn't exists for id="+prod.getProdId());
            throw new ProductNotPresentException("product doesn't exists for id="+prod.getProdId());
        }
		return pDao.save(prod);		
		
	}

	@Override
	public Product deleteProduct(Integer prodId) {
		Product prod= findProduct(prodId);
		pDao.deleteById(prodId);
		return prod;
		
	}

}
