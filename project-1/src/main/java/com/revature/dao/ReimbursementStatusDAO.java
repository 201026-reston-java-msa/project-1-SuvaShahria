package com.revature.dao;

import org.hibernate.Session;

import com.revature.model.ReimbursementStatus;
import com.revature.util.HibernateUtil;

	public class ReimbursementStatusDAO {

	public ReimbursementStatus getStatusById(int id) {
		Session ses = HibernateUtil.getSession();
		
		ReimbursementStatus r = ses.get(ReimbursementStatus.class, id);
		return r;
	}
}