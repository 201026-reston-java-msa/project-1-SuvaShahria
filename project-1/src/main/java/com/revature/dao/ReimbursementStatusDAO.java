package com.revature.dao;

import org.hibernate.Session;
import com.revature.model.*;
import com.revature.util.*;

	public class ReimbursementStatusDAO   {

	
	public ReimbursementStatus getRStatusById(int id) {
		Session sesh = HibernateUtil.getSession();
		
		ReimbursementStatus rs = sesh.get(ReimbursementStatus.class, id);
		
		return rs;
	}
}