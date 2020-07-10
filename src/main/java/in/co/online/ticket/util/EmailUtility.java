package in.co.online.ticket.util;


import java.util.Properties;
import java.util.ResourceBundle;
 

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import in.co.online.ticket.exception.ApplicationException;

 
/**
 * Email Utility provides Email Services
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 * 
 */

public class EmailUtility
{
	/**
     * Create Resource Bundle to read properties file
     */
    static ResourceBundle rb = ResourceBundle.getBundle("in.co.online.ticket.bundle.system");
 
    /**
     * Email Server
     */
    private static final String SMTP_HOST_NAME = rb.getString("smtp.server");
 
    /**
     * Email Server Port
     */
    private static final String SMTP_PORT = rb.getString("smtp.port");
 
    /**
     * Session Factory, A session is a connection to email server.
     */
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
 
    /**
     * Administrator's email id by which all messages are sent
     */
    private static final String emailFromAddress = rb.getString("email.login");
 
    /**
     * Administrator email's password
     */
    private static final String emailPassword = rb.getString("email.pwd");
 
    /**
     * Email server properties
     */
 
    private static Properties props = new Properties();
 
    /**
     * Static block to initialize static parameters
     */
    static {
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
    }
 
    /**
     * Sends an Email
     * 
     * @param emailMessageDTO
     *            : Email message
     * @throws ApplicationException
     */
    public static void sendMail(EmailMessage emailMessageDTO) throws ApplicationException{
 
        try {
 
            // Connection to Mail Server
            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(emailFromAddress,emailPassword);
                        }
                    });
 
            // Make debug mode true to display debug messages at console
            session.setDebug(true);
 
            // Create a message
            Message msg = new MimeMessage(session);
            InternetAddress addressFrom = new InternetAddress(emailFromAddress);
            msg.setFrom(addressFrom);
 
            // Set TO addresses
            String[] emailIds = new String[0];
 
            if (emailMessageDTO.getTo() != null) {
                emailIds = emailMessageDTO.getTo().split(",");
            }
 
            // Set CC addresses
            String[] emailIdsCc = new String[0];
 
            if (emailMessageDTO.getCc() != null) {
                emailIdsCc = emailMessageDTO.getCc().split(",");
            }
 
            // Set BCC addresses
            String[] emailIdsBcc = new String[0];
 
            if (emailMessageDTO.getBcc() != null) {
                emailIdsBcc = emailMessageDTO.getBcc().split(",");
            }
 
            InternetAddress[] addressTo = new InternetAddress[emailIds.length];
 
            for (int i = 0; i < emailIds.length; i++) {
                addressTo[i] = new InternetAddress(emailIds[i]);
            }
 
            InternetAddress[] addressCc = new InternetAddress[emailIdsCc.length];
 
            for (int i = 0; i < emailIdsCc.length; i++) {
                addressCc[i] = new InternetAddress(emailIdsCc[i]);
            }
 
            InternetAddress[] addressBcc = new InternetAddress[emailIdsBcc.length];
 
            for (int i = 0; i < emailIdsBcc.length; i++) {
                addressBcc[i] = new InternetAddress(emailIdsBcc[i]);
            }
 
            if (addressTo.length > 0) {
                msg.setRecipients(Message.RecipientType.TO, addressTo);
            }
 
            if (addressCc.length > 0) {
                msg.setRecipients(Message.RecipientType.CC, addressCc);
            }
 
            if (addressBcc.length > 0) {
                msg.setRecipients(Message.RecipientType.BCC, addressBcc);
            }
 
            // Setting the Subject and Content Type
            msg.setSubject(emailMessageDTO.getSubject());
 
            // Set message MIME type
            switch (emailMessageDTO.getMessageType()) {
            case EmailMessage.HTML_MSG:
                msg.setContent(emailMessageDTO.getMessage(), "text/html");
                break;
            case EmailMessage.TEXT_MSG:
                msg.setContent(emailMessageDTO.getMessage(), "text/plain");
                break;
 
            }
 
            // Send the mail
            Transport.send(msg);
 
        } catch (Exception ex) {
            throw new ApplicationException("Email " + ex.getMessage());
        }	
    }
}
