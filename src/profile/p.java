package profile;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class p extends HttpServlet {
	
	public void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
			
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
				PreparedStatement pst1=conn.prepareStatement("select * from obs where name=?");
				pst1.setString(1,name);
				ResultSet rs1=pst1.executeQuery();
				rs1.next();
				
				pw.println("<html>");
				pw.println("<body>");
				pw.println("<style>");
				pw.println(".centered {\r\n" + 
						"    position: absolute;\r\n" + 
						"    top: 50%;\r\n" + 
						"    left: 50%;\r\n" + 
						"    transform: translate(-50%, -50%);\r\n" + 
						"}");
				pw.println(".container {\r\n" + 
						"    position: relative;\r\n" + 
						"    text-align: left;\r\n" + 
						"    color: black;\r\n" + 
						"}");
				pw.println(".navbar {\r\n" + 
						"    overflow: hidden;\r\n" + 
						"    background-color: #333;\r\n" + 
						"    font-family: Arial, Helvetica, sans-serif;\r\n" + 
						"}\r\n" + 
						"\r\n" + 
						".navbar a {\r\n" + 
						"    float: left;\r\n" + 
						"    font-size: 20px;\r\n" + 
						"    color: white;\r\n" + 
						"    text-align: center;\r\n" + 
						"    padding: 14px 16px;\r\n" + 
						"    text-decoration: none;\r\n" + 
						"}\r\n" + 
						"\r\n" + 
						".dropdown {\r\n" + 
						"    float: right;\r\n" + 
						"    overflow: hidden;\r\n" + 
						"}\r\n" + 
						"\r\n" + 
						".dropdown .dropbtn {\r\n" + 
						"    font-size: 20px;    \r\n" + 
						"    border: none;\r\n" + 
						"    outline: none;\r\n" + 
						"    color: white;\r\n" + 
						"    padding: 14px 16px;\r\n" + 
						"    background-color: inherit;\r\n" + 
						"    font-family: inherit;\r\n" + 
						"    margin: 0;\r\n" + 
						"}\r\n" + 
						"\r\n" + 
						".navbar a:hover, .dropdown:hover .dropbtn {\r\n" + 
						"    background-color: red;\r\n" + 
						"}\r\n" + 
						"\r\n" + 
						".dropdown-content {\r\n" + 
						"    display: none;\r\n" + 
						"    position: absolute;\r\n" + 
						"    background-color: #f9f9f9;\r\n" + 
						"    min-width: 160px;\r\n" + 
						"    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);\r\n" + 
						"    z-index: 1;\r\n" + 
						"}\r\n" + 
						"\r\n" + 
						".dropdown-content a {\r\n" + 
						"    float: none;\r\n" + 
						"    color: black;\r\n" + 
						"    padding: 12px 16px;\r\n" + 
						"    text-decoration: none;\r\n" + 
						"    display: block;\r\n" + 
						"    text-align: left;\r\n" + 
						"}\r\n" + 
						"\r\n" + 
						".dropdown-content a:hover {\r\n" + 
						"    background-color: #ddd;\r\n" + 
						"}\r\n" + 
						"\r\n" + 
						".dropdown:hover .dropdown-content {\r\n" + 
						"    display: block;\r\n" + 
						"}");
				pw.println(".a{\r\n" + 
						"    background-color: red; \r\n" + 
						"    border: none;\r\n" + 
						"    color: white;\r\n" + 
						"    padding: 15px 32px;\r\n" + 
						"    text-align: center;\r\n" + 
						"    text-decoration: none;\r\n" + 
						"    display: inline-block;\r\n" + 
						"    font-size: 16px;\r\n" + 
						"    margin: 4px 2px;\r\n" + 
						"    cursor: pointer;\r\n" + 
						"}");
				pw.println("</style>");
				pw.println("<header>\r\n" + 
						"<div class=\"navbar\">\r\n" + 
						"  <a href=\"login.html\">     HOME  &nbsp&nbsp&nbsp&nbsp  |</a>                  &nbsp&nbsp\r\n" + 
						"  <a href=\"wallet.html\">WALLET  &nbsp&nbsp&nbsp&nbsp |</a>       &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp\r\n" + 
						"  <a href=\"T & C.html\">TERMS & CONDITIONS  &nbsp&nbsp&nbsp&nbsp  |</a>                   &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp\r\n" + 
						"  <a href=\"about.html\">ABOUT US  &nbsp&nbsp&nbsp&nbsp  |</a> 			 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp\r\n" + 
						"\r\n" + 
						" <div class=\"dropdown\">\r\n" + 
						"    <button class=\"dropbtn\">DROPDOWN \r\n" + 
						"      <i class=\"fa fa-caret-down\"></i>\r\n" + 
						"    </button>\r\n" + 
						"    <div class=\"dropdown-content\">\r\n" + 
						"      <a href=\"login.html\">HOME</a>\r\n" + 
						"      <a href=\"logout.html\">LOGOUT</a>" +
						"    </div>\r\n" + 
						"  </div>\r\n" + 
						"</div>\r\n" + 
						"</header>");
				pw.println("<center><b><font size=\"8\">MY PROFILE</font></b></center>");
				pw.println("<div class=\"container\">");
				pw.println("<img src=\"images/C.jpg\" style=\"width:100%;\">");
				pw.println("<div class=\"centered\">");
				pw.println("<font size=8; text-align: left;>");
				pw.println("<b><u>Name:</u></b>  "+rs1.getString(1));
				pw.println("<br>");
				pw.println("<b><u>Account:</u></b>  "+rs1.getString("ac"));
				pw.println("<br>");
				pw.println("<b><u>Account no:</u></b>   "+rs1.getString("accn"));
				pw.println("<br>");
				pw.println("<b><u>Email:</u></b>   "+rs1.getString("email"));
				pw.println("<br>");
				pw.println("<b><u>Gender:</u></b>   "+rs1.getString("gender"));
				pw.println("<br>");
				pw.println("<b><u>Date of Birth:</u></b>   "+rs1.getString("dob"));
				pw.println("<br>");
				pw.println("<b><u>City:</u></b>   "+rs1.getString("city"));
				pw.println("<br>");
				pw.println("<b><u>Contact:</u></b>   "+rs1.getString("contact"));
				pw.println("<br>");
				pw.println("<b><u>Address:</u></b>   "+rs1.getString("address"));
				pw.println("</font>");
				pw.println("<br><br>");
				pw.println("<center><a href=\"login.html\"><input type=\"submit\" value=\"BACK\" class=\"a\"></a></center>");
				
				pw.println("</div>");
				pw.println("</div>");
				
				pw.println("</body>");
				pw.println("</html>");
			}
			
			else
			{
				session.invalidate();
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
