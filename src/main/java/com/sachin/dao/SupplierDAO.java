package com.sachin.dao;

import java.util.List;

import com.sachin.domain.Supplier;

public interface SupplierDAO {
	public boolean save(Supplier supplier);
	public boolean saveOrUpdate(Supplier supplier);
	public   Supplier     get(String sid);
	public   boolean    delete(String sid);
	public List<Supplier> list();

}
