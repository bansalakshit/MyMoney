package mypack;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
public class account extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();

		String ac=req.getParameter("ac");
		
		HttpSession session =req.getSession();
		session.setAttribute("ac", ac);
			
		res.sendRedirect("insertedpage1.html");
		
		
	}
	
}

	
	


