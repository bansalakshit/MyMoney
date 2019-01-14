package withdrawal;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
public class withdrawalMoney extends HttpServlet {

	protected void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
		int g=20000;
		String wi=req.getParameter("wi");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			java.sql.Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			
				if(Integer.parseInt(wi)>g)
				{
					pw.println("<script>alert('Sorry, but you cannot withdrawal more than Rs20000 in your account...'); window.location='moneyWithdrawal.html'</script>");
				}
				else
				{
					HttpSession session =req.getSession();
					String name=(String)session.getAttribute("name");
					String passwrd=(String)session.getAttribute("passwrd");
					
					PreparedStatement pst3=conn.prepareStatement("select * from obs where name=? and password=?");
					pst3.setString(1, name);
					pst3.setString(2, passwrd);
					ResultSet rs=pst3.executeQuery();
					rs.next();
					String walet=rs.getString("walet");
					String accb=rs.getString("accb");
					
					pst3.executeUpdate();
					
					if(Integer.parseInt(wi)>Integer.parseInt(walet))
					{
						pw.println("<script>alert('Insufficient Balance in your wallet..'); window.location='moneyWithdrawal.html'</script>");
					}
					else
					{
						int i=Integer.valueOf(walet) - Integer.valueOf(wi);
						String i2=Integer.toString(i);
				
						PreparedStatement pst1=conn.prepareStatement("update obs set walet=? where name=? and password=?");
						pst1.setString(1,i2);
						pst1.setString(2,name);
						pst1.setString(3,passwrd);
						pst1.executeUpdate();
				
						int j=Integer.valueOf(wi) + Integer.valueOf(accb);
						String j2=Integer.toString(j);
				 
						PreparedStatement pst2=conn.prepareStatement("update obs set accb=? where name=? and password=?");
						pst2.setString(1,j2);
						pst2.setString(2,name);
						pst2.setString(3,passwrd);
						pst2.executeUpdate();
				
						RequestDispatcher rd=req.getRequestDispatcher("moneyWithdrawal1.html");  
						rd.forward(req, res); 
					}
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

	
	

