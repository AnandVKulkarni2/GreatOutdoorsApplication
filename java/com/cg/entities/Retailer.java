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
	private String retailerId;
	private String retailerName;
	private String password;
	private String role;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Autowired
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address retailerAddress;

	public Retailer() {
	}



	public Retailer(String retailerId, String retailerName, String password, String role, Address retailerAddress) {
		super();
		this.retailerId = retailerId;
		this.retailerName = retailerName;
		this.password = password;
		this.role = role;
		this.retailerAddress = retailerAddress;
	}



	public Retailer(String string, String string2) {
		this.retailerId=string;
		this.role=string2;
	}

	@Override
	public String toString() {
		return "Retailer [retailerId=" + retailerId + ", retailerName=" + retailerName + ", password=" + password
				+ ", role=" + role + ", retailerAddress=" + retailerAddress + "]";
	}

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
