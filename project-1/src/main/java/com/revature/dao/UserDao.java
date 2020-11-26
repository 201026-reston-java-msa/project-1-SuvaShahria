package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.revature.model.User;
import com.revature.util.*;
public class UserDAO{
	
	private static final Logger log = Logger.getLogger(UserDAO.class);
	
	public UserDAO() {
		super();
	}

	public boolean addUser(User u) {
		Session ses = HibernateUtil.getSession();
		
		try {
			ses.save(u);
			log.info("user added");
			return true;
		} catch (HibernateException e) {
			log.error(e);
			return false;
		} finally {
			HibernateUtil.closeSes();
		}
	}

	public boolean updateUser(User u) {
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = ses.beginTransaction();
		
		try {
			ses.merge(u);
			tx.commit();
			log.info("user updated");
			return true;
		} catch (HibernateException e) {
			log.error(e);
			tx.rollback();
			return false;
		} finally {
			HibernateUtil.closeSes();
		}
	}
	
	public List<User> getAllUsers() {
		Session ses = HibernateUtil.getSession();
		
		List<User> list = ses.createQuery("FROM User").list();
	//	log.info("list retrieved");
		return list;
	}

	public User getUserById(int id) {
		Session ses = HibernateUtil.getSession();
		
		User u = ses.get(User.class, id);
		//log.info("user by id");
		return u;
	}

	public User getUserByUsername(String username) {
		Session ses = HibernateUtil.getSession();
		
		List<User> list = ses.createQuery("FROM User WHERE userName = '"+username+"'", User.class).list();
		User u = list.get(0);
		
		return u;
	}
	
}


