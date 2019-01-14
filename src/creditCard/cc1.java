package creditCard;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
public class cc1 extends HttpServlet {

	protected void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
		String ccn=req.getParameter("ccn");
		String cvut=req.getParameter("cvut");
		String ccvv=req.getParameter("ccvv");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			java.sql.Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			
			PreparedStatement pst3=conn.prepareStatement("select * from obs where ccn=? and cvut=? and ccvv=?");
			pst3.setString(1, ccn);
			pst3.setString(2, cvut);
			pst3.setString(3, ccvv);
			int a=pst3.executeUpdate();
			if(a!=0)
			{	
				HttpSession session =req.getSession();
				String x=(String)session.getAttribute("acb1");
				String acb1=x.trim();
				PreparedStatement pst=conn.prepareStatement("select accb from obs where ccn=? and cvut=? and ccvv=?");
				pst.setString(1, ccn);
				pst.setString(2, cvut);
				pst.setString(3, ccvv);
				ResultSet rs=pst.executeQuery();
				rs.next();
				String ab=rs.getString("accb").trim();
				
				int acb2=Integer.valueOf(ab) - Integer.valueOf(acb1);
				String acb3=Integer.toString(acb2);
				
				PreparedStatement pst1=conn.prepareStatement("update obs set accb=? where ccn=? and cvut=? and ccvv=?");
				pst1.setString(1,acb3);
				pst1.setString(2,ccn);
				pst1.setString(3,cvut);
				pst1.setString(4,ccvv);
				pst1.executeUpdate();
				
				String y=(String)session.getAttribute("acn1");
				String acn1=y.trim();  
				
				PreparedStatement pst2=conn.prepareStatement("select accb from obs where accn=?");
				pst2.setString(1, acn1);
				ResultSet rs2=pst2.executeQuery();
				rs2.next();
				String ac=rs2.getString("accb").trim();
				
				int acb4=Integer.valueOf(ac) + Integer.valueOf(acb1);
				String acb5=Integer.toString(acb4);
				
				PreparedStatement pst4=conn.prepareStatement("update obs set accb=? where accn=?");
				pst4.setString(1,acb5);
				pst4.setString(2,acn1);
				pst4.executeUpdate();
				
				RequestDispatcher rd=req.getRequestDispatcher("credit2.html");  
		        rd.forward(req, res); 
				
			}
			else
			{
				pw.println("<script>alert('Invalid Card Details'); window.location='credit.html'</script>");	
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

	
	

