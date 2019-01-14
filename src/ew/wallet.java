package ew;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
public class wallet extends HttpServlet {

	protected void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
		int h=20000;
		
		String wan=req.getParameter("wan");
		String wam=req.getParameter("wam");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			java.sql.Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			
			PreparedStatement pst3=conn.prepareStatement("select * from obs where accn=?");
			pst3.setString(1, wan);
			int u=pst3.executeUpdate();
			if(u!=0)
			{	
					if(Integer.parseInt(wam)>h)
					{
						pw.println("<script>alert('Sorry, but you cannot transfer more than Rs20000...'); window.location='wallet.html'</script>");
					}
					else
					{
						HttpSession session =req.getSession();
						String x=(String)session.getAttribute("name");
						String y=(String)session.getAttribute("passwrd");
						PreparedStatement pst=conn.prepareStatement("select walet from obs where name=? and password=?");
						pst.setString(1, x);
						pst.setString(2, y);
						ResultSet rs=pst.executeQuery();
						rs.next();
						String ab=rs.getString("walet");
						if(Integer.parseInt(ab)>=Integer.parseInt(wam))
						{
						int accb2=Integer.valueOf(ab) - Integer.valueOf(wam);
						String accb3=Integer.toString(accb2);
				
						PreparedStatement pst1=conn.prepareStatement("update obs set walet=? where name=? and password=?");
						pst1.setString(1,accb3);
						pst1.setString(2,x);
						pst1.setString(3,y);
						pst1.executeUpdate();
				
						PreparedStatement pst2=conn.prepareStatement("select accb from obs where accn=?");
						pst2.setString(1, wan);
						ResultSet rs2=pst2.executeQuery();
						rs2.next();
						
						String ac=rs2.getString("accb");
				
						int accb4=Integer.valueOf(wam) + Integer.valueOf(ac);
						String accb5=Integer.toString(accb4);
				
						PreparedStatement pst4=conn.prepareStatement("update obs set accb=? where accn=?");
						pst4.setString(1,accb5);
						pst4.setString(2,wan);
						pst4.executeUpdate();
				
						RequestDispatcher rd=req.getRequestDispatcher("wallet1.html");  
						rd.forward(req, res); 
						}
						else
						{
							pw.println("<script>alert('Insufficient Balance...'); window.location='wallet.html'</script>");
						}
					}
				}
			else
			{
				pw.println("<script>alert('Invalid Details'); window.location='wallet.html'</script>");	
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

	
	

