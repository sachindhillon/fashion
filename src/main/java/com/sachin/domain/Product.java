package com.sachin.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Product {

	@Id
	private String pid;
	private String pname;
	private String pdescription;
	private int price;
	private String categoryID;
	private String supplierID;

	public String getCategoryID() {
		return categoryID;
	}



	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}



	public String getSupplierID() {
		return supplierID;
	}



	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "categoryId", updatable = false, insertable = false, nullable = false)
	private Category category;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "supplierid", nullable = false, updatable = false, insertable = false)
	private Supplier supplier;


	
	public String getPid() {
		return pid;
	}



	public void setPid(String pid) {
		this.pid = pid;
	}



	public String getPname() {
		return pname;
	}



	public void setPname(String pname) {
		this.pname = pname;
	}



	public String getPdescription() {
		return pdescription;
	}



	public void setPdescription(String pdescription) {
		this.pdescription = pdescription;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public Supplier getSupplier() {
		return supplier;
	}



	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}



	
}
