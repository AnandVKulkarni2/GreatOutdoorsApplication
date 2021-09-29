package com.cg.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {
	
	@Id
	private long phoneNo;
	private int pinCode;
	private String city;
	private String street;
	private String state;
	public Address() {
	}
	public Address(int pinCode, String city, String street, String state, long phoneNo) {
		super();
		this.pinCode = pinCode;
		this.city = city;
		this.street = street;
		this.state = state;
		this.phoneNo=phoneNo;
	}
	
	
	@Override
	public String toString() {
		return "Address [phoneNo=" + phoneNo + ", pinCode=" + pinCode + ", city=" + city + ", street=" + street
				+ ", state=" + state + "]";
	}
	public int getPinCode() {
		return pinCode;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
