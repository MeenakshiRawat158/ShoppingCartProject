package com.shoppingcart.BackEnd.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingcart.BackEnd.dao.SupplierDAO;
import com.shoppingcart.BackEnd.domain.Supplier;

@Transactional
@Repository("supplierDAO")
public class SupplierDAOImpl implements SupplierDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public boolean save(Supplier supplier) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(supplier);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Supplier supplier) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(supplier);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	public Supplier get(String id) {
		// TODO Auto-generated method stub

		return sessionFactory.getCurrentSession().load(Supplier.class, id);

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

	public List<Supplier> list() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Supplier").getResultList();
	}

	public Supplier getSupplierById(String id) {
		return (Supplier) sessionFactory.getCurrentSession().createQuery("from Supplier where id = '" + id + "' ")
				.uniqueResult();
	}
}
