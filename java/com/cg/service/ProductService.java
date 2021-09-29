package com.cg.service;

import com.cg.entities.Product;

public interface ProductService {
	
	Product findProduct(Integer prodId);

	Product addProduct(Product prod);

	Product deleteProduct(Integer prodId);

	Product updateProduct(Product prod);

}
