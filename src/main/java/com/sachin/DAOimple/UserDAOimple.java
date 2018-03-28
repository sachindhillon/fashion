package com.sachin.DAOimple;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sachin.dao.UserDAO;
import com.sachin.domain.User;

@Repository("userDAO")
@Transactional
public class UserDAOimple implements UserDAO{

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
		
		try {
			user.setRegistrationdate(new Date(System.currentTimeMillis()));
			user.setRole('c');
			getSession().save(user);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public User getUser(String emailid) {
		
		return sessionFactory.getCurrentSession().get(User.class, emailid);
	}

	public boolean saveOrUpdate(User user) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(user);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	public User validate(String emailid, String password) {
		
		User u = (User) sessionFactory.getCurrentSession().
				createCriteria(User.class).
				add(Restrictions.eq("emailid", emailid))
				.add(Restrictions.eq("password",password)).uniqueResult();
		if(u==null)
		{
			return null;
		}
		else
		{
			return u;
		}
	}

	public List<User> list() {
		
		return	sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	public boolean delete(String emailid) {
		// TODO Auto-generated method stub
		try {
			user = get(emailid);
			if (user == null) {
				return false;
			}

			sessionFactory.getCurrentSession().delete(user);

			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		return false;
	}
	}

	public User get(String emailid) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(User.class, emailid);
	}

	

}
