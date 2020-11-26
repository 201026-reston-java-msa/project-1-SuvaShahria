package com.revature.dao;

import org.hibernate.Session;

import com.revature.model.UserRole;
import com.revature.util.HibernateUtil;

public class UserRoleDAO{

	public UserRole getRoleById(int id) {
		Session ses = HibernateUtil.getSession();
		
		UserRole ur = ses.get(UserRole.class, id);
		
		return ur;
	}
}