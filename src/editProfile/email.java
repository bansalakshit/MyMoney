package editProfile;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
public class email extends HttpServlet {

	public void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
		String em=req.getParameter("email");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			java.sql.Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			
			HttpSession session =req.getSession();
			String name=(String)session.getAttribute("name");
			String passwrd=(String)session.getAttribute("passwrd");
			                                                                    
			PreparedStatement pst=conn.prepareStatement("select * from obs where name=? and password=?");
			pst.setString(1,name);
			pst.setString(2,passwrd);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
					PreparedStatement pst1=conn.prepareStatement("update obs set email=? where name=? and password=?");
					pst1.setString(1,em);
					pst1.setString(2,name);
					pst1.setString(3,passwrd);
					pst1.executeUpdate();
					
					pw.println("<script>alert('Email has been changed Successfully !!!!!!'); window.location='login.html'</script>");	
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


	
	

