package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entities.Retailer;

public interface RetailerDao extends JpaRepository<Retailer, Integer> {

}
