package com.shoppingcart.BackEnd.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingcart.BackEnd.dao.CartDAO;
import com.shoppingcart.BackEnd.domain.MyCart;



@Repository("cartDAO")
@Transactional
public class CartDAOImpl implements CartDAO {

	@Autowired
	private SessionFactory sessionFactory;
	public List<MyCart> list(String user_id) {
		String status="N";
		String hql = "from MyCart where user_id ='"+user_id+"'  and status='"+status+"' ";
  return  sessionFactory.getCurrentSession().createQuery(hql).list();
  
		
	}

	public MyCart get(String id) {
	//return (MyCart) sessionFactory.getCurrentSession().get(MyCart.class,id);
	return (MyCart) sessionFactory.getCurrentSession().createQuery("from MyCart where id= '"+id+"' ").uniqueResult();
	}

	public boolean save(MyCart myCart) {
		try {
			sessionFactory.getCurrentSession().save(myCart);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public Double getTotalAmount(String id) {
		
		String hql = "select sum(price) from MyCart where user_id=" + "'" + id + "' " + "  and status = " + "'N'";
		

		return (Double) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
		
		
	}

	public boolean update(MyCart myCart) {
		try {
			sessionFactory.getCurrentSession().update(myCart);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(MyCart myCart) {
		try {
			sessionFactory.getCurrentSession().delete(myCart);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
