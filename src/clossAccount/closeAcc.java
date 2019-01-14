package clossAccount;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
public class closeAcc extends HttpServlet {

	public void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			java.sql.Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			HttpSession session =req.getSession();
			String name=(String)session.getAttribute("name");
			PreparedStatement pst=conn.prepareStatement("delete from obs where name=?");
			pst.setString(1,name);
			pst.executeUpdate();
			
			pw.println("<script>alert('Your Account has been deleted successfully !!!!!!'); window.location='index.html'</script>");
			
			}
	    
		catch(ClassNotFoundException e){
			System.out.println(e);
		}
		
		catch(SQLException e){
			System.out.println(e);
		}
	}
}

	
	

