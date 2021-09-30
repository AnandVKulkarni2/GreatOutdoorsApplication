package com.cg.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="retailer")
public class Retailer {
	
	@Id
	private int retailerId;
	private String retailerName;
	
	@Autowired
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address retailerAddress;

	public Retailer() {
	}

	public Retailer(int retailerId, String retailerName, Address retailerAddress) {
		super();
		this.retailerId = retailerId;
		this.retailerName = retailerName;
		this.retailerAddress = retailerAddress;
	}

	@Override
	public String toString() {
		return "Retailer [retailerId=" + retailerId + ", retailerName=" + retailerName + ", retailerAddress="
				+ retailerAddress + "]";
	}

	public int getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(int retailerId) {
		this.retailerId = retailerId;
	}

	public String getRetailerName() {
		return retailerName;
	}

	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	public Address getRetailerAddress() {
		return retailerAddress;
	}

	public void setRetailerAddress(Address retailerAddress) {
		this.retailerAddress = retailerAddress;
	}
	
	
}
