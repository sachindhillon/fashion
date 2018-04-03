package com.sachin.dao;

import java.util.List;

import com.sachin.domain.Cart;

public interface CartDAO {
	
	
	public boolean save(Cart cart);
	public boolean saveOrUpdate(Cart cart);
	public Cart get(String emailid);
	public boolean delete(String emailid);
	public List<Cart> list(String emailid);

}
