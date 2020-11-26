package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.*;
import com.revature.util.*;

	public class ReimbursementDAO  {

	public ReimbursementDAO() {
		super();
	}
	
	
	public List<Reimbursement> getAllReimbursements() {
		Session sesh = HibernateUtil.getSession();
		
		List<Reimbursement> list = sesh.createQuery("FROM Reimbursement").list();
		
		return list;
	}

	public List<Reimbursement> getAllReimbursementsByAuthor(User author) {
		Session sesh = HibernateUtil.getSession();
		
		List<Reimbursement> list = sesh.createQuery("FROM Reimbursement WHERE reimbAuthorId = "+author.getUserId(), Reimbursement.class).list();
		
		return list;
	}

	public List<Reimbursement> getAllReimbursementsByStatus(ReimbursementStatus status) {
		Session sesh = HibernateUtil.getSession();
		
		List<Reimbursement> list = sesh.createQuery("FROM Reimbursement WHERE reimbStatusId = "+status.getStatusId(), Reimbursement.class).list();
		
		return list;
	}

	public Reimbursement getReimbursementById(int id) {
		Session sesh = HibernateUtil.getSession();
		
		Reimbursement r = sesh.get(Reimbursement.class, id);
		
		return r;
	}

	public boolean addReimbursement(Reimbursement r) {
		Session sesh = HibernateUtil.getSession();
		
		try {
			sesh.save(r);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSes();
		}
		
	}

	public boolean updateReimbursement(Reimbursement r) {
		Session sesh = HibernateUtil.getSession();
		
		Transaction tx = sesh.beginTransaction();
		
		try {
			sesh.merge(r);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		} finally {
			HibernateUtil.closeSes();
		}
	}
}
