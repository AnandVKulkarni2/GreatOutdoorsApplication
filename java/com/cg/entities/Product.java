package com.cg.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	private int prodId;
	private String prodName;
	private int quantity;
	private double price;
	private String prodCategory;
	public Product() {
		super();
	}
	public Product(int prodId, String prodName, int quantity, double price, String prodCategory) {
		super();
		this.prodId = prodId;
		this.prodName = prodName;
		this.quantity = quantity;
		this.price = price;
		this.prodCategory = prodCategory;
	}
	
	//Copy constructor
//	public Product(Product prod) {
//		this.prodId = prod.getProdId();
//		this.prodName = prod.getProdName();
//		this.quantity = prod.getQuantity();
//		this.price = prod.getPrice();
//		this.prodCategory=prod.getProdCategory();
//	}
	@Override
	public String toString() {
		return "Product [prodId=" + prodId + ", prodName=" + prodName + ", quantity=" + quantity + ", price=" + price
				+ ", prodCategory=" + prodCategory + "]";
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProdCategory() {
		return prodCategory;
	}
	public void setProdCategory(String prodCategory) {
		this.prodCategory = prodCategory;
	}
	
	
}


