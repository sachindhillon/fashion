package com.sachin.DAOimple;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sachin.dao.CartDAO;
import com.sachin.domain.Cart;

@Repository("cartDAO")
@Transactional
public class CartDAOimple implements CartDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private Cart cart;
	
	public CartDAOimple(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	public CartDAOimple()
	{
		
	}
	private Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	public boolean save(Cart cart) {
		// TODO Auto-generated method stub
		try {
			getSession().save(cart);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean saveOrUpdate(Cart cart) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(cart);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		return false;
		}
	}

	public Cart get(String emailid) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(Cart.class, emailid);
	}

	public boolean delete(String emailid) {
		try {
			cart = get(emailid);
			if (cart == null) {
				return false;
			}

			sessionFactory.getCurrentSession().delete(cart);

			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		return false;
	}
	}

	public List<Cart> list(String emailid) {
		// TODO Auto-generated method stub
		return	sessionFactory.getCurrentSession().
				createCriteria(Cart.class).add(Restrictions.eq(emailid, emailid)).list();
	}


}
