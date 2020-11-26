package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.User;
import com.revature.util.HibernateUtil;

	public class ReimbursementDAO {

	public ReimbursementDAO() {
		super();
	}

	public boolean addReimbursement(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		
		try {
			ses.save(r);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSes();
		}
		
	}

	public boolean updateReimbursement(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		
		Transaction t = ses.beginTransaction();
		
		try {
			ses.merge(r);
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
	
	public List<Reimbursement> getAllReimbursements() {
		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> list = ses.createQuery("FROM Reimbursement").list();
		
		return list;
	}

	
	public List<Reimbursement> getAllReimbursementsByAuthor(User author) {
		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> list = ses.createQuery("FROM Reimbursement WHERE reimbAuthorId = "+author.getUserId(), Reimbursement.class).list();
		
		return list;
	}

	
	public List<Reimbursement> getAllReimbursementsByStatus(ReimbursementStatus status) {
		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> list = ses.createQuery("FROM Reimbursement WHERE reimbStatusId = "+status.getStatusId(), Reimbursement.class).list();
		
		return list;
	}

	
	public Reimbursement getReimbursementById(int id) {
		Session ses = HibernateUtil.getSession();
		
		Reimbursement r = ses.get(Reimbursement.class, id);
		
		return r;
	}

}
