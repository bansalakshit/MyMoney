package mypack;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
public class signup4 extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();


		String accn=req.getParameter("accn");
		String accb=req.getParameter("accb");
		String walet=req.getParameter("walet");
			
			try {
				HttpSession session =req.getSession();
				
				String ac=(String)session.getAttribute("ac");
				String name=(String)session.getAttribute("name");
				String password=(String)session.getAttribute("password");
				String email=(String)session.getAttribute("email");
				String gender=(String)session.getAttribute("gender");
				String dob=(String)session.getAttribute("dob");
				String city=(String)session.getAttribute("city");
				String contact=(String)session.getAttribute("contact");
				String address=(String)session.getAttribute("address");
				String dcn=(String)session.getAttribute("dcn");
				String dvut=(String)session.getAttribute("dvut");
				String dcvv=(String)session.getAttribute("dcvv");
				String ccn=(String)session.getAttribute("ccn");
				String cvut=(String)session.getAttribute("cvut");
				String ccvv=(String)session.getAttribute("ccvv");
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				java.sql.Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
				
				PreparedStatement pst=conn.prepareStatement("insert into obs(name,password,ac,email,gender,dob,city,contact,address,dcn,dvut,dcvv,ccn,cvut,ccvv,accn,accb,walet) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				
				pst.setString(1,name);  
		        pst.setString(2,password);        
		        pst.setString(3,ac);
		        pst.setString(4,email);
		        pst.setString(5,gender);
		        pst.setString(6,dob);
		        pst.setString(7,city);
		        pst.setString(8,contact);
		        pst.setString(9,address);
		        pst.setString(10,dcn);  
		        pst.setString(11,dvut);        
		        pst.setString(12,dcvv);
		        pst.setString(13,ccn);
		        pst.setString(14,cvut);
		        pst.setString(15,ccvv);
		        pst.setString(16,accn);
		        pst.setString(17,accb);
		        pst.setString(18,walet);
		        pst.executeUpdate();
		        
		        pw.println("<script>alert('Account Created Successfully...'); window.location='index.html'</script>");
		        
			}
			catch (Exception e)
			{  
		          pw.println(e);  
			}
		
		}
	}
	


	
	


