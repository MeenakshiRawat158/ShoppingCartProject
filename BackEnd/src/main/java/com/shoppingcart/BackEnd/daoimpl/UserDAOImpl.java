package com.shoppingcart.BackEnd.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingcart.BackEnd.dao.UserDAO;
import com.shoppingcart.BackEnd.domain.User;

@Transactional
@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public boolean save(User user) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(User user) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	public User get(String email) {
		// TODO Auto-generated method stub

		return sessionFactory.getCurrentSession().load(User.class, email);

	}

	public boolean delete(String email) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().delete(get(email));
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	public User validate(String email, String password) {
		// TODO Auto-generated method stub
		return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
				.add(Restrictions.eqOrIsNull("email", email)).add(Restrictions.eq("password", password)).uniqueResult();
	}

	public List<User> list() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from user").getResultList();
	}

}
