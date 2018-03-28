package com.sachin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sachin.dao.CategoryDAO;
import com.sachin.domain.Category;

public class CategoryTestCase {
	@Autowired
	private static AnnotationConfigApplicationContext context;
	@Autowired
	private static Category category;
	@Autowired
	private static CategoryDAO categoryDAO;

	@BeforeClass
	public static void initialize()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com.sachin");
		context.refresh();
		
		category = (Category)context.getBean("category");
		categoryDAO = (CategoryDAO)context.getBean("categoryDAO");
		
	}
	@Test
	public void categoryTestCase()
	{
		category.setCid("1");
		category.setCname("mobile");
		category.setCdescription("don't know");
	
	
	  boolean flag	= categoryDAO.save(category);
  	  assertEquals("categoryTestCase " , true,flag );
		
	}
	@Test
	public void updateCategoryTestCase()
	{
		category.setCid("s001");
		category.setCname("jaskaran");
		boolean status = categoryDAO.saveOrUpdate(category);
		assertEquals("update test case", true,status );
	}
	
	@Test
	public void getCategorySuccessTestCase()
	{
		
	category= categoryDAO.get("s001");
	
	assertNotNull("get category test case", category);
	}
	
	@Test
	public void getCategoryFailureTestCase()
	{
		
	category= categoryDAO.get("s0002");
	
	assertNull("get user test case", category);
	}
	
	@Test
	public void deleteCategorySuccessTestCase()
	{
	boolean status =	categoryDAO.delete("s001");
	assertEquals("delete user succss test case" , true, status);
	
	}
	
	@Test
	public void deleteCategoryFailureTestCase()
	{
	boolean status =	categoryDAO.delete("s00022");
	assertEquals("delete user failure test case" , false, status);
	
	}
	
	
	@Test
	public void getAllCategoriesTestCase()
	{
	List<Category>	categories = categoryDAO.list();
	
	assertEquals("get all usres " , 3, categories.size() );
	
	}
}
