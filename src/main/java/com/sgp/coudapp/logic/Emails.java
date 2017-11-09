/*  -> Designed for testing and development purposes.
 *  -> Project to design a small eLogistics prototype.
 *  -> Development Phase -- Premature.
 *  -> Project Type -- Educational.
 *  -> Institute -- University Institute Of Technology, Burdwan University.
 *  -> Owner/Code file Designer :
 *             @ Name - Palash Sarkar.
 *             @ Department - Computer Science And Engineering.
 *             @ Roll.Number - 2014_1038.
 *             @ Email - palashsarkar0007@gmail.com.
 *  -> Copyright Norms - Every piece of code given below 
 *                       has been written by 'Palash Sarkar (Tj07)'©,
 *                       and he holds the rights to the file. Not meant to be
 *                       copied or tampered without prior permission
 *                       from the author.
 *  -> Guide - Asst.Proff. Dr. Sumit Gupta.             
 */

package com.sgp.coudapp.logic;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;

public final class Emails 
{
     
	 public static void OdCnfEml(String nm,String uid,String pid,String pnm,String oid,String odt,String ddt,String st,String addr,String eml)
	 {
		 try
		   {
	            String host ="smtp.gmail.com" ;
	            String user = "dm.flashcartteam97@gmail.com";
	            String pass = "flctdelev123#";
	            String to = eml;
	            String from = "dm.flashcartteam97@gmail.com";
	            String subject = "Order Summary---";
	            boolean sessionDebug = false;

	            Properties props = System.getProperties();

	            props.put("mail.smtp.starttls.enable", "true");
	            props.put("mail.smtp.host", host);
	            props.put("mail.smtp.port", "587");
	            props.put("mail.smtp.auth", "true");
	            props.put("mail.smtp.starttls.required", "true");
	            props.put("mail.user", user);
	            props.put("mail.password", pass);

	            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	            Session mailSession = Session.getDefaultInstance(props, null);
	            mailSession.setDebug(sessionDebug);
	            Message msg = new MimeMessage(mailSession);
	            msg.setFrom(new InternetAddress(from));
	            InternetAddress[] address = {new InternetAddress(to)};
	            msg.setRecipients(Message.RecipientType.TO, address);
	            msg.setSubject(subject); msg.setSentDate(new Date());
	            msg.setContent("<h2>Order Status--</h4>"
	            		         +"<h4>"+"CustomerName - "+nm+"</h4>"
	            		         +"<h4>"+"CustomerId - "+uid+"</h4>"
	            		         +"<h4>"+"DeleveryAddress - "+addr+"</h4>"
	            		         +"<h4>"+"ProductId - "+pid+"</h4>"
	            		         +"<h4>"+"ProductName - "+pnm+"</h4>"
	            		         +"<h4>"+"OrderId - "+oid+"</h4>"
	            		         +"<h4>"+"Placed - "+odt+"</h4>"
	            		         +"<h4>"+"Status - "+st+"</h4>"
	            		         +"<h4>"+"DeleveryDate - "+ddt+"</h4>", "text/html");
	            
                Transport transport=mailSession.getTransport("smtp");
	            transport.connect(host, user, pass);
	            transport.sendMessage(msg, msg.getAllRecipients());
	            transport.close();
	            System.out.println("Message sent successfully!!");
	          }catch(Exception ex)
	               {
	                    System.out.println(ex);
	               }
	 }
	 
	 public static void DelvCnfEml(String nm,String price,String pnm,String oid,String ddt,String eml)
	 {
		 try
		   {
	            String host ="smtp.gmail.com" ;
	            String user = "dm.flashcartteam97@gmail.com";
	            String pass = "flctdelev123#";
	            String to = eml;
	            String from = "dm.flashcartteam97@gmail.com";
	            String subject = "Delivery Details---";
	            String messageText = "Dear "+nm+",\n Your order for "+pnm+" ("+oid+") "+"costing Rs."+price+" was sucessfully delivered on "+ddt+"\n thank you for choosing Flashcart.";
	            boolean sessionDebug = false;

	            Properties props = System.getProperties();

	            props.put("mail.smtp.starttls.enable", "true");
	            props.put("mail.smtp.host", host);
	            props.put("mail.smtp.port", "587");
	            props.put("mail.smtp.auth", "true");
	            props.put("mail.smtp.starttls.required", "true");
	            props.put("mail.user", user);
	            props.put("mail.password", pass);

	            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	            Session mailSession = Session.getDefaultInstance(props, null);
	            mailSession.setDebug(sessionDebug);
	            Message msg = new MimeMessage(mailSession);
	            msg.setFrom(new InternetAddress(from));
	            InternetAddress[] address = {new InternetAddress(to)};
	            msg.setRecipients(Message.RecipientType.TO, address);
	            msg.setSubject(subject); msg.setSentDate(new Date());
	            msg.setText(messageText);
	            
                Transport transport=mailSession.getTransport("smtp");
	            transport.connect(host, user, pass);
	            transport.sendMessage(msg, msg.getAllRecipients());
	            transport.close();
	            System.out.println("Message sent successfully!!");
	          }catch(Exception ex)
	               {
	                    System.out.println(ex);
	               }
	 }
	 
 }
