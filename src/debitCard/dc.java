package debitCard;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

public class dc extends HttpServlet {

	public void service(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
		int i=49500;
		
		String accn1=req.getParameter("dcnn");
		String accb1=req.getParameter("acb");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			java.sql.Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			
			PreparedStatement pst=conn.prepareStatement("select * from obs where accn=?");
			pst.setString(1,accn1);
			
			int b=pst.executeUpdate();
			
			if(b!=0)
			{
				if(Integer.parseInt(accb1)>i)
				{
					pw.println("<script>alert('Sorry, but you cannot transfer more than Rs49,500 in 1 day...'); window.location='debit.html'</script>");
				}
				else
				{
					HttpSession session =req.getSession();
					session.setAttribute("accb1", accb1);
					session.setAttribute("accn1", accn1);
					res.sendRedirect("debit1.html");  
				}
				
			}
			else
			{
				pw.println("<script>alert('Invalid Details'); window.location='debit.html'</script>");	
			}	
		}
		catch(ClassNotFoundException e){
			System.out.println(e);
		}
		
		catch(SQLException e){
			System.out.println(e);
		}
	}
}
	