package com.lovelystickersua.DTO;

public class ProductDTO {

	private String id;
	private String name;
	private String price;
	private String iconPath;
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
	public String getIconPath() {
		return iconPath;
	}
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	public ProductDTO(String name, String price, String iconPath, String id) {
		this.name = name;
		this.price = price;
		this.iconPath = iconPath;
		this.id = id;
	}
	public ProductDTO() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}