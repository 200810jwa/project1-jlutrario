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
 * Servlet implementation class ReimbDetailsServlet
 */
@WebServlet(urlPatterns = {"/getdetails"})
public class ReimbDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static ReimbursementService rs = new ReimbursementService();
	private static UserService userServ = new UserService();
	private static RStatusService rStatServ = new RStatusService();
	private static RTypeService rTypeServ = new RTypeService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Reimbursement r = rs.getReimbursement(id);
		
		PrintWriter writer = response.getWriter();
		
		JSONObject json = new JSONObject();
			json.put("id", r.getId());
			json.put("amount", r.getAmount());
			json.put("submitted", r.getSubmitted().toString());
			if (r.getResolved() == null) {
				json.put("resolved", 0);
			} else {
				json.put("resolved", r.getResolved().toString());
			}
			json.put("description", r.getDescription());
			if (r.getReceipt() == null) {
				json.put("receipt", 0);
			} else {
				json.put("receipt", r.getReceipt());
			}
			json.put("author", userServ.authorName(r.getAuthor()));
			if (r.getResolver() == 0) {
				json.put("resolver", 0);
			} else {
				json.put("resolver", userServ.authorName(r.getResolver()));
			}
			json.put("status", rStatServ.getStatus(r.getStatus_id()));
			json.put("type", rTypeServ.getType(r.getType_id()));
			
		writer.print(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
