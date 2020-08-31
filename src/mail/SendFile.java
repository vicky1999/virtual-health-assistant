package mail;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;


@MultipartConfig
@WebServlet("/send-file")
//@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
//                 maxFileSize=1024*1024*10,      // 10MB
//                 maxRequestSize=1024*1024*50)   // 50MB

public class SendFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String mail_id="vhealth4@gmail.com";
	String pass="vhealth123";
	
	 private boolean isMultipart;
	   private String filePath;
	   private int maxFileSize = 50 * 1024;
	   private int maxMemSize = 4 * 1024;
	   private File file ;

	   public void init( ){
	      // Get the file location where it would be stored.
	      filePath = getServletContext().getInitParameter("file-upload"); 
	   }
	   
	   public void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, java.io.IOException {
		   
		   Part filePart = request.getPart("file");
			
			//get the InputStream to store the file somewhere
		    InputStream fileInputStream = filePart.getInputStream();
		    //for example, you can copy the uploaded file to the server
		    //note that you probably don't want to do this in real life!
		    //upload it to a file host like S3 or GCS instead
		    File fileToSave = new File("F:\\Virtusa\\WebContent\\Uploads\\"+filePart.getSubmittedFileName());
			Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			//You can get other form data too
			String name = request.getParameter("name");
			response.getOutputStream().println("<p>Report sent Successfully</p>");	
			response.getOutputStream().println("<p>Upload another file <a href=\"http://localhost:8081/Virtual_Health_Assistant/send-report.jsp\">here</a>.</p>");	
		
			HttpSession sess=request.getSession();
	        String role=(String) sess.getAttribute("role");
	        String txt="";
	        
	        Properties props = new Properties();    
			 String to=request.getParameter("doctor");
				
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
		    	  try{  
	        		    MimeMessage message = new MimeMessage(session);
	        		    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	        		    String patient=request.getParameter("patient");
	        		    if(!patient.contentEquals("")) {
	        		    	message.addRecipient(Message.RecipientType.TO,new InternetAddress(patient));  
	        		    }
	        		    message.setSubject("Report details");  
	        		      
	        		    //3) create MimeBodyPart object an	d set your message text     
	        		    BodyPart messageBodyPart1 = new MimeBodyPart();  
	        		    messageBodyPart1.setText("Report Details");  
	        		      
	        		    //4) create new MimeBodyPart object and set DataHandler object to this object      
	        		    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
	        		  String filename=fileToSave.getPath();
	        		    DataSource source = new FileDataSource(filename);  
	        		    messageBodyPart2.setDataHandler(new DataHandler(source));  
	        		    messageBodyPart2.setFileName(filename);  
	        		             		     
	        		    //5) create Multipart object and add MimeBodyPart objects to this object      
	        		    Multipart multipart = new MimeMultipart();  
	        		    multipart.addBodyPart(messageBodyPart1);  
	        		    multipart.addBodyPart(messageBodyPart2);  
	        		  
	        		    //6) set the multiplart object to the message object  
	        		    message.setContent(multipart );  
	        		     
	        		    //7) send message  
	        		    Transport.send(message);  
	        		   
	        		   }catch (MessagingException ex) {ex.printStackTrace();}  
	   }
}

