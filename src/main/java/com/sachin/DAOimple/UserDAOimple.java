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

import com.sachin.dao.UserDAO;
import com.sachin.domain.User;

@Repository("userDAO")
@Transactional
public class UserDAOimple implements UserDAO{
	Logger logger = LoggerFactory.getLogger(UserDAOimple.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private User user;

	
	public UserDAOimple(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public UserDAOimple()
	{
		
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public boolean save(User user) {
		logger.debug("starting of save user method");
		try {
			user.setRegistrationdate(new Date(System.currentTimeMillis()));
			user.setRole('c');
			logger.info("going to save user"+user);
			getSession().save(user);
			logger.debug("ending of save user method");
			return true;
		} catch (Exception e) {
			logger.error("user not saved due to"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public User getUser(String emailid) {
		logger.debug("starting of get user method");
		
		return sessionFactory.getCurrentSession().get(User.class, emailid);
	}

	public boolean saveOrUpdate(User user) {
		logger.debug("starting of saveOrUpdate user method");
		try {
			logger.info("going to saveOrupdate user"+user);
			sessionFactory.getCurrentSession().saveOrUpdate(user);
			logger.debug("ending of saveOrupdate user method");
			return true;
		} catch (HibernateException e) {
			logger.error("user not savedOrUpdated due to"+e.getMessage());
			e.printStackTrace();
			return false;
		}
		
	}

	public User validate(String emailid, String password) {
		logger.debug("starting of validate user method");
		logger.info("going to validate user id"+emailid);
		User u = (User) sessionFactory.getCurrentSession().
				createCriteria(User.class).
				add(Restrictions.eq("emailid", emailid))
				.add(Restrictions.eq("password",password)).uniqueResult();
		if(u==null)
		{
			return null;
		}
		logger.debug("ending of validate user method");
			return u;
		
	}

	public List<User> list() {
		logger.debug("starting of list of user method");
		return	sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	public boolean delete(String emailid) {
		logger.debug("starting of delete user method");
		try {
			user = get(emailid);
			logger.info("going to delete user "+user);
			if (user == null) {
				return false;
			}

			sessionFactory.getCurrentSession().delete(user);
			logger.debug("ending of delete user method");
			return true;
		} catch (HibernateException e) {
			logger.error("user not deleted due to"+e.getMessage());
			e.printStackTrace();
		return false;
	}
	}

	public User get(String emailid) {
		logger.debug("starting of get user method");
		logger.info("going to get user with emailid"+emailid);
		return sessionFactory.getCurrentSession().get(User.class, emailid);
	}

	

}
