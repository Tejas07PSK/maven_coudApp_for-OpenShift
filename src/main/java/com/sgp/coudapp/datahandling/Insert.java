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

package com.sgp.coudapp.datahandling;

import java.io.Serializable;

import com.sgp.coudapp.pojos.OrderDetails;
import com.sgp.coudapp.pojos.UserDetails;
import java.sql.*;

public class Insert implements Serializable
{
	 
    private static final long serialVersionUID = 1L;
	 
	 public static void insertUD(UserDetails ud,Connection con)
	 {
		  PreparedStatement smt = null;
		  String sql = "";
		  try
		    {
		       sql = "INSERT INTO USER_DETAILS ( FIRST_NAME , LAST_NAME , USER_ID , MOB_NO , EMAIL_ID ) VALUES ( ? , ? , ? , ? , ? )";
		       smt = con.prepareStatement(sql);
		       smt.setString(1 , ud.getFirstname());
		       smt.setString(2 , ud.getLastname());
		       smt.setString(3 , ud.getUsr_id());
		       smt.setLong(4, ud.getMob_no());
		       smt.setString(5 , ud.getEmail_id());
		       smt.execute();
		       smt.close();
		    }catch(SQLException se)
		           {
		    	       se.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	   }
		      catch(Exception e)
		           {
		    	       e.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	   }
		      finally
		           {
		    	      try
		    	        {
		    	           if(smt!=null)
		    	              smt.close();
		    	        }
		    	        catch(SQLException se2)
		    	        {
		    	        	se2.printStackTrace();
		    	        } 
		           }
	 }
	 
	 public static void insertAD(UserDetails ud,Connection con)
	 {
		  PreparedStatement smt = null;
		  String sql = "";
		  try
		    {
		       sql = "INSERT INTO ADDRESS_DETAILS ( UID , LOCALITY , CITY , PIN , STATE , COUNTRY , AD_ID ) VALUES ( ? , ? , ? , ? , ? , ? , ? )";
		       smt = con.prepareStatement(sql);
		       smt.setString(1 , ud.getUsr_id());
		       smt.setString(2 , ud.ad.getLocality());
		       smt.setString(3 , ud.ad.getCity());
		       smt.setString(4 , ud.ad.getPincode());
		       smt.setString(5 , ud.ad.getState());
		       smt.setString(6 , ud.ad.getCountry());
		       smt.setString(7 , ud.ad.getAdd_id());
		       smt.execute();
		       smt.close();
		    }catch(SQLException se)
		           {
		    	       se.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	   }
		      catch(Exception e)
		           {
		    	       e.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	   }
		      finally
		           {
		    	      try
		    	        {
		    	           if(smt!=null)
		    	              smt.close();
		    	        }
		    	        catch(SQLException se2)
		    	        {
		    	        	se2.printStackTrace();
		    	        } 
		           }
	 }
	 
	 public static void insertAD(UserDetails ud,Connection con,String uid)
	 {
		  PreparedStatement smt = null;
		  String sql = "";
		  try
		    {
		       sql = "INSERT INTO ADDRESS_DETAILS ( UID , LOCALITY , CITY , PIN , STATE , COUNTRY , AD_ID) VALUES ( ? , ? , ? , ? , ? , ? , ? )";
		       smt = con.prepareStatement(sql);
		       smt.setString(1 , uid);
		       smt.setString(2 , ud.ad.getLocality());
		       smt.setString(3 , ud.ad.getCity());
		       smt.setString(4 , ud.ad.getPincode());
		       smt.setString(5 , ud.ad.getState());
		       smt.setString(6 , ud.ad.getCountry());
		       smt.setString(7 , ud.ad.getAdd_id());
		       smt.execute();
		       smt.close();
		    }catch(SQLException se)
		           {
		    	       se.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	   }
		      catch(Exception e)
		           {
		    	       e.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	   }
		      finally
		           {
		    	      try
		    	        {
		    	           if(smt!=null)
		    	              smt.close();
		    	        }
		    	        catch(SQLException se2)
		    	        {
		    	        	se2.printStackTrace();
		    	        } 
		           }
	 }
	 
	 public static void insertOD(OrderDetails od,Connection con)
	 {
		  PreparedStatement smt = null;
		  String sql = "";
		  try
		    {
		       sql = "INSERT INTO ORDER_DETAILS ( ORDER_ID , PRO_ID , USR_ID , ORDER_DATE , DELIVERY_DATE , STATUS , ADD_ID ) VALUES ( ? , ? , ? , ? , ? , ? , ? )";
		       smt = con.prepareStatement(sql);
		       smt.setString(1 , od.getOd_id());
		       smt.setString(2 , od.getProduct_id());
		       smt.setString(3 , od.getUsr_id());
		       smt.setString(4 , od.getOd_date());
		       smt.setString(5 , od.getDelv_date());
		       smt.setString(6 , od.getStatus());
		       smt.setString(7 , od.getAd_id());
		       smt.execute();
		       smt.close();
		    }catch(SQLException se)
		           {
		    	       se.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	   }
		      catch(Exception e)
		           {
		    	       e.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	   }
		      finally
		           {
		    	      try
		    	        {
		    	           if(smt!=null)
		    	              smt.close();
		    	        }
		    	        catch(SQLException se2)
		    	        {
		    	        	se2.printStackTrace();
		    	        } 
		           }
	 }
	 
	 public static void insertAgent(String agname,String agid,String loc,String cty,String pin,String st,String cntry,String pass,Connection con)
	 {
		  PreparedStatement smt = null;
		  String sql = "";
		  try
		    {
		       sql = "INSERT INTO AGENT_DETAILS ( AG_ID , Fullname , AG_Locality , AG_City , AG_Pin , AG_State , AG_Country , Password ) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? )";
		       smt = con.prepareStatement(sql);
		       smt.setString(1 , agid);
		       smt.setString(2 , agname);
		       smt.setString(3 , loc);
		       smt.setString(4 , cty);
		       smt.setString(5 , pin);
		       smt.setString(6 , st);
		       smt.setString(7 , cntry);
		       smt.setString(8 , pass);
		       smt.execute();
		       smt.close();
		    }catch(SQLException se)
		           {
		    	       se.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	   }
		      catch(Exception e)
		           {
		    	       e.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	   }
		      finally
		           {
		    	      try
		    	        {
		    	           if(smt!=null)
		    	              smt.close();
		    	        }
		    	        catch(SQLException se2)
		    	        {
		    	        	se2.printStackTrace();
		    	        } 
		           }
	    }
	 
 }

