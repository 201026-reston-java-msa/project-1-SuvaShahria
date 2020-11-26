package com.revature.dao;

import org.hibernate.Session;
import com.revature.model.*;
import com.revature.util.*;

public class ReimbursementTypeDAO  {

	
	public ReimbursementType getRTypeById(int id) {
		Session sesh = HibernateUtil.getSession();
		
		ReimbursementType rt = sesh.get(ReimbursementType.class, id);
		
		return rt;
	}	
}
