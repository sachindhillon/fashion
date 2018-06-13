package com.sachin.dao;

import java.util.List;

import com.sachin.domain.Cart;
import com.sachin.domain.Product;

public interface CartDAO {
	
	
	public boolean save(Cart cart);
	public Cart getCartById(int cartid);
	public boolean update(Cart cart);
	public int getCartQuantity(int cartid);
	public boolean emptyCart(String useremail);
	public boolean deleteCart(int cartid);
	public List<Cart> list(String useremail);
	public Long total(String useremail);
	public boolean reduceQuan(int cartid);
	public boolean increaseQuan(int cartid);

}
