package lout;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
public class logout extends HttpServlet {

	protected void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
		 HttpSession session=req.getSession();  
         session.invalidate();
		
		RequestDispatcher rd=req.getRequestDispatcher("logout.html");  
		rd.forward(req, res); 
						
	}
}

	
	

