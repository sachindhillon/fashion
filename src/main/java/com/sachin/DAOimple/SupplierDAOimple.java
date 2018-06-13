package com.sachin.DAOimple;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sachin.dao.SupplierDAO;
import com.sachin.domain.Supplier;

@Repository("supplierDAO")
@Transactional
public class SupplierDAOimple implements SupplierDAO {
	private static final Logger logger = LoggerFactory.getLogger(SupplierDAOimple.class);
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
		logger.debug("starting of save supplier method");
		try {
			logger.info("going to save supplier"+supplier);
			getSession().save(supplier);
			logger.debug("ending of save supplier method");
			return true;
		} catch (Exception e) {
			logger.error("supplier not saved due to"+e.getMessage());
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean saveOrUpdate(Supplier category) {
		logger.debug("starting of saveOrUpdate supplier method");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(category);
			logger.debug("ending of saveOrUpdate supplier method");
			return true;
		} catch (HibernateException e) {
			logger.error("supplier not savedOrUpdated due to"+e.getMessage());
			e.printStackTrace();
		
		return false;
		}
	}
	public Supplier get(String sid) {
		logger.debug("starting of get supplier method");
		return sessionFactory.getCurrentSession().get(Supplier.class, sid);
	}
	public boolean delete(String sid) {
		logger.debug("starting of delete supplier method");
		try {
			supplier = get(sid);
			logger.info("going to delete supplier"+supplier);
			if (supplier == null) {
				return false;
			}
			
			sessionFactory.getCurrentSession().delete(supplier);
			logger.debug("ending of delete supplier method");
			return true;
		} catch (HibernateException e) {
			logger.error("supplier not deleted due to"+e.getMessage());
			e.printStackTrace();
		return false;
	}
	}
	public List<Supplier> list() {
		logger.debug("starting of list of suppliers method");
		return	sessionFactory.getCurrentSession().createQuery("from Supplier").list();
		
	}

}
