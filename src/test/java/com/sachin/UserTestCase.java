package com.sachin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sachin.dao.UserDAO;
import com.sachin.domain.User;

public class UserTestCase {

	@Autowired
	private static AnnotationConfigApplicationContext context;
	@Autowired
	private static User user;
	@Autowired
	private static UserDAO userDAO;
	@BeforeClass
	public static void initialize()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com.sachin");
		context.refresh();
		
		user = (User)context.getBean("user");
		userDAO = (UserDAO)context.getBean("userDAO");
		
	}
	@Test
	public void saveUserTestCase()
	{
		
		user.setEmailid("shubham@gmail.com");
		user.setMobile("9034326101");
		user.setName("shubham");
		user.setPassword("shubham@123");
		
	  boolean flag	= userDAO.save(user);
	  
	  assertEquals("saveUserTestCase " , true,flag );
		
	}
	@Test
	public  void getTestEmailid() {
		user=userDAO.getUser("sachin");
		assertNotNull("get user test case",user);
	}
	@Test
	public void updateUserTestCase()
	{
		user.setEmailid("jaskaran1@gmail.com");
		user.setMobile("888888888");
		boolean status = userDAO.saveOrUpdate(user);
		assertEquals("update test case", true,status );
	}
	
	@Test
	public void getUserSuccessTestCase()
	{
		
	user= userDAO.get("jaskaran1@gmail.com");
	
	assertNotNull("get user test case", user);
	}
	
	@Test
	public void getUserFailureTestCase()
	{
		
	user= userDAO.get("jaya@gmail.com");
	
	assertNull("get user test case", user);
	}
	
	@Test
	public void deleteUserSuccessTestCase()
	{
	boolean status =	userDAO.delete("sachin");
	assertEquals("delete user succss test case" , true, status);
	
	}
	
	@Test
	public void deleteUserFailureTestCase()
	{
	boolean status =	userDAO.delete("arpith@gmail.com");
	assertEquals("delete user failure test case" , false, status);
	
	}
	
	
	@Test
	public void getAllUsersTestCase()
	{
	List<User>	users = userDAO.list();
	
	assertEquals("get all usres " , 3, users.size() );
	
	}
	@Test
	public void validateCredentailsTestCase()
	{
		
	User flag = userDAO.validate("sachin@gmail.com","sachin@123");
	assertEquals("Validate test case", null, flag );
	
	}
}
