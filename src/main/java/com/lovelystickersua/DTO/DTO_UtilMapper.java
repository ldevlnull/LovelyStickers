package com.lovelystickersua.DTO;

import java.util.ArrayList;
import java.util.List;

import com.lovelystickersua.entity.Product;
import com.lovelystickersua.entity.User;

public class DTO_UtilMapper {

	public static ProductDTO productDTO_ToProductDTO(Product product){return new ProductDTO(product.getName(), product.getPrice(), product.getIconPath(), ""+product.getID());}
	public static List<ProductDTO> listProductToProductDTO(List<Product> products){
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for(Product product : products){productDTOs.add(productDTO_ToProductDTO(product));}
		return productDTOs;
	}
	public static UserDTO user_ToUserDTO(User user){return new UserDTO(user.getName(),user.getEmail(),user.getPathImage(), user.getActivateLink());}
	public static List<UserDTO> listUserToUserDTO(List<User>users){
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for(User user : users){userDTOs.add(user_ToUserDTO(user));}
		return userDTOs;
	}
}	
