package com.sachin.dao;

import java.util.List;

import com.sachin.domain.Category;
import com.sachin.domain.User;

public interface CategoryDAO {
	public boolean save(Category category);
	public boolean saveOrUpdate(Category category);
	public Category get(String cid);
	public boolean delete(String cid);
	public List<Category> list();
	

}
