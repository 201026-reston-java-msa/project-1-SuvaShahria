package com.revature.services;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.*;
import com.revature.model.User;
import com.revature.util.*;

public class UserService {

	private static final Logger log = Logger.getLogger(RbService.class);
	private static UserDAO uDao = new UserDAO();
	
	
	public User login(User u) {
		try { 
			u.setPassWord(Hashing.getHash(u.getPassWord()));
			String password = u.getPassWord();
			
			User u2 = getUserByUsername(u.getUserName());
			if (u2 != null) {
				
				if (u2.getPassWord().equals(u.getPassWord())) {
					return u2;
				} else {
					System.out.println("Invalid");
				}
			}
			
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			System.out.println("User doesn't exist");
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}	
	
	public User getUserByUsername(String username) {
		log.info("Getting User by name");
		return uDao.getUserByUsername(username);
	}
	
	public List<User> getAllUsers() {
		log.info("Getting all Users");
		return uDao.getAllUsers();
	}
	
	public User getUserById(int id) {
		log.info("Getting User by id");
		return uDao.getUserById(id);
	}
	
	public boolean addUser(User u) {
		return uDao.addUser(u);
	}
	
	public boolean updateUser(User u) {
		return uDao.updateUser(u);
	}	
}