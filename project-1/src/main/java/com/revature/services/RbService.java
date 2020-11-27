package com.revature.services;

import java.util.List;


import com.revature.dao.*;
import com.revature.model.*;
import org.apache.log4j.Logger;


public class RbService {
	private static final Logger log = Logger.getLogger(RbService.class);
	
	private static ReimbursementDAO rDao = new ReimbursementDAO();
	
	public List<Reimbursement> getAllReimb(){
		log.info("Getting All Reimb");
		return rDao.getAllReimbursements();
	}
	
	public List<Reimbursement> getAllByAuthor(User author){
		log.info("Getting Reimb by Author");
		return rDao.getAllReimbursementsByAuthor(author);
	}
	
	public List<Reimbursement> getAllByStatus(ReimbursementStatus status){
		log.info("Getting Reimb by Status");
		return rDao.getAllReimbursementsByStatus(status);
	}
	
	public Reimbursement getById(int id){
		log.info("Getting Reimb by Id");
		return rDao.getReimbursementById(id);
	}
	
	public boolean addReimb(Reimbursement r){
		log.info("Adding Reimb");
		return rDao.addReimbursement(r);
	}
	
	public boolean updateReimb(Reimbursement r){
		log.info("Updating Reimb");
		return rDao.updateReimbursement(r);
	}	
}
