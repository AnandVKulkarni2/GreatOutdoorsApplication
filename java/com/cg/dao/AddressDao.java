package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entities.Address;

public interface AddressDao extends JpaRepository<Address, Long> {

	Address findByPinCode(Integer pinCode);

}
