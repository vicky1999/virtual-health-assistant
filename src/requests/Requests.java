package requests;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/requests")
public class Requests extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Requests() {
        super();
    }
	
	String url="jdbc:mysql://localhost:3306/vhealth";
	String username="vhealth";
	String password="vhealth";
	
	public List<String[]> getRequests(String from,String role) {
		System.out.println(from);
		System.out.println(role);
		
		List<String[]> res=new ArrayList<String[]>();
		try {
			String sql="SELECT * FROM requests where receiver=? and role=?";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,username,password);
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1,from);
			stmt.setString(2,role);
			ResultSet rs=stmt.executeQuery();
			System.out.println(rs.getFetchSize()); 	
			 while(rs.next()){
		         //Retrieve by column name
				 String[] temp=new String[3];
		         temp[0] = rs.getString("sender");
		         temp[1] = rs.getString("receiver");
		         temp[2] = rs.getString("message");
		         System.out.println(temp[2]);
		         res.add(temp);
			 }
		}
		catch(Exception e) {}
		return res;
	}


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession sess=request.getSession();
    	java.util.List<String[]> data=getRequests((String)sess.getAttribute("mail"),(String)sess.getAttribute("role")); 
    	System.out.println("Role: "+(String) sess.getAttribute("role"));
    	System.out.println("Mail: "+(String) sess.getAttribute("mail"));
    	
		try{
	    	String html="<!DOCTYPE html>\r\n" + 
	    			"	<html>\r\n" + 
	    			"	<head>\r\n" + 
	    			"	<meta charset=\"ISO-8859-1\">\r\n" + 
	    			"	<title>Insert title here</title>\r\n"
	    			+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
	    			"	<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\">\r\n" + 
	    			"	<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n" + 
	    			"	<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\r\n" + 
	    			"	<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js\"></script>\r\n" + 
	    			"	</head>\r\n" + 
	    			"<style>table,thead,th,td,tr {border: 1px solid black; border-collapse:  collapse;}"
	    			+ "table { text-align: center;margin-top: 50px;}"
	    			+ "thead{background-color: gray;color: white;}"
	    			+ "</style> \r\n" + 
	    			"	<body>\r\n" + 
	    			"		<script> function setcookie(data) { document.cookie =\" to=\"+data; return true; }</script> \r\n"+
	    			"<div class=\"bs-example container\" data-example-id=\"striped-table\">\r\n" + 
	    			"  <center><table class=\"table table-striped table-bordered table-hover\">\r\n" + 
	    			"    <thead>\r\n" + 
	    			"		  	<tr>\r\n" + 
	    			"		  		<th>Sender</th>\r\n" + 
	    			"			  	<th>Receiver</th>\r\n" + 
	    			"			  	<th>Message</th>\r\n" + 
	    			"			</tr>\r\n" + 
	    			"	</thead>\r\n" +
	    			"<tbody> \r\n" +
	    			"			<tr>\r\n";
	    					      for(int i=0;i<data.size();i++) {  
	    					    	  html+=" <tr>\r\n"; 
	    								   String[] val=data.get(i); 
	    									for(int j=0;j<val.length;j++) {  
	    										html+="<td>"+val[j]+"</td>\r\n"; 
	    									}  
	    			html+="</tr>\r\n";
	    								}
	    			html+="			</tr>\r\n" + 
	    			"  		</tbody> \r\n" +	
	    			"		  </table></center>\r\n" + 
	    			"	</body>\r\n" + 
	    			"	</html>";
	       response.setContentType("text/html");
	       PrintWriter writer = response.getWriter();
	       writer.print(html);
	       writer.close();
	    }
	    catch(Exception e) {}
    }

}
