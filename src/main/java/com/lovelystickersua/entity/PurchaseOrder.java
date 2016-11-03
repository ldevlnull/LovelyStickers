package com.lovelystickersua.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class PurchaseOrder {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long ID;
	private String offer_name;
    private Date offer_date;
    private String totalPrice; 
    @ManyToOne(fetch=FetchType.LAZY)
    private User user;
    @OneToMany(fetch=FetchType.LAZY, mappedBy="purchaseOrder")
    private List<Product> products;
    
    
    
	public PurchaseOrder(User user, List<Product> products) {
		this.user = user;
		this.products = products;
		this.offer_date = new Date(System.currentTimeMillis());
		this.offer_name = "offer"+user.getName()+":"+offer_date;
		double totalPriceTemp = 0;
		for(Product p : products){
			totalPriceTemp += Double.parseDouble(p.getPrice());			
		}
		this.totalPrice = ""+totalPriceTemp;
	}
	public PurchaseOrder() {
		
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getOffer_name() {
		return offer_name;
	}
	public void setOffer_name(String offer_name) {
		this.offer_name = offer_name;
	}
	public Date getOffer_date() {
		return offer_date;
	}
	public void setOffer_date(Date offer_date) {
		this.offer_date = offer_date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "Заказ: имя заказа:" + offer_name + "; дата заказа: " + offer_date + "; суммарная цена за заказ:" + totalPrice;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	
	
    
    
    
}
