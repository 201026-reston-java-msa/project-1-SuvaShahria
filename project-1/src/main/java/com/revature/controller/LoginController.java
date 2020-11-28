package com.revature.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.User;
import com.revature.services.UserService;
import com.google.gson.Gson;

import com.google.gson.JsonObject;

public class LoginController {
	
	private static UserService us = new UserService();
	private static ObjectMapper om = new ObjectMapper();
	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setStatus(500);
		
		System.out.println("in login");
		// TODO Auto-generated method stub
		BufferedReader reader = req.getReader();
		
		StringBuilder sb = new StringBuilder();
		
		String line = reader.readLine();
		
		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		
		String body = new String(sb);
		System.out.println(sb);
		
		User u = om.readValue(body, User.class);
		User u2 = null;
		try{
			u2 = us.login(u);
		}catch(Exception e) {
			
		}
	//	System.out.println(u2.getUserRoleId().getRoleId());
		if(u2!= null) {
			//System.out.println(u2);
			HttpSession ses = req.getSession();
			ses.setAttribute("user", u2);
			ses.setAttribute("userid", u2.getUserId());
			ses.setAttribute("roleid", u2.getUserRoleId());
			ses.setAttribute("username", u2.getUserName());
			if(u2.getUserRoleId().getRoleId()  == 1) {
				res.setStatus(201);
				//System.out.println("201");
			}else {
				System.out.println("202");
				res.setStatus(202);
			}
			
			res.getWriter().println("Logged In");
			System.out.println("logged in");
		}else {
	    	String message = "{ \"message\": \"Invalid Credentials\" }";
	    	JsonObject json2 = new Gson().fromJson(message, JsonObject.class);
	    	res.getWriter().println(json2);
	    	HttpSession s = req.getSession(false);
	    	res.setStatus(204);
	    	System.out.println("204");
			if (s != null) {
				
				s.invalidate();
			}
			System.out.println("oh no");
		}
		
	
		
		
	}
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession ses = request.getSession(false);
		//System.out.println("In logout");
		
		if (ses != null) {
			//System.out.println("In session logout");
			String username = (String) ses.getAttribute("username");
			ses.invalidate();
			response.setStatus(200);
			String message = "{ \"message\": \"You have successfully logged out {username}\" }";
			StringBuffer jb = new StringBuffer();
			jb.append("{ \"message\": \"You have successfully logged out ");
			jb.append(username);
			jb.append("\" }");
	    	JsonObject json2 = new Gson().fromJson(jb.toString(), JsonObject.class);
	    	//response.getWriter().println(json2);
		} else {
			response.setStatus(200);
			String message = "{ \"message\": \"There was no user logged into the session\" }";
	    	JsonObject json2 = new Gson().fromJson(message, JsonObject.class);
	    	//response.getWriter().println(json2);
		}
		
	}

}
