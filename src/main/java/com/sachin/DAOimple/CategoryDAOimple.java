package com.sachin.DAOimple;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sachin.dao.CategoryDAO;
import com.sachin.domain.Category;
import com.sachin.domain.User;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOimple implements CategoryDAO{
	 Logger logger = LoggerFactory.getLogger(CategoryDAOimple.class);
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
		logger.debug("starting of save category method");
		try {
			logger.info("going to delete category"+category);
			getSession().save(category);
			logger.debug("ending of save category method");
			return true;
		} catch (Exception e) {
			logger.error("category not saved due to"+e.getMessage());
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean saveOrUpdate(Category category) {
		logger.debug("starting of saveOrupdate category method");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(category);
			logger.debug("ending of saveOrupdate category method");
			return true;
		} catch (HibernateException e) {
			logger.error("category not savedOrUpdate due to"+e.getMessage());
			e.printStackTrace();
		
		return false;
		}
	}

	public Category get(String cid) {
		logger.debug("starting of get category method");
		return sessionFactory.getCurrentSession().get(Category.class, cid);
	}

	public boolean delete(String cid) {
		logger.debug("starting of delete category method");
		try {
			category = get(cid);
			if (category == null) {
				return false;
			}
			logger.info("going to delete category"+category);
			sessionFactory.getCurrentSession().delete(category);
			logger.debug("ending of delete category method");
			return true;
		} catch (HibernateException e) {
			logger.error("category not deleted due to"+e.getMessage());
			e.printStackTrace();
		return false;
	}
	}

	public List<Category> list() {
		logger.debug("starting of list of category method");
		
		return (List<Category>) 
		          sessionFactory.getCurrentSession()
				.createCriteria(Category.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

}
