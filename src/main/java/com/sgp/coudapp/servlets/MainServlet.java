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

package com.sgp.coudapp.servlets;

import java.io.*;
import java.sql.*;
//import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sgp.coudapp.pojos.*;
import com.sgp.coudapp.logic.*;
import java.util.Random;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.sgp.coudapp.datahandling.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

@WebServlet(description = "Core Servlet For Begining all Operations", urlPatterns = { "/mainServlet" })
final public class MainServlet extends HttpServlet  
{
	private static final long serialVersionUID = 1L;
	private static Connection con = null;
	private UserDetails ud = new UserDetails();
	private OrderDetails od = new OrderDetails();
	private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static InetAddress ip ;
	@SuppressWarnings("unused")
	private static String ipadd = "" ;
	
	
	@Override
	public void init()
	{
		if(con == null)
     	   startDBConnection();
		setInetAddress();
	}
	
	private static void setInetAddress()
	{
		try{
			 ip = InetAddress.getLocalHost();
			 ipadd = ip.getHostAddress();
		}catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
           Update.updtOdStat(request.getParameter("orderid"), request.getParameter("delevdate"), request.getParameter("status"), con);
           Emails.DelvCnfEml(request.getParameter("usrname"), request.getParameter("price"), request.getParameter("proname"), request.getParameter("orderid"), request.getParameter("delevdate"),request.getParameter("eml"));
           response.setContentType("text");
   		   response.setCharacterEncoding("UTF-8");
   		   response.setBufferSize(8192);
   		   PrintWriter out = response.getWriter();
   		   out.println("Delivery Transaction, Successfully Completed!!");
   		   out.flush();
   		   out.close();
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setBufferSize(8192);
		PrintWriter out = response.getWriter(); 
		Boolean yu = assignUD(request);
		System.out.println("yu - "+yu);
		if(yu==true)
		{
			int cs = Checker.getTCN();
			switch (cs)
			{
			     case 1 :  //user details already exist in the db-system;
			    	       if(Checker.isAddressExists(request.getParameter("locality"),request.getParameter("city"),request.getParameter("pin"),request.getParameter("state"),request.getParameter("country"),con) == false)
			    	       {
			    	    	   if(Checker.adc==1)
			    	    	   {
			    	    		    System.out.println("ERROR!!");
			    	    		    out.println("<html lang=\"en\">"
			    	    		    		      + "<head><title>SQL ERROR!!</title></head>"
			    	    		    		      + "<body bgcolor=\"#ffffff\">"
			    	    		    		      + "<h1>FAILED!!! Error Occured_database exception.</h1>"
			    	    		    		      + "<p>Please click the button to return to the orderpage --> " +cs+"</p>"
			    	    		    		      + "<a href=\"http://"+ipadd+":8080/coudApp/orderform.html\"><input type=\"button\" value=\"Return\"></a>"
			    	    		    		      + "</body></html>");
			    	    		    out.flush();
			    	    		    out.close();
                                    //out.response _ error;
			    	    	   }
			    	    	   else
			    	    	   {
			    	    	        assignAD(request);
			    	    	        assignOD(request.getParameter("products"),Checker.EXISTINGUSER_UID,ud.ad.getAdd_id());
			    	    	        Insert.insertAD(ud,con,Checker.EXISTINGUSER_UID);
			    	    	        Insert.insertOD(od,con);
			    	    	         String address = request.getParameter("locality")+", "+request.getParameter("city")+" "+request.getParameter("pin")+", "+request.getParameter("state")+", "+request.getParameter("country");
			    	    	         String nm = request.getParameter("custofname")+" "+request.getParameter("custolname");
			    	    	         String pid=od.getProduct_id();
			    	    			 String uid=od.getUsr_id();
			    	    			 String oid=od.getOd_id();
			    	    			 String odt=od.getOd_date();
			    	    			 String ddt=od.getDelv_date();
			    	    			 String st=od.getStatus();
			    	    			 String pnm=request.getParameter("products");
			    	    			 String eml=request.getParameter("Email");
			    	    			 Emails.OdCnfEml(nm,uid,pid,pnm,oid,odt,ddt,st,address,eml);
			    	    	         out.println("<html lang=\"en\">"
	    	    		    		              + "<head><title>ORDER SUCCESSFUL!!</title></head>"
	    	    		    		              + "<body bgcolor=\"#ffffff\">"
	    	    		    		    	      + "<h1>Order successfully placed!!.</h1>"
	    	    		    		    	      + "<p>Hi there recurring buyer!!<br/>We have registered your new address.<br/>Please click the button to view your order_summary --></p>"
	    	    		    		              + "<a href=\"http://"+ipadd+":8080/coudApp/ordersummary.html?Od_id="+oid+"&Pr_id="+pid+"&Pr_name="+pnm+"&Ur_nm="+nm+"&Ur_id="+uid+"&Addr="+address+"&Stat="+st+"&Odt="+odt+"&Ddt="+ddt+"\">"+"<input type=\"button\" value=\"View\"></a>"
	    	    		    		    	      + "</body></html>");
			    	    	        out.flush();
			    	    		    out.close();
			    	    	        //out.response stuff -> goes here;
			    	    	   }
			    	       }
			    	       else
			    	       {
			    	    	   assignOD(request.getParameter("products"),Checker.EXISTINGUSER_UID,Checker.EXISTINGAD_ID);
			    	    	   Insert.insertOD(od,con);
			    	    	   String address = request.getParameter("locality")+", "+request.getParameter("city")+" "+request.getParameter("pin")+", "+request.getParameter("state")+", "+request.getParameter("country");
		    	    	         String nm = request.getParameter("custofname")+" "+request.getParameter("custolname");
		    	    	         String pid=od.getProduct_id();
		    	    			 String uid=od.getUsr_id();
		    	    			 String oid=od.getOd_id();
		    	    			 String odt=od.getOd_date();
		    	    			 String ddt=od.getDelv_date();
		    	    			 String st=od.getStatus();
		    	    			 String pnm=request.getParameter("products");
		    	    			 String eml=request.getParameter("Email");
		    	    			 Emails.OdCnfEml(nm,uid,pid,pnm,oid,odt,ddt,st,address,eml);
			    	    	   out.println("<html lang=\"en\">"
 	    		    		                 + "<head><title>ORDER SUCCESSFUL!!</title></head>"
 	    		    		                 + "<body bgcolor=\"#ffffff\">"
 	    		    		    	         + "<h1>Order successfully placed!!.</h1>"
 	    		    		    	         + "<p>Hi there recurring buyer!!<br/>Please click the button to view your order_summary --></p>"
 	    		    		                 + "<a href=\"http://"+ipadd+":8080/coudApp/ordersummary.html?Od_id="+oid+"&Pr_id="+pid+"&Pr_name="+pnm+"&Ur_nm="+nm+"&Ur_id="+uid+"&Addr="+address+"&Stat="+st+"&Odt="+odt+"&Ddt="+ddt+"\">"+"<input type=\"button\" value=\"View\"></a>"
 	    		    		    	         + "</body></html>");
			    	    	    out.flush();
			    			    out.close();
			    	    	    //out.response stuff -> goes here;
			    	       }
			    	       break;
			    default : //default case new user ... add details to db;
			    	      assignAD(request);
			    	      assignOD(request.getParameter("products"),ud.getUsr_id(),ud.ad.getAdd_id());
			    	      Insert.insertUD(ud,con);
			    	      Insert.insertAD(ud,con);
			    	      Insert.insertOD(od,con);
			    	      String address = request.getParameter("locality")+", "+request.getParameter("city")+" "+request.getParameter("pin")+", "+request.getParameter("state")+", "+request.getParameter("country");
	    	    	         String nm = request.getParameter("custofname")+" "+request.getParameter("custolname");
	    	    	         String pid=od.getProduct_id();
	    	    			 String uid=od.getUsr_id();
	    	    			 String oid=od.getOd_id();
	    	    			 String odt=od.getOd_date();
	    	    			 String ddt=od.getDelv_date();
	    	    			 String st=od.getStatus();
	    	    			 String pnm=request.getParameter("products");
	    	    			 String eml=request.getParameter("Email");
	    	    			 Emails.OdCnfEml(nm,uid,pid,pnm,oid,odt,ddt,st,address,eml);
			    	      out.println("<html lang=\"en\">"
    		    		                + "<head><title>ORDER SUCCESSFUL!!</title></head>"
    		    		                + "<body bgcolor=\"#ffffff\">"
    		    		    	        + "<h1>Order successfully placed!!.</h1>"
    		    		    	        + "<p>Hi there new user!!<br/>We have registered you in our database.<br/>Please click the button to view your order_summary --></p>"
    		    		                + "<a href=\"http://"+ipadd+":8080/coudApp/ordersummary.html?Od_id="+oid+"&Pr_id="+pid+"&Pr_name="+pnm+"&Ur_nm="+nm+"&Ur_id="+uid+"&Addr="+address+"&Stat="+st+"&Odt="+odt+"&Ddt="+ddt+"\">"+"<input type=\"button\" value=\"View\"></a>"
    		    		    	        + "</body></html>");
			    	      out.flush();
			  		      out.close();
			    	      //out.response stuff -> goes here;
			    	      break;
		   }
		}
		else
		{
			int cs = Checker.getFCN();
			switch (cs)
			{
			     case 1 : out.println("<html lang=\"en\">"
	    		                        + "<head><title>Mobile No Exists!!</title></head>"
	    		                        + "<body bgcolor=\"#ffffff\">"
	    		    	                + "<h1>ORDER FAILED!!!</h1>"
	    		    	                + "<p>The mobile number you entered already exists in our system.<br/>"
	    		    	                + "Please enter anothe mob no..<br/>Please click the button to return to the orderpage --></p>"
	    		                        + "<a href=\"http://"+ipadd+":8080/coudApp/orderform.html\"><input type=\"button\" value=\"Return\"></a>"
	    		    	                + "</body></html>");
			              out.flush();
				          out.close();
			    	      //given mob no exists;
			    	      //out.respose stuff _ goes here;
			    	      break;
			     case 2 : out.println("<html lang=\"en\">"
	    		                        + "<head><title>Email Id. Exists!!</title></head>"
	    		                        + "<body bgcolor=\"#ffffff\">"
	    		    	                + "<h1>ORDER FAILED!!!</h1>"
	    		    	                + "<p>The email id. you entered already exists in our system.<br/>"
	    		    	                + "Please enter anothe email id..<br/>Please click the button to return to the orderpage --></p>"
	    		                        + "<a href=\"http://"+ipadd+":8080/coudApp/orderform.html\"><input type=\"button\" value=\"Return\"></a>"
	    		    	                + "</body></html>");
			              out.flush();
				          out.close();
			    	      //given email exists;
			    	      //out.response stuff _ goes here;
			    	      break;
			     case 3 : out.println("<html lang=\"en\">"
	    		                        + "<head><title>Mobile No And Email Id. Exists!!</title></head>"
	    		                        + "<body bgcolor=\"#ffffff\">"
	    		    	                + "<h1>ORDER FAILED!!!</h1>"
	    		    	                + "<p>The mobile number and email id you entered already exists in our system.<br/>"
	    		    	                + "Please enter anothe mob no.. and email id..<br/>Please click the button to return to the orderpage --></p>"
	    		                        + "<a href=\"http://"+ipadd+":8080/coudApp/orderform.html\"><input type=\"button\" value=\"Return\"></a>"
	    		    	                + "</body></html>");
			              out.flush();
				          out.close();
			    	      // both given mobno and email exist;
			    	      // out.response stuff goes _ here; 
			              break;
			    default : out.println("<html lang=\"en\">"
	    		                        + "<head><title>SQL ERROR!!</title></head>"
	    		                        + "<body bgcolor=\"#ffffff\">"
	    		    	                + "<h1>FAILED!!! Error Occured_database exception.</h1>"
	    		    	                + "<p>Please click the button to return to the orderpage --> "+cs+"</p>"
	    		                        + "<a href=\"http://"+ipadd+":8080/coudApp/orderform.html\"><input type=\"button\" value=\"Return\"></a>"
	    		    	                + "</body></html>");
			              out.flush();
			              out.close();
			    	      //error case;
			    	      //out.response stuff _ goes here;
			              break;
			}
		}
	}
    
	public static Connection getCon()
    {
    	if(con == null)
    		startDBConnection();
    	return (con);
    }
    
	@Override
	public void destroy() 
	{
		stopDBConnection();
		ud = null;
		od = null;
	}
	
	private static void startDBConnection()
	{
		// JDBC driver name and database URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		final String DB_URL = "jdbc:mysql://mysql:3306/coudApp";

		//Database credentials
		final String USER = "apsalladmin";
	              final String PASS = "apsadmin3";
	    
	    try
	    {
	    	//Register JDBC driver
	        Class.forName(JDBC_DRIVER).newInstance();

	        //Start connection
	        System.out.println("Connecting to database...");
	        con = DriverManager.getConnection(DB_URL,USER,PASS);
	    }
	    catch(SQLException se)
	         {
	             //Handle errors for JDBC
	               se.printStackTrace();
	               con = null;
	         }
	    catch(Exception e)
	         {
	             //Handle errors for Class.forName
	               e.printStackTrace();
	               con = null;
	         }
	    finally
	        {
	             //finally block used to check connection
	             if(con != null)
	             {
	        	       System.out.println("Connection Successful!!");
	             }
	             else
	             {
	        	      System.out.println("Connection Unsuccessful!!");
	        	      try
	        	      {
	        		        con.close();
	        	      }catch(SQLException se){
	        		        se.printStackTrace();
	        	           }
	        	      con = null;
	             }
	        }
	 }
	
	private void stopDBConnection()
	{
		try
	      {
		     con.close();
	      }catch(SQLException se){
		        se.printStackTrace();
	           }
	      con = null;
		
	}
	
	private boolean assignUD(HttpServletRequest request)
	{
	    Checker chk = new Checker();
		Boolean mob = chk.checkMobno(Long.parseLong(request.getParameter("mobno")),con);
		Boolean email = chk.checkEml(request.getParameter("Email"), con);
		
		System.out.println("Mob - "+mob);
		System.out.println("Email -"+email);
		System.out.println(Checker.getTCN());
		
		if(mob == true && email == false)
		   return (false);
		if(mob == false && email == true)
		   return (false);
		if(mob == true && email == true)
		{
			Boolean nameagenm = chk.checkName(request.getParameter("custofname")+" "+request.getParameter("custolname"),request.getParameter("Email"),Long.parseLong(request.getParameter("mobno")),con);
			System.out.println("Nm -"+nameagenm);
			if(nameagenm == true)
				return (true);
			else
			    return (false);
		}
		else
		{
		    ud.setFirstname(request.getParameter("custofname"));
		    ud.setLastname(request.getParameter("custolname"));
		    ud.setMob_no(Long.parseLong(request.getParameter("mobno")));
		    ud.setEmail_id(request.getParameter("Email"));
		    if(generateUID() == null)
		    {
		    	System.out.println("Eureka");
		        return (false);
		    }
		    else
		    {
		        ud.setUsr_id(generateUID());
		        return (true);
		    }
		}
	}
	
	private void assignAD(HttpServletRequest request)
	{
		String loc = request.getParameter("locality");
		String city = request.getParameter("city");
		String pin = request.getParameter("pin");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String ad_id = generateAID();
		ud.ad.setLocality(loc);
		ud.ad.setCity(city);
		ud.ad.setPincode(pin);
		ud.ad.setState(state);
		ud.ad.setCountry(country);
		ud.ad.setAdd_id(ad_id);
	}
	
	private void assignOD(String pname,String uid,String aid)
	{
		 String pid = Checker.getProductId(pname,con);
		 od.setProduct_id(pid);
		 od.setUsr_id(uid);
		 od.setOd_id(generateOD_ID());
		 od.setOd_date(sdf.format(new Date()));
		 od.setDelv_date("N/A");
		 od.setStatus("In Transit");
		 od.setAd_id(aid);
    }
	

	private String generateUID() 
	{
		Random r = new Random ();
		int x = r.nextInt(50000);
		String uid = "UID"+x;
		if (Checker.isExistsUID(uid, con) == true)
			return (generateUID());
		else
		{
			if (Checker.getFCN() == 7)
				return (null);
			else
				return (uid);
		}
    }
	
	private String generateOD_ID() 
	{
		Random r = new Random ();
		int x = r.nextInt(50000);
		String oid = "OID"+x;
		      return (oid);
	}
	
	private String generateAID() 
	{
		Random r = new Random ();
		int x = r.nextInt(50000);
		String aid = "AID"+x;
		      return (aid);
	}
 }
