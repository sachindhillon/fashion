package com.sachin;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sachin.dao.CartDAO;
import com.sachin.domain.Cart;

public class CartTestCase {

	@Autowired
	private static AnnotationConfigApplicationContext context;
	@Autowired
	private static Cart cart;
	@Autowired
	private static CartDAO cartDAO;

	@BeforeClass
	public static void initialize()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com.sachin");
		context.refresh();
		
		cart = (Cart)context.getBean("cart");
		cartDAO = (CartDAO)context.getBean("cartDAO");
		
	}
	@Test
	public void cartTestCase()
	{
		cart.setEmailid("sachin@gmail.com");
		cart.setProductid("2");
		cart.setProductName("laptop");
		cart.setPrice(50000);
		cart.setQuantity(1);
	
	
	  boolean flag	= cartDAO.save(cart);
  	  assertEquals("cartTestCase " , true,flag );
		
	}
	/*@Test
	public void updateCartTestCase()
	{
		cart.setEmailid("sachin@gmail.com");
		cart.setProductName("jaskaran");
		cart.setPrice(1000);
		cart.setQuantity(6);
	
		boolean status = cartDAO.saveOrUpdate(cart);
		assertEquals("update test case", true,status );
	}
	
	@Test
	public void getCartSuccessTestCase()
	{
		
	cart= cartDAO.get("sachin@gmail.com");
	
	assertNotNull("get cart test case", cart);
	}
	
	@Test
	public void getAllCartTestCase()
	{
	List<Cart>	carts = cartDAO.list("sachin@gmail.com");
	
	assertEquals("get all usres " , 3, carts.size() );
	
	}

	
	@Test
	public void getCartFailureTestCase()
	{
		
	cart= cartDAO.get("s0002");
	
	assertNull("get user test case", cart);
	}
	
	@Test
	public void deleteCartSuccessTestCase()
	{
	boolean status =cartDAO.delete("sachin@gmail.com");
	assertEquals("delete cart succss test case" , true, status);
	
	}
	
	@Test
	public void deleteCartFailureTestCase()
	{
	boolean status =	cartDAO.delete("s00022");
	assertEquals("delete user failure test case" , false, status);
	
	}
	*/
	
	
}
