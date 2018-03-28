package com.sachin.DAOimple;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sachin.dao.SupplierDAO;
import com.sachin.domain.Supplier;

@Repository("supplierDAO")
@Transactional
public class SupplierDAOimple implements SupplierDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private Supplier supplier;
	public SupplierDAOimple(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	public SupplierDAOimple()
	{
		
	}

	private Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	public boolean save(Supplier supplier) {
		// TODO Auto-generated method stub
		try {
			getSession().save(supplier);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean saveOrUpdate(Supplier category) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(category);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		return false;
		}
	}
	public Supplier get(String sid) {
		return sessionFactory.getCurrentSession().get(Supplier.class, sid);
	}
	public boolean delete(String sid) {
		try {
			supplier = get(sid);
			if (supplier == null) {
				return false;
			}

			sessionFactory.getCurrentSession().delete(supplier);

			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		return false;
	}
	}
	public List<Supplier> list() {
		return	sessionFactory.getCurrentSession().createQuery("from Supplier").list();
		
	}

}
