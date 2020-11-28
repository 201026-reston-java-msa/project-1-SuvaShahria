package com.revature.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.*;
import com.revature.model.*;

import com.revature.services.*;

public class RbController {
	private static ReimbursementStatusDAO rsdao = new ReimbursementStatusDAO();
	private static  RbService rs = new RbService();
	private static UserService us = new UserService();
	private static ObjectMapper om = new ObjectMapper();

	
	public void getAllReimbursementsByAuthor(HttpServletResponse res, HttpServletRequest request, int Id) throws IOException {
		if(request.getMethod().equals("POST")) {
			System.out.println("rb post");
			BufferedReader reader = request.getReader();
			
			StringBuilder sb = new StringBuilder();
			
			String line = reader.readLine();
			
			while(line != null) {
				sb.append(line);
				line = reader.readLine();
			}
			
			String body = new String(sb);
			//System.out.println(body);
			ReimbursementDTO rbt = om.readValue(body, ReimbursementDTO.class);
			Id = rbt.getId();
			//System.out.println(Id);
		}
		User u = us.getUserById(Id);
		List<Reimbursement> r = null;
		try {
			 r = rs.getAllByAuthor(u);
		}catch(Exception e) {
			
		}
		
		if (r == null) {
			res.setStatus(400);
		} else {
			res.setStatus(200);
			String json = om.writeValueAsString(r);
			res.getWriter().println(json);
		}
		
		
	}
	
	
	public void getAllReimbursementsByStatus (HttpServletResponse res, int sId) throws IOException {
		
		ReimbursementStatus rs2 = rsdao.getStatusById(sId);
		List<Reimbursement> r = null;
		try {
			r = rs.getAllByStatus(rs2);
		}catch(Exception e) {
			
		}
		
		if (r == null) {
			res.setStatus(400);
		} else {
			res.setStatus(200);
			String json = om.writeValueAsString(r);
			res.getWriter().println(json);
		}
		
	}
	
	public void getReimbursement(HttpServletResponse res, int id) throws IOException {
		
		Reimbursement r = rs.getById(id);
		
		if (r == null) {
			res.setStatus(400);
		} else {
			res.setStatus(200);
			String json = om.writeValueAsString(r);
			res.getWriter().println(json);
		}
		
	}
	
	public void getAllReimbursements(HttpServletRequest request, HttpServletResponse res) throws IOException {
		
		List<Reimbursement> r = rs.getAllReimb();
		res.setStatus(200);
		String json = om.writeValueAsString(r);
		res.getWriter().println(json);
		
	}

	public void addReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		BufferedReader reader = req.getReader();
		
		StringBuilder sb = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		
		String body = new String(sb);
		System.out.println(body);
		
		ReimbursementDTO rbt = om.readValue(body, ReimbursementDTO.class);
		//System.out.println(rbt);
		
		double rA = rbt.getAmount();
		String rDesc = rbt.getDescription();
		HttpSession ses = req.getSession(false);
		if(ses == null) {
			System.out.println("User not logged in");
			return;
		}
		//(int)ses.getAttribute("userid")
		
		User rAuthor = us.getUserById((int)ses.getAttribute("userid"));
		ReimbursementStatus nrs = new ReimbursementStatus(1, "PENDING");
		
		String type = rbt.getrType();
		
		ReimbursementType rtype = null;
		if (type.equalsIgnoreCase("food")) {
			rtype = new ReimbursementType(1, "Food");
		} else if (type.equalsIgnoreCase("Lodging")) {
			rtype = new ReimbursementType(2, "Lodging");
		} else if (type.equalsIgnoreCase("Travel")) {
			rtype = new ReimbursementType(3, "Travel");
		} else if (type.equalsIgnoreCase("Other")) {
			rtype = new ReimbursementType(4, "Other");
		}
		//System.out.println(rtype);
		Reimbursement r = new Reimbursement(rA, new Timestamp(System.currentTimeMillis()), null, rDesc, rAuthor, null, nrs, rtype);
		//System.out.println(r);
		if (rs.addReimb(r)) {
			res.setStatus(200);
			res.getWriter().println("Reimbursement Created");
		} else {
			res.setStatus(400);
		}
		
	}
	
	
	public void updateReimbStatus (HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		BufferedReader reader = req.getReader();
		
		StringBuilder sb = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		
		String body = new String(sb);
		
		ReimbursementDTO rdt = om.readValue(body, ReimbursementDTO.class);
		
		int rId = rdt.getId();
		
		Reimbursement r = rs.getById(rId);
		
		String status = rdt.getrStatus();
		
		ReimbursementStatus rStatus = null;
		if (status.equalsIgnoreCase("APPROVED")) {
			rStatus = new ReimbursementStatus(2, "APPROVED");
		} else if (status.equalsIgnoreCase("DENIED")) {
			rStatus = new ReimbursementStatus(3, "DENIED");
		}
		
		r.setReimbStatusId(rStatus);
		HttpSession ses = req.getSession(false);
		if(ses == null) {
			System.out.println("User not logged in");
			return;
		}
		//(int)ses.getAttribute("userid")
		
		
		User resolver = us.getUserById((int)ses.getAttribute("userid"));
		r.setReimbResolverId(resolver);
		r.setResolved(new Timestamp(System.currentTimeMillis()));
		
		if (rs.updateReimb(r)) {
			res.setStatus(200);
			res.getWriter().println("Reimbursement Status Updated");
		} else {
			res.setStatus(400);
		}	
	}


	public void viewPend(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("in view pend");
		List<Reimbursement> r = rs.getAllReimb();
		List<Reimbursement> r2 = new LinkedList<Reimbursement>();;
		
		for(Reimbursement tmp: r) {
			if(tmp.getReimbStatusId().getStatusId()==1) {
				r2.add(tmp);
			}
		}
		
		response.setStatus(200);
		String json = om.writeValueAsString(r2);
		response.getWriter().println(json);
		
	}
	
	public void viewAppr(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("in view pend");
		List<Reimbursement> r = rs.getAllReimb();
		List<Reimbursement> r2 = new LinkedList<Reimbursement>();;
		
		for(Reimbursement tmp: r) {
			if(tmp.getReimbStatusId().getStatusId()==2) {
				r2.add(tmp);
			}
		}
		
		response.setStatus(200);
		String json = om.writeValueAsString(r2);
		response.getWriter().println(json);
		
	}
}