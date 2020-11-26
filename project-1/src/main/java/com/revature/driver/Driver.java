package com.revature.driver;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.CrimeDao;
import com.revature.dao.SuperPrisonDao;
import com.revature.dao.SuperVillainDao;

import com.revature.model.*;
import com.revature.util.*;
import com.revature.dao.*;
//import com.revature.model.User;
public class Driver {

	private static UserRoleDAO urDao = new UserRoleDAO();
	private static UserDAO ud = new UserDAO();
	public static void main(String[] args) {
		initialValues();
		test();
		// run a selectAll statment from one of our Daos...like SuperPrisonDao
//		String p1 = "e";
//		String p2 = "f";
//		try {
//			p1 = Hashing.getHash("e");
//			p2 = Hashing.getHash("f");
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		UserRole r1 = urDao.getRoleById(1);
//		UserRole r2 = urDao.getRoleById(2);
//		
//		User u1 = new User("e",p1, "e", "e", "e", r1);
//		User u2 = new User("e", p2, "f", "f", "f", r2);
//		
//		ud.addUser(u1);
//		ud.addUser(u2);
		HibernateUtil.closeSes();
		System.out.println("w");
		
	}
	
	public static void test() {
		UserRole r1 = urDao.getRoleById(1);
		UserRole r2 = urDao.getRoleById(2);
		System.out.println(r1.getRole());
		
	}
	
	public static void initialValues() {
		// Create new Crimes
		Crime c1 = new Crime("Arson", "setting something ablaze");
		Crime c2 = new Crime("Freeze", "covering a whole city in ice");
		Crime c3 = new Crime("Time Manipulation", "freeze time and rob banks");
		
		// Creaete a a new CrimeDao
		CrimeDao cd = new CrimeDao();
		
		
		// use the CrimeDao to insert the crimes
		cd.insert(c1);
		cd.insert(c2);
		cd.insert(c3);
		
		List<Crime> cList = new ArrayList<>();
		cList.add(c1);
		cList.add(c2);
		cList.add(c3);
		
		
		// Create Super Prisons,
		SuperPrisons sp1 = new SuperPrisons("Azkaban", "England");
				
		// Create SuperVillains
		SuperVillain sv1 = new SuperVillain("Joker", "Evilness", 1000000, cList, sp1);
		
		// Create a SuperPrisonDao
		SuperPrisonDao spd = new SuperPrisonDao();
		
		
		SuperVillainDao svd = new SuperVillainDao();
		svd.insert(sv1);
		
//		List<SuperVillain> svList = new ArrayList<>();
//		svList.add(sv1);
//		sp1.setVillList(svList);

//		// insert the list of super villains into super prison, then add to DB
//		spd.insert(sp1);		
//		
		
		
		
	}
	
	
	
}
