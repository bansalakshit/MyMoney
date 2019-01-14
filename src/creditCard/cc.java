package creditCard;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

public class cc extends HttpServlet {

	public void service(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
		int s=49500;
		
		String acn1=req.getParameter("acn");
		String acb1=req.getParameter("acb");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			java.sql.Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			
			PreparedStatement pst=conn.prepareStatement("select * from obs where accn=?");
			pst.setString(1,acn1);
			
			int b=pst.executeUpdate();
			
			if(b!=0)
			{
				
				if(Integer.parseInt(acb1)>s)
				{
					pw.println("<script>alert('Sorry, but you cannot transfer more than Rs49,500 in 1 day...'); window.location='credit.html'</script>");
				}
				else
				{
					HttpSession session =req.getSession();
					session.setAttribute("acb1", acb1);
					session.setAttribute("acn1", acn1);
					res.sendRedirect("credit1.html");  
				}  
			}
			else
			{
				pw.println("<script>alert('Invalid Details'); window.location='credit.html'</script>");	
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
	