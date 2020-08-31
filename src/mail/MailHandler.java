package mail;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.Part;
import javax.mail.internet.*;

public class MailHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String mail_id="vhealth4@gmail.com";
	private final String pass="vhealth123";
	
	String to="";
	
	public void insert_request(String role,String from,String to,String message) {
		String url="jdbc:mysql://localhost:3306/vhealth";
		String username="vhealth";
		String password="vhealth";
		String sql="INSERT INTO requests VALUES(?,?,?,?)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection(url,username,password);
				PreparedStatement stmt=con.prepareStatement(sql);
				stmt.setString(1,from);
				stmt.setString(2, to);
				stmt.setString(3,message);
				stmt.setString(4,role);
				int created=stmt.executeUpdate();
				return;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
	    
		HttpSession sess=req.getSession();
        String role=(String) sess.getAttribute("role");
        String txt="";
        
        Properties props = new Properties();    
		Cookie[] cookies=req.getCookies();
		 PrintWriter out = res.getWriter(); 
			if( cookies != null ) {
		        Cookie cookie;
		        for (int i = 0; i < cookies.length; i++) {
		           cookie = cookies[i];
		           if(cookie.getName().contentEquals("to")) {
		        	   to=cookie.getValue();
		        	   break;
		        }
		     }
			}else {
		    	 res.sendRedirect("/doctors");
		        System.out.print("<h2>No cookies founds</h2>");
		     }

		props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
        //get Session   
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication(mail_id,pass);  
         }    
        });    
        //compose message    
        if(role.equalsIgnoreCase("user")) {
        	txt="Hi Dr., \n  There is an appointment request from the patient.  \n\nThe patient details were given below. "
				+ "\nName : "+sess.getAttribute("username")+"\nMail ID: "+sess.getAttribute("mail")+"\nMobile Number: "+sess.getAttribute("mobile")
						+ "\n\nMessage from the patient \n\n"+req.getParameter("message");
	        insert_request("doctor",(String)sess.getAttribute("mail"),to,req.getParameter("message"));    
        	try {    
	                MimeMessage message = new MimeMessage(session);    
	                message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	                message.setSubject("Appointment Request");
	                message.setText(txt);    
	                //send message  
	                Transport.send(message);    
	                res.setContentType("text/html");
	                if(role.equalsIgnoreCase("user")) {
	               	 out.println("<HTML><HEAD><TITLE>Request Sent</TITLE></HEAD><BODY<h1>Your Appointment request has been sent to the doctor Successfully</h1></BODY></HTML>");
	                }
	                else if(role.equalsIgnoreCase("doctor")) {
	               	 out.println("<HTML><HEAD><TITLE>Request Sent</TITLE></HEAD><BODY<h1>Your request has been sent to the lab technician Successfully</h1></BODY></HTML>");
	                }
	                 out.close();
	                
	               } catch (MessagingException e) {throw new RuntimeException(e);}    
        }
        else if(role.equalsIgnoreCase("doctor")) {
        	txt="Hi, We got a lab test request from Dr."+sess.getAttribute("username")+" \n\nThe patient details were given below. \r\n" + 
        			"\nName : "+sess.getAttribute("username")+"\nMail ID: "+sess.getAttribute("mail")+"\nMobile Number: "+sess.getAttribute("mobile")+" \n\nHere's the message from the doctor\n\n"+req.getParameter("message");
            try {    
                MimeMessage message = new MimeMessage(session);    
                message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
                message.setSubject("Appointment Request");
                message.setText(txt);    
                //send message  
                Transport.send(message);    
                res.setContentType("text/html");
                insert_request("lab",(String)sess.getAttribute("mail"),to,req.getParameter("message"));    
                if(role.equalsIgnoreCase("user")) {
               	 out.println("<HTML><HEAD><TITLE>Request Sent</TITLE></HEAD><BODY<h1>Your Appointment request has been sent to the doctor Successfully</h1><br/>"
               	 		+ "<a href=\"Doctor/index.jsp\">Home</ a></BODY></HTML>");
                }
                else if(role.equalsIgnoreCase("doctor")) {
               	 out.println("<HTML><HEAD><TITLE>Request Sent</TITLE></HEAD><BODY<h1>Your request has been sent to the lab technician Successfully</h1><br/>"
               	 		+ "<a href=\"Lab/index.jsp\">Home</a></BODY></HTML>");
                }
                 out.close();
                
               } catch (MessagingException e) {throw new RuntimeException(e);}    
        }
           	
	}
}
