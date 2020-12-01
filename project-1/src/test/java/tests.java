import static org.junit.Assert.*;

import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.revature.dao.*;
import com.revature.model.*;
import com.revature.services.*;
import com.revature.util.Hashing;

import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class tests {
	private static UserRoleDAO urDao = new UserRoleDAO();
	private static UserService us = new UserService();
	private static RbService rus = new RbService();
	private static int id1;
	private static int rid;

	@Test
	public void test() {
		
	}
	
	@BeforeClass
	public static void init() {
		String p1 = "te";
		String p2 = "tf";
		try {
			p1 = Hashing.getHash(p1);
			p2 = Hashing.getHash(p2);
		}catch(Exception e) {
			
		}
		UserRole r1 = urDao.getRoleById(1);
		UserRole r2 = urDao.getRoleById(2);
		try {
			User u1 = new User("te", p1, "te", "te", "te@gmail.com", r1);
			User u2 = new User("tf", p2, "tf", "tf", "tf@gmail.com", r2);
			us.addUser(u1);
			us.addUser(u2);
		}catch(Exception e) {
			
		}
		id1= us.getUserByUsername("te").getUserId();
		//System.out.println(id1);
		

	}
	
	@Test
	public void a_insertException() {
		//exception will be caught in dao
		String p1 = "te";
		String p2 = "tf";
		UserRole r1 = urDao.getRoleById(1);
		UserRole r2 = urDao.getRoleById(2);
		try {
			User u1 = new User("te", p1, "te", "te", "te@gmail.com", r1);
			//User u2 = new User("tf", p2, "tf", "tf", "tf@gmail.com", r2);
			us.addUser(u1);
			//us.addUser(u2);
			assert(true);
		}catch(Exception e) {
			
			assert(false);
		}
		
	}
	
	@Test
	public void b_findById() {
		
		if(id1 ==us.getUserByUsername("te").getUserId() ) {
			//System.out.println(us.getUserByUsername("te").getUserId());
			assert(true);
		}else {
			assert(false);
		}
		
	}
	
	@Test
	public void c_findAllUsers() {
		List<User> u = new ArrayList<User>();
		u = us.getAllUsers();
		if(u==null) {
			assert(false);
		}
		assert(true);
	}
	
	@Test public void d_login() {
		User u = new User();
		u.setUserName("te");
		u.setPassWord("te");
		if(us.login(u)==null) {
			assert(false);
		}
		assert(true);
	}
	
	@Test
	public void e_wrongLogin() {
		User u = new User();
		u.setUserName("te2");
		u.setPassWord("te2");
		try {
			if(us.login(u)==null) {
				assert(true);
			}else {
				assert(false);
			
			}
			
		}catch(Exception e) {
			assert(true);
		}
		
	}
	
	@Test
	public void f_addRb() {
		User rAuthor = us.getUserById(id1);
		ReimbursementStatus nrs = new ReimbursementStatus(1, "PENDING");
		ReimbursementType rtype = new ReimbursementType(1, "Food");
		Reimbursement r = new Reimbursement(15, new Timestamp(System.currentTimeMillis()), null, "test", rAuthor, null, nrs, rtype);
		if(rus.addReimb(r)) {
			assert(true);
		}else {
			assert(false);
		}
	}
	
	@Test
	public void h_finaAllR() {
		List<Reimbursement> r = new ArrayList<Reimbursement>();
		r = rus.getAllReimb();
		if(r==null) {
			assert(false);
		}
		assert(true);
	}
	
	@Test
	public void i_findByStatus() {
		ReimbursementStatus nrs = new ReimbursementStatus(1, "PENDING");
		List<Reimbursement> r = new ArrayList<Reimbursement>();
		r = rus.getAllByStatus(nrs);
		if(r==null) {
			assert(false);
		}
		assert(true);
	}
	
	@Test
	public void j_findByAuthor() {
		User u = us.getUserById(id1);
		List<Reimbursement> r = new ArrayList<Reimbursement>();
		r = rus.getAllByAuthor(u);
		if(r==null) {
			assert(false);
		}
		assert(true);
	}

}
