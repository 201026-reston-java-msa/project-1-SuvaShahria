package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.User;
import com.revature.util.HibernateUtil;

public class UserDAO {
	
	public UserDAO() {
		super();
	}
	
	public boolean addUser(User u) {
		Session ses = HibernateUtil.getSession();
		
		try {
			ses.save(u);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSes();
		}
	}

	public boolean updateUser(User u) {
		Session ses = HibernateUtil.getSession();
		
		Transaction t = ses.beginTransaction();
		
		try {
			ses.merge(u);
			t.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			t.rollback();
			return false;
		} finally {
			HibernateUtil.closeSes();
		}
	}	
	
	public List<User> getAllUsers() {
		Session ses = HibernateUtil.getSession();
		
		List<User> list = ses.createQuery("FROM User").list();
		
		return list;
	}

	
	public User getUserById(int id) {
		Session ses = HibernateUtil.getSession();
		
		User u = ses.get(User.class, id);
		
		return u;
	}

	public User getUserByUsername(String username) {
		Session ses = HibernateUtil.getSession();
		
		List<User> list = ses.createQuery("FROM User WHERE userName = '"+username+"'", User.class).list();
		User u = list.get(0);
		
		return u;
	}

	

}


