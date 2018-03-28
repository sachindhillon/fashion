package com.sachin.DAOimple;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sachin.dao.CategoryDAO;
import com.sachin.domain.Category;
import com.sachin.domain.User;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOimple implements CategoryDAO{
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private Category category;
	
	public CategoryDAOimple(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	public CategoryDAOimple()
	{
		
	}
	private Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	public boolean save(Category category) {
		// TODO Auto-generated method stub
		try {
			getSession().save(category);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean saveOrUpdate(Category category) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(category);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		return false;
		}
	}

	public Category get(String cid) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(Category.class, cid);
	}

	public boolean delete(String cid) {
		try {
			category = get(cid);
			if (category == null) {
				return false;
			}

			sessionFactory.getCurrentSession().delete(category);

			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		return false;
	}
	}

	public List<Category> list() {
		// TODO Auto-generated method stub
		return	sessionFactory.getCurrentSession().createQuery("from Category").list();
	}

}
