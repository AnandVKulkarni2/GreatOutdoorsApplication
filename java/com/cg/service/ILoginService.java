package com.cg.service;

import com.cg.entities.Retailer;
import com.cg.entities.UserDetails;

public interface ILoginService {

	Retailer login(UserDetails userDetails);

}
