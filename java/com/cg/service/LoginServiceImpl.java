package com.cg.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dao.ILoginDao;
import com.cg.entities.Retailer;
import com.cg.entities.UserDetails;
import com.cg.exception.UserNameNotFoundException;

@Service
@Transactional
public class LoginServiceImpl implements ILoginService {
	
	@Autowired
	private ILoginDao lDao;
	
	
	@Override
	public Retailer login(UserDetails userDetails) {
		Optional<Retailer> opt= lDao.findById(userDetails.getUsername());
		if(userDetails.getUsername().equals("Admin")) {
			if(userDetails.getPassword().equals("admin123")) {
				Retailer details= new Retailer("Admin","Admin");
				return details;
			}
			else {
				throw new UserNameNotFoundException("Invalid password");
			}
		}
		if(!opt.isPresent()) {
			throw new UserNameNotFoundException("Username "+userDetails.getUsername()+" not found");
		}
		Retailer details= opt.get();
		if(!details.getPassword().equals(userDetails.getPassword())) {
			throw new UserNameNotFoundException("Invalid password");
		}
		return details;
	}

}
