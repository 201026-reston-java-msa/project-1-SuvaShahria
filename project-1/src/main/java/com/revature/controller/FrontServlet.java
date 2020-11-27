package com.revature.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controller.LoginController;

/**
 * Servlet implementation class FrontServlet
 */
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static LoginController lc = new LoginController();

    /**
     * Default constructor. 
     */
    public FrontServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		request.getRequestDispatcher("test.html").forward(request, response);
//		response.setStatus(200);
		entry(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//entry(request,response);
		//request.getRequestDispatcher("test.html").forward(request, response);	
		response.setStatus(200);
	}
	
	protected void entry(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("application/json");
		response.setStatus(400);
		final String URI = request.getRequestURI().replace("/project-1/", "");
		String[] split = URI.split("/");
		
		System.out.println(split[0]);
		switch (split[0]) {
		case "login":
			lc.login(request,response);
			break;
		case "logout":
			lc.logout(request,response);
		case "s":
			RequestDispatcher rd = request.getRequestDispatcher("test.html");
			rd.forward(request, response);
			System.out.println("s");
		default:
			break;
		
		
		}
	}
	

}
