package com.cg.service;

import com.cg.entities.Address;
import com.cg.entities.Retailer;

public interface RetailService {

	Address addAddress(Address addr);

	Address updateAddress(Address addr);

	Retailer findRetailer(String retailerId);

	Retailer addRetailer(Retailer ret);

	Retailer deleteRetailer(String retailerId);

	Retailer updateRetailer(Retailer ret);


}
