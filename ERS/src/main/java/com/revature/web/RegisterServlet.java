package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.templates.RegisterTemplate;
import com.revature.utilities.ResponseUtilities;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static ObjectMapper om = new ObjectMapper();
	private static UserService us = new UserService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/static/register.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		
		String body = reader.lines().collect(Collectors.joining());
		
		RegisterTemplate rt = om.readValue(body, RegisterTemplate.class);
		
		User u = us.register(rt);
		
		if(u == null) {
			response.setStatus(400);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", u);
			ResponseUtilities.writeJSON(response, u);
		}
	}

}
