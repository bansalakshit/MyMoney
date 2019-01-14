package debitCard;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
public class dc1 extends HttpServlet {

	protected void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
		String dcn=req.getParameter("dcn");
		String dvut=req.getParameter("dvut");
		String dcvv=req.getParameter("dcvv");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			java.sql.Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			
			PreparedStatement pst3=conn.prepareStatement("select * from obs where dcn=? and dvut=? and dcvv=?");
			pst3.setString(1, dcn);
			pst3.setString(2, dvut);
			pst3.setString(3, dcvv);
			int a=pst3.executeUpdate();
			if(a!=0)
			{	
				HttpSession session =req.getSession();
				String x=(String)session.getAttribute("accb1");
				String accb1=x.trim();
				PreparedStatement pst=conn.prepareStatement("select accb from obs where dcn=? and dvut=? and dcvv=?");
				pst.setString(1, dcn);
				pst.setString(2, dvut);
				pst.setString(3, dcvv);
				ResultSet rs=pst.executeQuery();
				rs.next();
				String ab=rs.getString("accb").trim();
				if(Integer.parseInt(accb1)>Integer.parseInt(ab))
				{
					pw.println("<script>alert('Insufficient Balance..'); window.location='debit.html'</script>");
				}
				else
				{
				int accb2=Integer.valueOf(ab) - Integer.valueOf(accb1);
				String accb3=Integer.toString(accb2);
				
				PreparedStatement pst1=conn.prepareStatement("update obs set accb=? where dcn=? and dvut=? and dcvv=?");
				pst1.setString(1,accb3);
				pst1.setString(2,dcn);
				pst1.setString(3,dvut);
				pst1.setString(4,dcvv);
				pst1.executeUpdate();
				
				String y=(String)session.getAttribute("accn1");
				String accn1=y.trim();  
				//pw.println(accn1);
				PreparedStatement pst2=conn.prepareStatement("select accb from obs where accn=?");
				pst2.setString(1, accn1);
				ResultSet rs2=pst2.executeQuery();
				rs2.next();
				String ac=rs2.getString("accb").trim();
				
				int accb4=Integer.valueOf(ac) + Integer.valueOf(accb1);
				String accb5=Integer.toString(accb4);
				
				PreparedStatement pst4=conn.prepareStatement("update obs set accb=? where accn=?");
				pst4.setString(1,accb5);
				pst4.setString(2,accn1);
				pst4.executeUpdate();
				
				RequestDispatcher rd=req.getRequestDispatcher("debit2.html");  
		        rd.forward(req, res); 
				
			}
				
			}
			else
			{
				pw.println("<script>alert('Invalid Card Details'); window.location='debit.html'</script>");	
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

	
	

