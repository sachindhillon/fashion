package com.sachin.DAOimple;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sachin.dao.ProductDAO;
import com.sachin.domain.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOimple implements ProductDAO {
	
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
		// TODO Auto-generated method stub
		try {
			getSession().save(product);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
	}
	public boolean saveOrUpdate(Product category) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(category);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		return false;
		}
	}
	public Product get(String pid) {
		return sessionFactory.getCurrentSession().get(Product.class, pid);
	}
	public boolean delete(String pid) {
		try {
			product= get(pid);
			if (product == null) {
				return false;
			}

			sessionFactory.getCurrentSession().delete(product);

			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		return false;
	}
	}
	public List<Product> list() {
		return	sessionFactory.getCurrentSession().createQuery("from Product").list();
		
	}


}
