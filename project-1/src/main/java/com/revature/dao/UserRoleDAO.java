package com.revature.dao;

import org.hibernate.Session;

import com.revature.model.*;
import com.revature.util.*;

public class UserRoleDAO {


	public UserRole getRoleById(int id) {
		Session sesh = HibernateUtil.getSession();
		
		UserRole ur = sesh.get(UserRole.class, id);
		
		return ur;
	}
}