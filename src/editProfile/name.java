package editProfile;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
public class name extends HttpServlet {

	public void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
		String na=req.getParameter("namee");
		
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
					String e=rs.getString("email");
					String f=rs.getString("dob");
					PreparedStatement pst2=conn.prepareStatement("update obs set name=? where password=? and email=? and dob=?");
					pst2.setString(1,na);
					pst2.setString(2,passwrd);
					pst2.setString(3,e);
					pst2.setString(4,f);
					pst2.executeUpdate();
					
					pw.println("<script>alert('Name has been changed Successfully !!!!!!'); window.location='login.html'</script>");	
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


	
	

