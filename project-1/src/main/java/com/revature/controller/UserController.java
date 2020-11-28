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

}
