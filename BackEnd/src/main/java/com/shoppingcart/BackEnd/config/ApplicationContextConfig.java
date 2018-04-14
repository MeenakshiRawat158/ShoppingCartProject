package com.shoppingcart.BackEnd.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.shoppingcart.BackEnd.dao.CategoryDAO;
import com.shoppingcart.BackEnd.dao.ProductDAO;
import com.shoppingcart.BackEnd.dao.SupplierDAO;
import com.shoppingcart.BackEnd.dao.UserDAO;
import com.shoppingcart.BackEnd.daoimpl.CategoryDAOImpl;
import com.shoppingcart.BackEnd.daoimpl.ProductDAOImpl;
import com.shoppingcart.BackEnd.daoimpl.SupplierDAOImpl;
import com.shoppingcart.BackEnd.daoimpl.UserDAOImpl;
import com.shoppingcart.BackEnd.domain.Category;
import com.shoppingcart.BackEnd.domain.MyCart;
import com.shoppingcart.BackEnd.domain.Product;
import com.shoppingcart.BackEnd.domain.Supplier;
import com.shoppingcart.BackEnd.domain.User;

@Configuration
@ComponentScan("com.shoppingcart.BackEnd")
@EnableTransactionManagement
public class ApplicationContextConfig {

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:~/test1;AUTO_SERVER=TRUE");
		dataSource.setUsername("sa");
		dataSource.setPassword("");

		return dataSource;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "false");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("current_session_context_class", "thread");
		return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClasses(User.class);
		sessionBuilder.addAnnotatedClasses(Product.class);
		sessionBuilder.addAnnotatedClasses(Supplier.class);
		sessionBuilder.addAnnotatedClasses(Category.class);
		sessionBuilder.addAnnotatedClasses(MyCart.class);
		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	@Autowired
	@Bean(name = "userDAO")
	public UserDAO getUserDAO(SessionFactory sessionFactory) {
		return new UserDAOImpl();
	}

	@Autowired
	@Bean(name = "categoryDAO")
	public CategoryDAO getCategoryDAO(SessionFactory sessionFactory) {
		return new CategoryDAOImpl();
	}

	@Autowired
	@Bean(name = "productDAO")
	public ProductDAO getProductDAO(SessionFactory sessionFactory) {
		return new ProductDAOImpl();
	}

	@Autowired
	@Bean(name = "supplierDAO")
	public SupplierDAO getSupplierDAO(SessionFactory sessionFactory) {
		return new SupplierDAOImpl();
	}

	@Autowired
	@Bean(name = "user")
	public User getUser() {
		return new User();
	}

	@Autowired
	@Bean(name = "product")
	public Product getProduct() {
		return new Product();
	}

	@Autowired
	@Bean(name = "supplier")
	public Supplier getSupplier() {
		return new Supplier();
	}

	@Autowired
	@Bean(name = "category")
	public Category getCategory() {
		return new Category();
	}
	
	@Autowired
	@Bean(name = "cart")
	public MyCart getCart() {
		return new MyCart();
	}
}
