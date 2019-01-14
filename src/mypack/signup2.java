package mypack;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
public class signup2 extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();


		String dcn=req.getParameter("dcn");
		String dvut=req.getParameter("dvut");
		String dcvv=req.getParameter("dcvv");
		
			HttpSession session =req.getSession();
			
			session.setAttribute("dcn", dcn);
			session.setAttribute("dvut", dvut);
			session.setAttribute("dcvv", dcvv);
			
			//session.setMaxInactiveInterval(30*60);
			
			res.sendRedirect("insertedpage3.html");
		
		}
	}
	


	
	


