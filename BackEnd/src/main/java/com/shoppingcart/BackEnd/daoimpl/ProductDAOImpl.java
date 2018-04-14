package com.shoppingcart.BackEnd.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingcart.BackEnd.dao.ProductDAO;
import com.shoppingcart.BackEnd.domain.Product;

@Transactional
@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public boolean save(Product product) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(product);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Product product) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	public Product get(String id) {
		// TODO Auto-generated method stub

		return sessionFactory.getCurrentSession().load(Product.class, id);

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


	public List<Product> list() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Product").getResultList();
	}

	
	public Product getProductById(String id) {
		return (Product) sessionFactory.getCurrentSession().createQuery("from Product where id= '"+id+"' ").uniqueResult();
	}

}
