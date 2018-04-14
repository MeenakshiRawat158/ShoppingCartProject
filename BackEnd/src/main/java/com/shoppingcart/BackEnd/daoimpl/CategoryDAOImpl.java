package com.shoppingcart.BackEnd.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingcart.BackEnd.dao.CategoryDAO;
import com.shoppingcart.BackEnd.domain.Category;
import com.shoppingcart.BackEnd.domain.User;

@Transactional
@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public boolean save(Category category) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(category);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Category category) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	public Category get(String id) {
		// TODO Auto-generated method stub

		return sessionFactory.getCurrentSession().load(Category.class, id);

	}

	public boolean delete(String id) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().delete(get(id));
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}


	public List<Category> list() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Category").list();
	}

	public Category getCategoryById(String id) {
		return  (Category) sessionFactory.getCurrentSession().createQuery("from Category where id = '"+id + "'").uniqueResult();
	}

	public Category getCategoryByName(String name) {
		 return  (Category) sessionFactory.getCurrentSession().createQuery("from Category where name = '"+name + "'").uniqueResult();
	}
}
