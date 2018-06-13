package com.sachin;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sachin.dao.OrderDetailsDAO;
import com.sachin.domain.OrderDetails;


public class OrderDetailsTestCase {

	@Autowired
	private static AnnotationConfigApplicationContext context;
	@Autowired
	private static OrderDetails orderDetails;
	@Autowired
	private static OrderDetailsDAO orderDetailsDAO;

	@BeforeClass
	public static void initialize()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com.sachin");
		context.refresh();
		
		orderDetails = (OrderDetails)context.getBean("orderDetails");
		orderDetailsDAO = (OrderDetailsDAO)context.getBean("orderDetailsDAO");
		
	}
	/*@Test
	public void orderDetailsTestCase()
	{
		orderDetails.setCustomer_name("sachin");
		orderDetails.setProduct_name("shoes");
		orderDetails.setPrice(5000l);
		orderDetails.setQuantity(1);
		orderDetails.setMobile_no("3544474158");
		orderDetails.setShipping_Address("100021gffdf");
		
	
	
	  boolean flag	= orderDetailsDAO.save(orderDetails);
  	  assertEquals("orderDetailsTestCase " , true,flag );
		
	}
	@Test
	public void updateOrderDetailsTestCase()
	{
		orderDetails.setCustomer_name("sachin");
		orderDetails.setProduct_name("shoes");
		orderDetails.setPrice(5000l);
		orderDetails.setQuantity(1);
		
	
		boolean status = orderDetailsDAO.saveOrUpdate(orderDetails);
		assertEquals("update test case", true,status );
	}*/
	
	@Test
	public void getOrderDetailsSuccessTestCase()
	{
		
	List<OrderDetails>orderDetails= orderDetailsDAO.list("sachin@gmail.com");
	
	assertNotNull("get orderDetails test case", orderDetails);
	}
	
	/*@Test
	public void getAllOrderDetailsTestCase()
	{
	List<OrderDetails>	orderDetailss = orderDetailsDAO.list("sachin@gmail.com");
	
	assertEquals("get all usres " , 3, orderDetailss.size() );
	
	}

	
	@Test
	public void getOrderDetailsFailureTestCase()
	{
		
	orderDetails= orderDetailsDAO.get("s0002");
	
	assertNull("get user test case", orderDetails);
	}
	
	@Test
	public void deleteOrderDetailsSuccessTestCase()
	{
	boolean status =orderDetailsDAO.delete("sachin@gmail.com");
	assertEquals("delete orderDetails succss test case" , true, status);
	
	}
	
	@Test
	public void deleteOrderDetailsFailureTestCase()
	{
	boolean status =	orderDetailsDAO.delete("s00022");
	assertEquals("delete user failure test case" , false, status);
	
	}*/
	

}
