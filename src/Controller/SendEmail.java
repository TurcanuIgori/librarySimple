package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendEmail
 */
public class SendEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/contact.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recipient's email ID needs to be mentioned.
	      String to = "tzurcanu93@mail.ru";
	 
	      // Sender's email ID needs to be mentioned
	      String from = "tzurcanu93@gmail.com";
	 
	      // Assuming you are sending email from localhost
	      String host = "localhost";
	 
	      // Get system properties
	      Properties properties = System.getProperties();
	 
	      // Setup mail server
	      properties.setProperty("mail.smtp.host", "smtp.gmail.com");
	      properties.setProperty("mail.smtp.username", "tzurcanu93@gmail.com");
	      properties.setProperty("mail.smtp.password", "password");
	      properties.setProperty("mail.smtp.defaultEncoding", "UTF-8");
	      properties.setProperty("mail.smtp.auth", "true");
	      properties.setProperty("mail.smtp.starttls.required", "true");
	      properties.setProperty("mail.smtp.starttls.enable", "true");
	      properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	      properties.setProperty("mail.smtp.socketFactory.fallback", "false");
	      properties.setProperty("mail.smtp.port", "465");
	      properties.setProperty("mail.smtp.socketFactory.port", "465");
		 
//		  mail.smtp.socketFactory.fallback=false
//		  mail.smtp.port=465
//		  mail.smtp.socketFactory.port=465
	      // Get the default Session object.
//	      Session session = Session.getDefaultInstance(properties);
	      Session session = Session.getDefaultInstance(properties,
	    	        new javax.mail.Authenticator() {
	    	            protected PasswordAuthentication getPasswordAuthentication() {
	    	                return new PasswordAuthentication("tzurcanu93@gmail.com",""password");
	    	            }
	    	        });
	      
		  // Set response content type
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);
	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));
	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));
	         // Set Subject: header field
	         message.setSubject("Message from: " + request.getParameter("firstName") + " email: " + request.getParameter("email") + " phone: " + request.getParameter("phone"));
	         // Now set the actual message
	         message.setText(request.getParameter("message"));
	         // Send message
	         Transport.send(message);
	         String title = "Send Email";
	         String res = "Sent message successfully....";
	         String docType =
	         "<!doctype html public \"-//w3c//dtd html 4.0 " +
	         "transitional//en\">\n";
	         out.println(docType +
	         "<html>\n" +
	         "<head><title>" + title + "</title></head>\n" +
	         "<body bgcolor=\"#f0f0f0\">\n" +
	         "<h1 align=\"center\">" + title + "</h1>\n" +
	         "<p align=\"center\">" + res + "</p>\n" +
	         "</body></html>");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	      request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

}
