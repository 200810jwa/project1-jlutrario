package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.revature.models.Reimbursement;
import com.revature.services.RStatusService;
import com.revature.services.RTypeService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet(urlPatterns = {"/view"})
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static ReimbursementService rs = new ReimbursementService();
	private static UserService userServ = new UserService();
	private static RStatusService rStatServ = new RStatusService();
	private static RTypeService rTypeServ = new RTypeService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/static/view.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/static/view.html").forward(request, response);
	}

}
