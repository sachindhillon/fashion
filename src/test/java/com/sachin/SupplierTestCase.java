package com.sachin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sachin.dao.SupplierDAO;
import com.sachin.domain.Supplier;

public class SupplierTestCase {

	@Autowired
	private static AnnotationConfigApplicationContext context;
	@Autowired
	private static SupplierDAO supplierDAO;
	@Autowired
	private static Supplier supplier;
	
	@BeforeClass
	public static void initialize()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com.sachin");
		context.refresh();
		
		supplier = (Supplier)context.getBean("supplier");
		supplierDAO = (SupplierDAO)context.getBean("supplierDAO");
		
	}
    @Test
	public void supplierTestCase()
	{
		supplier.setSid("123");
		supplier.setSname("sachin");
		supplier.setSaddress("karnal");
		
	  boolean flag	= supplierDAO.save(supplier);
	  
	  assertEquals("supplierTestCase " , true,flag );
		
	}
    @Test
	public void updateSupplierTestCase()
	{
		supplier.setSid("s001");
		supplier.setSname("jaskaran");
		supplier.setSaddress("hidar");
		boolean status = supplierDAO.saveOrUpdate(supplier);
		assertEquals("update test case", true,status );
	}
	
	@Test
	public void getSupplierSuccessTestCase()
	{
		
	supplier= supplierDAO.get("s001");
	
	assertNotNull("get supplier test case", supplier);
	}
	
	@Test
	public void getSupplierFailureTestCase()
	{
		
	supplier= supplierDAO.get("s0002");
	
	assertNull("get user test case", supplier);
	}
	
	@Test
	public void deleteSupplierSuccessTestCase()
	{
	boolean status =	supplierDAO.delete("s001");
	assertEquals("delete user succss test case" , true, status);
	
	}
	
	@Test
	public void deleteSupplierFailureTestCase()
	{
	boolean status =	supplierDAO.delete("s00022");
	assertEquals("delete user failure test case" , false, status);
	
	}
	
	
	@Test
	public void getAllSuppliersTestCase()
	{
	List<Supplier>	suppliers = supplierDAO.list();
	
	assertEquals("get all usres " , 3, suppliers.size() );
	
	}
	

}
