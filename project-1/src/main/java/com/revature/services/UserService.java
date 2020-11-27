package com.revature.services;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import com.revature.dao.*;
import com.revature.model.User;
import com.revature.util.*;

public class UserService {

	//private static final Logger log = LogManager.getLogger(UserServices.class);
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

	public List<User> getAllUsers() {
	//	log.info("Getting all Users");
		return uDao.getAllUsers();
	}
	
	public User getUserById(int id) {
	//	log.info("Getting User with id "+id);
		return uDao.getUserById(id);
	}
	
	public User getUserByUsername(String username) {
	//	log.info("Getting User by username "+username);
		return uDao.getUserByUsername(username);
	}
	
	public boolean addUser(User u) {
		//log.info("Adding a new User "+u);
		return uDao.addUser(u);
	}
	
	public boolean updateUser(User u) {
		//log.info("Updating User "+u);
		return uDao.updateUser(u);
	}	
}