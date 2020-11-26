package com.revature.dao;

import org.hibernate.Session;

import com.revature.model.ReimbursementType;
import com.revature.util.HibernateUtil;

public class ReimbursementTypeDAO {

	public ReimbursementType getTypeById(int id) {
		Session ses = HibernateUtil.getSession();
		
		ReimbursementType r = ses.get(ReimbursementType.class, id);
		return r;
	}	
}
