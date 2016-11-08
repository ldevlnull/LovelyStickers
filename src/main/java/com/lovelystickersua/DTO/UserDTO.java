package com.lovelystickersua.DTO;

public class UserDTO {

	private String username;
	private String email;
	private String pathImage;
	private String activateLink;
	public UserDTO(String username, String email, String pathImage, String activateLink) {
		super();
		this.username = username;
		this.email = email;
		this.pathImage = pathImage;
		this.activateLink = activateLink;
	}
	public UserDTO() {}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPathImage() {
		return pathImage;
	}
	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}
	public String getActivateLink() {
		return activateLink;
	}
	public void setActivateLink(String activateLink) {
		this.activateLink = activateLink;
	}
}
