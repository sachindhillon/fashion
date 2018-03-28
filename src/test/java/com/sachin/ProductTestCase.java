package com.sachin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sachin.dao.ProductDAO;
import com.sachin.domain.Product;

public class ProductTestCase {
	@Autowired
	private static AnnotationConfigApplicationContext context;
	@Autowired
	private static Product product;
	@Autowired
	private static ProductDAO productDAO;

	@BeforeClass
	public static void initialize()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com.sachin");
		context.refresh();
		
		product = (Product)context.getBean("product");
		productDAO = (ProductDAO)context.getBean("productDAO");
		
	}
	@Test
	public void productTestCase()
	{
		product.setPid("456");
		product.setPname("sachin");
		product.setPdescription("don't know");
	
	
	  boolean flag	= productDAO.save(product);
  	  assertEquals("productTestCase " , true,flag );
		
	}
	@Test
	public void updateProductTestCase()
	{
		product.setPid("s001");
		product.setPname("jaskaran");
		product.setPdescription("don't know");
		boolean status = productDAO.saveOrUpdate(product);
		assertEquals("update test case", true,status );
	}
	
	@Test
	public void getProductSuccessTestCase()
	{
		
	product= productDAO.get("s001");
	
	assertNotNull("get product test case", product);
	}
	
	@Test
	public void getProductFailureTestCase()
	{
		
	product= productDAO.get("s0002");
	
	assertNull("get user test case", product);
	}
	
	@Test
	public void deleteProductSuccessTestCase()
	{
	boolean status =	productDAO.delete("s001");
	assertEquals("delete user succss test case" , true, status);
	
	}
	
	@Test
	public void deleteProductFailureTestCase()
	{
	boolean status =	productDAO.delete("s00022");
	assertEquals("delete user failure test case" , false, status);
	
	}
	
	
	@Test
	public void getAllProductsTestCase()
	{
	List<Product>	products = productDAO.list();
	
	assertEquals("get all usres " , 3, products.size() );
	
	}

}
