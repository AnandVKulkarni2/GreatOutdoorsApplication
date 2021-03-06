package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entities.Retailer;

@Repository
public interface RetailerDao extends JpaRepository<Retailer, String> {

}
