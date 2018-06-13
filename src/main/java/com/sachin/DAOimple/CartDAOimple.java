package com.sachin.DAOimple;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sachin.dao.CartDAO;
import com.sachin.domain.Cart;
import com.sachin.domain.Product;

@Repository("cartDAO")
@Transactional
public class CartDAOimple implements CartDAO{
	private  final Logger logger = LoggerFactory.getLogger(CartDAOimple.class);
	
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
		logger.debug("starting of save cart method");
		try {
			getSession().save(cart);
			logger.debug("ending of save cart method");
			return true;
		} catch (Exception e) {
			logger.error("cart not saved due to"+e.getMessage());
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean update(Cart cart) {
		logger.debug("starting of saveor update cart method");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(cart);
			logger.debug("ending of saveor update cart method");
			return true;
		} catch (HibernateException e) {
			logger.error("cart not saved or update due to"+e.getMessage());
			e.printStackTrace();
		
		return false;
		}
	}

	public boolean emptyCart(String useremail) {
		logger.debug("starting of empty cart method");
		String hql="delete from Cart where useremail='"+useremail+"'";
		sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
		logger.debug("ending of empty cart method");
		return true;
	}

	public List<Cart> list(String useremail) {
		logger.debug("starting of get list of cart method");
		return	sessionFactory.getCurrentSession().
				createCriteria(Cart.class).add(Restrictions.eq("useremail", useremail)).list();
	}

	public boolean deleteCart(int cartid) {
		logger.debug("starting of delete cart method");
		try {
			sessionFactory.getCurrentSession().delete(getCartById(cartid));
			logger.debug("ending of delete cart method");
			return true;
		} catch (HibernateException e) {
			logger.error("cart not deleted due to"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public Cart getCartById(int cartid) {
		logger.debug("starting of get cart by id method");
		return sessionFactory.getCurrentSession().get(Cart.class, cartid);
	}
public Long total(String useremail)
{

	String hql="select sum(quantity*productprice) as total from Cart where useremail='"+useremail+"'";
	org.hibernate.query.Query query=sessionFactory.getCurrentSession().createQuery(hql);
	return (Long) query.uniqueResult();
	
}

public boolean reduceQuan(int cartid) {
	try {
		Cart cart = getCartById(cartid);
		cart.setQuantity(cart.getQuantity() - 1);
		sessionFactory.getCurrentSession().update(cart);
		return true;
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	
}

public boolean increaseQuan(int cartid) {
	try {
		Cart cart = getCartById(cartid);
		cart.setQuantity(cart.getQuantity() + 1);
		sessionFactory.getCurrentSession().update(cart);
		return true;
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
}
public int getCartQuantity(int cartid) {
	//String hql="select cartquantity from Cart where cartid="+cartid;
	try {
		int qty = (Integer) sessionFactory.getCurrentSession().createSQLQuery("select quantity from Cart where cartid="+cartid).uniqueResult();
		System.out.println(qty);
		return qty;
	} catch (HibernateException e) {
		e.printStackTrace();
		return 0;
	}
}



}
