package com.sachin.dao;

import java.util.List;

import com.sachin.domain.User;

public interface UserDAO 
{
	
	public User getUser(String emailid);
	public boolean save(User user);
	public boolean saveOrUpdate(User user);
	public   User     get(String emailid);
	public   boolean    delete(String emailid);
   	public User validate(String emailid, String password);
    public List<User> list();
	
}
