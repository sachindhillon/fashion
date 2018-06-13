package com.sachin.DAOimple;

import java.util.Date;
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

import com.sachin.dao.OrderDetailsDAO;
import com.sachin.domain.OrderDetails;

@Repository("orderDetailsDAO")
@Transactional
public class OrderDetailsDAOimple implements OrderDetailsDAO{
	private static final Logger logger = LoggerFactory.getLogger(OrderDetailsDAOimple.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public OrderDetailsDAOimple(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	public OrderDetailsDAOimple()
	{
		
	}
	private Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	

	public boolean save(OrderDetails orderDetails) {
		logger.debug("starting of save orderDetails method");
		try {
			orderDetails.setOrderdate(new Date(System.currentTimeMillis()));
			getSession().save(orderDetails);
			logger.debug("ending of save orderDetails method");
			return true;
		} catch (Exception e) {
			logger.error("orderDetails not saved due to"+e.getMessage());
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean saveOrUpdate(OrderDetails orderDetails) {
		logger.debug("starting of saveor update orderDetails method");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(orderDetails);
			logger.debug("ending of saveor update orderDetails method");
			return true;
		} catch (HibernateException e) {
			logger.error("orderDetails not saved or update due to"+e.getMessage());
			e.printStackTrace();
		
		return false;
		}
	}


	

	public List<OrderDetails> list(String customeremail) {
		logger.debug("starting of get list of orderDetails method");
		return	sessionFactory.getCurrentSession().
				createCriteria(OrderDetails.class).add(Restrictions.eq("customeremail", customeremail)).list();
	}

	public boolean deleteOrderDetails(int orderid) {
		logger.debug("starting of delete orderDetails method");
		try {
			sessionFactory.getCurrentSession().delete(getOrderDetailsById(orderid));
			logger.debug("ending of delete orderDetails method");
			return true;
		} catch (HibernateException e) {
			logger.error("orderDetails not deleted due to"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public OrderDetails getOrderDetailsById(int orderid) {
		logger.debug("starting of get orderDetails by id method");
		return sessionFactory.getCurrentSession().get(OrderDetails.class, orderid);
	}

	public OrderDetails getOrderDetails(String loginid) {
		
		return sessionFactory.getCurrentSession().get(OrderDetails.class, loginid);
	}
	
	public int getOrderID(int token) {				
		int t = (Integer) sessionFactory.getCurrentSession().createSQLQuery("select orderid from OrderDetails where token="+token).uniqueResult();
		return t;
	}

}
