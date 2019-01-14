package mypack;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
public class signup3 extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();


		String ccn=req.getParameter("ccn");
		String cvut=req.getParameter("cvut");
		String ccvv=req.getParameter("ccvv");
		
			HttpSession session =req.getSession();
			
			session.setAttribute("ccn", ccn);
			session.setAttribute("cvut", cvut);
			session.setAttribute("ccvv", ccvv);
			
			
			
			res.sendRedirect("insertedpage4.html");
		
		}
	}