package com.sachin.dao;

import java.util.List;

import com.sachin.domain.OrderDetails;



public interface OrderDetailsDAO {
	public boolean save(OrderDetails orderDetails);
	public OrderDetails getOrderDetailsById(int orderDetailsid);
	public OrderDetails getOrderDetails(String loginid);
	public boolean saveOrUpdate(OrderDetails orderDetails);
	
	public boolean deleteOrderDetails(int orderDetailsid);
	public List<OrderDetails> list(String emailid);
	public int getOrderID(int token);
}
