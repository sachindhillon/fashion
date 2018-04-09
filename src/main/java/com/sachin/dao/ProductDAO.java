package com.sachin.dao;

import java.util.List;

import com.sachin.domain.Product;

public interface ProductDAO
{
	public boolean save(Product product);
	public boolean saveOrUpdate(Product product);
	public   Product     get(String pid);
	public   boolean    delete(String pid);
	public List<Product> list();
	public List<Product> search(String searchItem);

}
