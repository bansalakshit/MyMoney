package mt;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
public class moneyTransfer extends HttpServlet {

	protected void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
		int ha=49500;
		
		String taccn=req.getParameter("taccn");
		String amm=req.getParameter("amm");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			java.sql.Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			
			PreparedStatement pst3=conn.prepareStatement("select * from obs where accn=?");
			pst3.setString(1, taccn);
			int uu=pst3.executeUpdate();
			if(uu!=0)
			{	
					if(Integer.parseInt(amm)>ha)
					{
						pw.println("<script>alert('Sorry, but you cannot transfer more than Rs49500...'); window.location='MT.html'</script>");
					}
					else
					{
						HttpSession session =req.getSession();
						String xx=(String)session.getAttribute("name");
						String yy=(String)session.getAttribute("passwrd");
						PreparedStatement pst=conn.prepareStatement("select accb from obs where name=? and password=?");
						pst.setString(1, xx);
						pst.setString(2, yy);
						ResultSet rs=pst.executeQuery();
						rs.next();
						String abb=rs.getString("accb");
						if(Integer.parseInt(abb)>Integer.parseInt(amm))
						{
						int accb2=Integer.valueOf(abb) - Integer.valueOf(amm);
						String accb3=Integer.toString(accb2);
				
						PreparedStatement pst1=conn.prepareStatement("update obs set accb=? where name=? and password=?");
						pst1.setString(1,accb3);
						pst1.setString(2,xx);
						pst1.setString(3,yy);
						pst1.executeUpdate();
				
						PreparedStatement pst2=conn.prepareStatement("select accb from obs where accn=?");
						pst2.setString(1, taccn);
						ResultSet rs2=pst2.executeQuery();
						rs2.next();
						
						String ac=rs2.getString("accb");
				
						int accb4=Integer.valueOf(ac) + Integer.valueOf(amm);
						String accb5=Integer.toString(accb4);
				
						PreparedStatement pst4=conn.prepareStatement("update obs set accb=? where accn=?");
						pst4.setString(1,accb5);
						pst4.setString(2,taccn);
						pst4.executeUpdate();
				
						RequestDispatcher rd=req.getRequestDispatcher("MT1.html");  
						rd.forward(req, res); 
						}
						else
						{
							pw.println("<script>alert('Insufficient Balance...'); window.location='MT.html'</script>");
						}
					}
				}
			else
			{
				pw.println("<script>alert('Invalid Details...'); window.location='MT.html'</script>");
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

	
	

