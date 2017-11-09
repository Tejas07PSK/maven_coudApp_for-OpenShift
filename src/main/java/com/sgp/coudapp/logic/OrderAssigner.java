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

import java.io.Serializable;
import java.sql.*;
import com.sgp.coudapp.pojos.PrevAssign;
import com.sgp.coudapp.pojos.Assign;
import java.util.ArrayList;

public class OrderAssigner implements Serializable
{
	
   private static final long serialVersionUID = 1L;
	private static int cs = 0;
	public static ArrayList <Assign> ar;
	private static Assign asg;

	public static int getCs() 
	{
		return (cs);
	}
	
	public static boolean verify_exists(String cty, String st, String crn)
	{
		 asg = new Assign ();
		 if (PrevAssign.pca_city==""&&PrevAssign.pca_country==""&&PrevAssign.pca_state=="")
		 {
			 System.out.println("First false");
			 ar = new ArrayList <Assign> ();
			 return (false);
		 }
		 else
		 {
			 if((PrevAssign.pca_city).equalsIgnoreCase(cty)==true&&(PrevAssign.pca_country).equalsIgnoreCase(crn)==true&&(PrevAssign.pca_state).equalsIgnoreCase(st)==true)
			 {
				 return (true);
		     }
			 else
			 {
				 System.out.println("Second False");
				 ar = new ArrayList <Assign> ();
				 return (false);
			 }
		 }
	 }

	public static void setCs(int cs) 
	{
		OrderAssigner.cs = cs;
	}
	
	public static void setArrLstOfAssObjs(String cty, String st, String crn,Connection con)
	{
		  Statement smt = null;
		  String sql = "";
		  ResultSet rs = null;
		  try
		    {
			   smt = con.createStatement();
		       sql = "SELECT * FROM ADDRESS_DETAILS WHERE CITY=\'"+cty+"\' AND STATE=\'"+st+"\' AND COUNTRY=\'"+crn+"\'";
		       rs = smt.executeQuery(sql);
		       if(rs.isBeforeFirst()==false)
		       {
		    	   System.out.println("No add");
		    	   rs.close();
		    	   smt.close();
		    	   setCs(1);
		    	   return ;
		       }
		       else
		       {
			       System.out.println("Add");
		    	   while (rs.next())
			       {
			    	   Statement smt1 = null;
			 		   String sql1 = "";
			 		   ResultSet rs1 = null;
			 		  try
					    {
						   smt1 = con.createStatement();
					       sql1 = "SELECT * FROM ORDER_DETAILS WHERE ADD_ID=\'"+rs.getString("AD_ID")+"\' AND ASG_AG=\'N/A\'";
					       rs1 = smt1.executeQuery(sql1);
					       if(rs1.isBeforeFirst()==false)
					       {
					    	   System.out.println("No odd");
					    	   rs1.close();
					    	   smt1.close();
					    	   setCs(1);
					    	   return ;
					       }
					       else
					       {
					    	   System.out.println("Order Exists");
					    	   while (rs1.next())
					    	   {
					    		   System.out.println("User");
					    		   Statement smt2 = null;
						 		   String sql2 = "";
						 		   ResultSet rs2 = null;
						 		   try
						 		   {
						 			  smt2 = con.createStatement();
								      sql2 = "SELECT * FROM USER_DETAILS WHERE USER_ID=\'"+rs1.getString("USR_ID")+"\'";
								      rs2 = smt2.executeQuery(sql2);
								      while(rs2.next())
								      {
								    	  System.out.println("Gotcha");
								    	  asg.setFname(rs2.getString("FIRST_NAME"));
								    	  asg.setLname(rs2.getString("LAST_NAME"));
								    	  asg.setMob_no(rs2.getString("MOB_NO"));
								    	  asg.setEmail(rs2.getString("EMAIL_ID"));
								    	  asg.setU_id(rs2.getString("USER_ID"));
								      }
								      System.out.println("OUT");
								      rs2.close();
							    	  smt2.close();
						 		   }catch(SQLException se)
						            {
						    	        se.printStackTrace();
						    	        System.out.println("ERROR!!!");
						    	        setCs(2);
						    	        PrevAssign.int_set();
						    	        ar = new ArrayList <Assign> ();
						    	        return ;
						    	    }
						            catch(Exception e)
						            {
						    	        e.printStackTrace();
						    	        System.out.println("ERROR!!!");
						    	        setCs(2);
						    	        PrevAssign.int_set();
						    	        ar = new ArrayList <Assign> ();
						    	        return ;
						    	    }
						            finally
						            {
						    	       try
						    	         {
						    	            if(smt2!=null)
						    	               smt2.close();
						    	         }
						    	         catch(SQLException se2)
						    	         {
						    	        	se2.printStackTrace();
						    	        	setCs(2);
						    	        	PrevAssign.int_set();
							    	        ar = new ArrayList <Assign> ();
						    	        	return ;
						    	         } 
						            }
						 		    System.out.println("In");
						 		    asg.setOd_id(rs1.getString("ORDER_ID"));
						 		    asg.setPro_id(rs1.getString("PRO_ID"));
						 		    System.out.println("Maybe Culprit");
						 		    asg.setPro_name(findPro_name(rs1.getString("PRO_ID"),con));
						 		    asg.setOd_price(findPro_price(rs1.getString("PRO_ID"),con));
						 		    System.out.println("No Culprit");
						 		    asg.setOd_date(rs1.getString("ORDER_DATE"));
						 		    asg.setLocality(rs.getString("LOCALITY"));
						 		    asg.setCity(rs.getString("CITY"));
						 		    asg.setCountry(rs.getString("COUNTRY"));
						 		    asg.setState(rs.getString("STATE"));
						 		    asg.setPin(rs.getString("PIN"));
						 		    asg.setAd_id(rs.getString("AD_ID"));
						 		    ar.add (asg);
						 		    asg = new Assign ();
						 		    System.out.println("Over");
					    	   }
					    	   rs1.close();
					    	   smt1.close();
					    	   //return ;
					    	}
					       }catch(SQLException se)
				            {
				    	        se.printStackTrace();
				    	        System.out.println("ERROR!!!");
				    	        setCs(2);
				    	        PrevAssign.int_set();
				    	        ar = new ArrayList <Assign> ();
				    	        return ;
				    	    }
				            catch(Exception e)
				            {
				    	        e.printStackTrace();
				    	        System.out.println("ERROR!!!");
				    	        setCs(2);
				    	        PrevAssign.int_set();
				    	        ar = new ArrayList <Assign> ();
				    	        return ;
				    	    }
				            finally
				            {
				    	       try
				    	         {
				    	            if(smt1!=null)
				    	               smt1.close();
				    	         }
				    	         catch(SQLException se2)
				    	         {
				    	        	se2.printStackTrace();
				    	        	setCs(2);
				    	        	PrevAssign.int_set();
					    	        ar = new ArrayList <Assign> ();
				    	        	return ;
				    	         } 
				            }
			           }
			          
			          PrevAssign.pca_city=cty;
			          PrevAssign.pca_country=crn;
			          PrevAssign.pca_state=st;
			          System.out.println("Done");
			          rs.close();
		    	      smt.close();
		    	      setCs(0);
		    	      //return ;
		          }
		       }catch(SQLException se)
		             {
		    	        se.printStackTrace();
		    	        System.out.println("ERROR!!!");
		    	        setCs(2);
	    	        	PrevAssign.int_set();
		    	        ar = new ArrayList <Assign> ();
	    	        	return ;
		    	     }
		             catch(Exception e)
		             {
		    	        e.printStackTrace();
		    	        System.out.println("ERROR!!!");
		    	        setCs(2);
	    	        	PrevAssign.int_set();
		    	        ar = new ArrayList <Assign> ();
	    	        	return ;
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
		    	        	 setCs(2);
			    	         PrevAssign.int_set();
				    	     ar = new ArrayList <Assign> ();
				    	     return ;
		    	        } 
		             }
		  return ;
	}
	
	private static String findPro_name(String pid,Connection con)
	{
		  Statement smt = null;
		  String sql = "";
		  ResultSet rs = null;
		  try
		    {
			   smt = con.createStatement();
		       sql = "SELECT PRODUCT_NAME FROM PRODUCT_DETAILS WHERE PRODUCT_ID=\'"+pid+"\'";
		       rs = smt.executeQuery(sql);
		       if(rs.isBeforeFirst()==true)
		       {
		    	   String pname="";
		    	   while(rs.next())
		    	        pname = rs.getString("PRODUCT_NAME");
		    	   rs.close();
		    	   smt.close();
		    	   return (pname);
		       }
		       else
		       {
		    	   rs.close();
		    	   smt.close();
		    	   return (null);
		       }
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
		       return (null);
    }
	
	private static String findPro_price(String pid,Connection con)
	{
		  Statement smt = null;
		  String sql = "";
		  ResultSet rs = null;
		  try
		    {
			   smt = con.createStatement();
		       sql = "SELECT PRICE FROM PRODUCT_DETAILS WHERE PRODUCT_ID=\'"+pid+"\'";
		       rs = smt.executeQuery(sql);
		       if(rs.isBeforeFirst()==true)
		       {
		    	   String price="";
		    	   while(rs.next())
		    	        price = rs.getString("PRICE");
		    	   rs.close();
		    	   smt.close();
		    	   return (price);
		       }
		       else
		       {
		    	   rs.close();
		    	   smt.close();
		    	   return (null);
		       }
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
		       return (null);
     }
       
}
