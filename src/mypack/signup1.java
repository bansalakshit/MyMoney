package mypack;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
public class signup1 extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
			res.setContentType("text/html");
			PrintWriter pw=res.getWriter();

			String name=req.getParameter("name");
			String password=req.getParameter("password");
			String email=req.getParameter("email");
			String gender=req.getParameter("gender");
			String dob=req.getParameter("dob");
			String city=req.getParameter("city");
			String contact=req.getParameter("contact");
			String address=req.getParameter("address");
			
			
				HttpSession session =req.getSession();
				session.setAttribute("name", name);
				session.setAttribute("password", password);
				session.setAttribute("email", email);
				session.setAttribute("gender", gender);
				session.setAttribute("dob", dob);
				session.setAttribute("city", city);
				session.setAttribute("contact", contact);
				session.setAttribute("address", address);
				//session.setMaxInactiveInterval(30*60);
				
				res.sendRedirect("insertedpage2.html");
			
			}
		    }
	


	

