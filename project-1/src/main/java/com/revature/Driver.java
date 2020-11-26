package com.revature;

import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import com.revature.dao.*;
import com.revature.model.*;
import com.revature.util.Hashing;

public class Driver {
	private static final Logger log = Logger.getLogger(Driver.class);

	private static UserRoleDAO urDao = new UserRoleDAO();
	private static UserDAO us = new UserDAO();
	
	public static void main(String[] args) {
		
		addUsers();
	}
	
	public static void addUsers() {
		String p1 = "e";
		String p2 = "f";
		
		try {
			p2 = Hashing.getHash("f");
			p1 = Hashing.getHash("e");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UserRole r1 = urDao.getRoleById(1);
		UserRole r2 = urDao.getRoleById(2);
		
		User u1 = new User("e", p1, "e", "e", "e@gmail.com", r1);
		User u2 = new User("f", p2, "f", "f", "f@gmail.com", r2);
		log.info("hiwwe");
		System.out.println("hi");
		us.addUser(u1);
		us.addUser(u2);
	}
}
