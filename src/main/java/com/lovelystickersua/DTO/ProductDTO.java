package com.lovelystickersua.DTO;

public class ProductDTO {

	private long id;
	private String name;
	private String price;
	private String iconPath;
	private String description;

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
	public ProductDTO(String name, String price, String iconPath, long id, String description) {
		this.name = name;
		this.price = price;
		this.iconPath = iconPath;
		this.id = id;
		this.description = description;
	}
	public ProductDTO() {
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}