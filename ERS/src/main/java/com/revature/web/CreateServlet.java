package com.revature.web;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.templates.RegisterTemplate;
import com.revature.templates.ReimbursementTemplate;
import com.revature.utilities.ResponseUtilities;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet(urlPatterns = {"/create/"})
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static ObjectMapper om = new ObjectMapper();
	private static ReimbursementService rs = new ReimbursementService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/static/create.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		
		String body = reader.lines().collect(Collectors.joining());
		
		ReimbursementTemplate rt = om.readValue(body, ReimbursementTemplate.class);
		
		Reimbursement r = rs.submit(rt);
				
		if(r == null) {
			response.setStatus(400);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("reimbursement", r);
			ResponseUtilities.writeJSON(response, r);
		}
	}

}
