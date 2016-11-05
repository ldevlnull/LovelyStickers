package com.lovelystickersua.entity;


import javax.persistence.*;

import java.util.*;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long ID;
	@Column(nullable=false/*, columnDefinition="NCHAR(128)"*/)
	private String name;
	@Column(nullable=false)
	private String price;
	@ManyToOne(fetch=FetchType.LAZY)
	private PurchaseOrder purchaseOrder;
	private String productIconPath;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "user_product",
			joinColumns = @JoinColumn(name = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users;

	@Override
	public String toString() {
		return "\n\tТовар: \n\t\tНазва товару: " + name + "\n\t\tВартість: "+ price + "$";
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
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
