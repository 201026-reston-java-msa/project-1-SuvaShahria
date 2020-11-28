package com.revature.controller;
import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.ReimbursementDTO;
import com.revature.model.User;
import com.revature.services.UserService;
import com.google.gson.Gson;

import com.google.gson.JsonObject;

public class UserController {
	private static UserService us = new UserService();
	private static ObjectMapper om = new ObjectMapper();

	public void getUser(HttpServletRequest request, HttpServletResponse response) throws  IOException{
		Gson gson=new Gson();
		// TODO Auto-generated method stub
		System.out.println("in get user");
		HttpSession ses = request.getSession(false);
		if(ses == null) {
			System.out.println("User not logged in");
			return;
		}
		User u = us.getUserById((int)ses.getAttribute("userid"));
		response.setStatus(200);
		System.out.println(gson.toJson(u));
		response.getWriter().println(gson.toJson(u));
	}

	public void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = request.getReader();
		
		StringBuilder sb = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		
		String body = new String(sb);
		System.out.println(body);
	
		User u = om.readValue(body, User.class);
//		System.out.println(u.getEmail());
//		System.out.println(u.getUserName());
		HttpSession ses = request.getSession(false);
		if(ses == null) {
			System.out.println("User not logged in");
			return;
		}
		//(int)ses.getAttribute("userid")
		
		
		User u2 = us.getUserById((int)ses.getAttribute("userid"));
		u2.setEmail(u.getEmail());
		u2.setUserName(u.getUserName());
		
		if (us.updateUser(u2)) {
			response.setStatus(200);
			response.getWriter().println("User Updated");
		} else {
			response.setStatus(400);
		}
	}

}
