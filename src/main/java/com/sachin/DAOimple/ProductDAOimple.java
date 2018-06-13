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

import com.sachin.dao.ProductDAO;
import com.sachin.domain.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOimple implements ProductDAO {
	private static final Logger logger = LoggerFactory.getLogger(ProductDAOimple.class);
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private Product product;
	
	public ProductDAOimple(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	public ProductDAOimple()
	{
		
	}
	private Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	public boolean save(Product product) {
		logger.debug("starting of save product method");
		try {
			logger.info("going to save product"+product);
			getSession().save(product);
			logger.debug("ending of save product method");
			return true;
		} catch (Exception e) {
			logger.error("product not saved due to"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	
	}
	public boolean saveOrUpdate(Product product) {
		logger.debug("starting of saveOrupdate product method");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(product);
			logger.debug("ending of saveOrupdate product method");
			return true;
		} catch (HibernateException e) {
			logger.error("product not savedOrUpdated due to"+e.getMessage());
			e.printStackTrace();
		
		return false;
		}
	}
	public Product get(String pid) {
		logger.debug("starting of get product method");
		return sessionFactory.getCurrentSession().get(Product.class, pid);
	}
	public boolean delete(String pid) {
		logger.debug("starting of delete product method");
		try {
			product= get(pid);
			if (product == null) {
				return false;
			}
			logger.info("going to delete product"+product);
			sessionFactory.getCurrentSession().delete(product);
			logger.debug("ending of delete product method");
			return true;
		} catch (HibernateException e) {
			logger.error("product not deleted due to"+e.getMessage());
			e.printStackTrace();
		return false;
	}
	}
	public List<Product> list() {
		logger.debug("starting of list of  products method");
		return	sessionFactory.getCurrentSession().createQuery("from Product").list();
		
	}

	public List<Product> search(String searchItem) {
		logger.debug("starting of list of searched products method");
		String searchQuery="from Product where pname like '%"+searchItem+"'";
		return sessionFactory.getCurrentSession().createQuery(searchQuery).list();
	}


}
