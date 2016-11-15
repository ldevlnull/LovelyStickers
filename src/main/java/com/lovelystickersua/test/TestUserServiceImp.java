package com.lovelystickersua.test;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lovelystickersua.POJO.User;
import com.lovelystickersua.serviceImp.UserServiceImp;

public class TestUserServiceImp {

	private static UserServiceImp userServiceImp;
	
	@BeforeClass
	public static void init(){
		userServiceImp = new UserServiceImp();
	}
	@AfterClass
	public static void destroy(){
		userServiceImp = null;
	}
	@Test
	public void testSave(){
		User user = new User("baton", "baton123", "baton@bat.on");
		userServiceImp.save(user);
		User user2 = userServiceImp.findOne(user.getID());
		System.out.println(user2);
		Assert.assertEquals(user, user2);
	}
	
	
}
