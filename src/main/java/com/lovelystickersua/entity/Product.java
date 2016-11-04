package com.lovelystickersua.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long ID;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String price;
	@ManyToOne(fetch=FetchType.LAZY)
	private PurchaseOrder purchaseOrder;
	private String productIconPath;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;

	@Override
	public String toString() {
		return "\nТовар: \nИмя товара: " + name + "\nЦена: "+ price + "$\nЗаказчик: " + user;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public String getProductIconPath() {
		return productIconPath;
	}

	public void setProductIconPath(String productIconPath) {
		this.productIconPath = productIconPath;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product(String name, String price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Product() {
		super();
	}
	
	
	
}
