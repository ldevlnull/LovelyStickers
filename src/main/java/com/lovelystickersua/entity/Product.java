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
	User user;

	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + ", users=" + user + "]";
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
