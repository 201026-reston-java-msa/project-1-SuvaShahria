package com.revature.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.controller.*;

/**
 * Servlet implementation class FrontServlet
 */
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static LoginController lc = new LoginController();
	private static RbController rb = new RbController();
	private static UserController us = new UserController();

	/**
	 * Default constructor.
	 */
	public FrontServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		request.getRequestDispatcher("test.html").forward(request, response);
//		response.setStatus(200);
		entry(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		entry(request, response);
		// request.getRequestDispatcher("test.html").forward(request, response);
		//System.out.println("in post");
		// response.setStatus(200);
	}

	protected void entry(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setStatus(400);
		final String URI = request.getRequestURI().replace("/project-1/", "");
		String[] split = URI.split("/");

		// System.out.println(split[0]);
		switch (split[0]) {
		case "user":
			if (request.getMethod().equals("GET")) {
				us.getUser(request,response);
			}else {
				us.updateUser(request,response);
			}
			break;
		case "login":
			lc.login(request, response);
			break;
		case "logout":
			lc.logout(request, response);
		case "viewEmp":
			us.getAll(request,response);
			break;
		case "viewPend":
			rb.viewPend(request,response);
			break;
		case "viewAppr":
			rb.viewAppr(request,response);
			break;
		case "reimbursements":
			if (request.getMethod().equals("GET")) {
				rb.getAllReimbursements(request, response);
				
			} else {
				rb.addReimbursement(request, response);
			}
			break;
		case "bystatus":
//			for(String i: split) {
//				System.out.println(i);
//			}
			// System.out.println(split);
			// System.out.println("in");
			int rsId = Integer.parseInt(split[1]);
//			System.out.println(rsId);
			rb.getAllReimbursementsByStatus(response, rsId);
			break;
		case "byauthor":
			System.out.println("byauthor");
			int raId;
			try {
				raId = Integer.parseInt(split[1]);
			}catch(Exception e) {
				System.out.println("author -e");
				HttpSession ses = request.getSession(false);
				raId = (int)ses.getAttribute("userid");
			}
			System.out.println(raId);
			
			rb.getAllReimbursementsByAuthor(response,request, raId);
			break;
		case "update":
			raId = Integer.parseInt(split[1]);
			if(raId ==1) {
				rb.updateReimbStatus(request, response,"approved");
			}else {
				rb.updateReimbStatus(request, response,"denied");
			}
			
			break;
		default:
			break;

		}
	}

}
