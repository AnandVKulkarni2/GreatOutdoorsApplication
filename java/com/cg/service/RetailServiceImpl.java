package com.cg.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.AddressDao;
import com.cg.dao.RetailerDao;
import com.cg.entities.Address;
import com.cg.entities.Retailer;
import com.cg.exception.AddressNotFoundException;
import com.cg.exception.RetailerAlreadyExistsException;
import com.cg.exception.RetailerNotFoundException;

@Service
@Transactional
public class RetailServiceImpl implements RetailService {

	@Autowired
	private RetailerDao rDao;
	
	@Autowired
	private AddressDao aDao;
	
	private Logger logger= Logger.getLogger(RetailServiceImpl.class);

	@Override
	public Address addAddress(Address addr) {
		addr= aDao.save(addr);
		return addr;
	}

	@Override
	public Address updateAddress(Address addr) {
		boolean exists= aDao.existsById(addr.getPhoneNo());
		System.out.println("Exists: "+exists);
		if(!exists) {
			logger.error("Address not found for phone number "+addr.getPhoneNo());
			throw new AddressNotFoundException("Address not found for phone number "+addr.getPhoneNo());
		}
		return aDao.save(addr);
	}


	@Override
	public Retailer findRetailer(Integer retailerId) {
		boolean exists=rDao.existsById(retailerId);
		if(!exists) {
			logger.error("Retailer not found for retailer id "+retailerId);
			throw new RetailerNotFoundException("Retailer not found for retailer id "+retailerId);

		}
		Optional<Retailer> or= rDao.findById(retailerId);
		return or.get();
	}

	@Override
	public Retailer addRetailer(Retailer ret) {
		boolean exists= rDao.existsById(ret.getRetailerId());
		if(exists) {
			logger.error("Retailer already exists with id: "+ret.getRetailerId());
			throw new RetailerAlreadyExistsException("Retailer already exists with id: "+ret.getRetailerId());
		}
		ret= rDao.save(ret);
		return ret;
	}

	@Override
	public Retailer deleteRetailer(int retailerId) {
		Retailer ret= findRetailer(retailerId);
		rDao.deleteById(retailerId);
		return ret;
		
	}

	@Override
	public Retailer updateRetailer(Retailer ret) {
		boolean exists=rDao.existsById(ret.getRetailerId());
		if(!exists) {
			logger.error("Retailer not found for retailer id "+ret.getRetailerId());
			throw new RetailerNotFoundException("Retailer not found for retailer id "+ret.getRetailerId());
		}
		
		return rDao.save(ret);
	}


}
