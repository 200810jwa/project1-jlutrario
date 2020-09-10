package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.services.ReimbursementService;

/**
 * Servlet implementation class ApproveDenyServlet
 */
@WebServlet(urlPatterns = {"/changestatus"})
public class ApproveDenyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ReimbursementService reimbServ = new ReimbursementService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveDenyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rid = Integer.parseInt(request.getParameter("rid"));
		int uid = Integer.parseInt(request.getParameter("uid"));
		int status = Integer.parseInt(request.getParameter("status"));
		
		PrintWriter writer = response.getWriter();
		
			writer.print(reimbServ.updateStatus(rid, uid, status));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
