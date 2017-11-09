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

interface chkfuncs
{
	public boolean checkMobno(long mno,Connection con);
	public boolean checkEml(String email,Connection con);
	public boolean checkName(String name,String email,long mno,Connection con);
}

public final class Checker implements chkfuncs,Serializable 
{
	  
	 private static final long serialVersionUID = 1L;
	  public static String EXISTINGUSER_UID = "";
	  public static String EXISTINGAD_ID = "";
	  public static int adc = 0;
	  
	  private static int TF[] = new int[2];
	  
	  public static int getTCN()
	  {
		  return(TF[0]);
	  }
	  
	  public static int getFCN()
	  {
		  return(TF[1]);
	  }
	  
	  public static void setTCN(int c)
	  {
		  TF[0]=c;
	  }
	  
	  public static void setFCN(int c)
	  {
		  TF[1]=c;
	  }
	
	  //@Override
	  public boolean checkMobno(long mno,Connection con)
	  { 
		  Statement smt = null;
		  String sql = "";
		  ResultSet rs = null;
		  try
		    {
			   System.out.println(con);
			   smt = con.createStatement();
		       sql = "SELECT * FROM USER_DETAILS";
		       rs = smt.executeQuery(sql);
		       if(rs.isBeforeFirst() == false)
		       {
		    	   rs.close();
		    	   smt.close();
		    	   setTCN(0);
		    	   System.out.println("IN");
		    	   return (false);
		       }
		       else
		       {
			        sql = "SELECT MOB_NO FROM USER_DETAILS WHERE MOB_NO="+String.valueOf(mno);
			        rs=smt.executeQuery(sql);
			        if(rs.isBeforeFirst() == true)
			        {
			        	rs.close();
				    	smt.close();
				    	setFCN(1);
				    	System.out.println("IN 2");
			        	return (true);
			        }
			        else
			        {
			        	rs.close();
			        	smt.close();
			        	setTCN(0);
			        	System.out.println("IN 2");
			        	return (false);
			        }
		       }
		     }catch(SQLException se)
		           {
		    	       se.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	       setFCN(7);
		    	   }
		      catch(Exception e)
		           {
		    	       e.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	       setFCN(7);
		    	   }
		      finally
		           {
		    	      try
		    	        {
		    	           if(smt != null)
		    	              smt.close();
		    	        }
		    	        catch(SQLException se2)
		    	        {
		    	        	se2.printStackTrace();
		    	        	setFCN(7);
		    	        	System.out.println("WTF");
		    	        	//return (true);
		    	        } 
		           }
		          return (true);
	    }
	  
	  //@Override
	  public boolean checkEml(String email,Connection con)
	  { 
		  Statement smt = null;
		  String sql = "";
		  ResultSet rs = null;
		  try
		    {
			   smt = con.createStatement();
		       sql = "SELECT * FROM USER_DETAILS";
		       rs = smt.executeQuery(sql);
		       if(rs.isBeforeFirst() == false)
		       {
		    	   rs.close();
		    	   smt.close();
		    	   setTCN(0);
		    	   System.out.println("IN eml");
		    	   return (false);
		       }
		       else
		       {
			        sql = "SELECT EMAIL_ID FROM USER_DETAILS WHERE EMAIL_ID=\'"+email+"\'";
			        rs=smt.executeQuery(sql);
			        if(rs.isBeforeFirst() == true)
			        {
			        	rs.close();
				    	smt.close();
				    	setFCN(2);
			        	return (true);
			        }
			        else
			        {
			        	rs.close();
			        	smt.close();
			        	setTCN(0);
			        	return (false);
			        }
		       }
		     }catch(SQLException se)
		           {
		    	       se.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	       setFCN(7);
		    	   }
		      catch(Exception e)
		           {
		    	       e.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	       setFCN(7);
		    	   }
		      finally
		           {
		    	      try
		    	        {
		    	           if(smt != null)
		    	              smt.close();
		    	        }
		    	        catch(SQLException se2)
		    	        {
		    	        	se2.printStackTrace();
		    	        	setFCN(7);
		    	        	//return (true);
		    	        } 
		           }
		       return (true);
	   }
	  
	  //@Override
	  public boolean checkName(String name,String email,long mno,Connection con)
	  { 
		  Statement smt = null;
		  String sql = "";
		  ResultSet rs = null;
		  try
		    {
			   smt = con.createStatement();
		       sql = "SELECT * FROM USER_DETAILS";
		       rs = smt.executeQuery(sql);
		       if(rs.isBeforeFirst() == false)
		       {
		    	   rs.close();
		    	   smt.close();
		    	   setTCN(0);
		    	   return (false);
		       }
		       else
		       {
			        sql = "SELECT USER_ID,FIRST_NAME,LAST_NAME FROM USER_DETAILS WHERE MOB_NO="+mno+" AND EMAIL_ID=\'"+email+"\'";
			        rs=smt.executeQuery(sql);      //String.valueOf(mno)
			        if(rs.isBeforeFirst() == true)
			        {
			        	Boolean ck=false;
			        	while(rs.next())
			        	{
			        		String nm = rs.getString("FIRST_NAME")+" "+rs.getString("LAST_NAME");
			        		if(nm.equalsIgnoreCase(name) == true)
			        		{
			        			setTCN(1);
			        			EXISTINGUSER_UID = rs.getString("USER_ID");
			        			ck=true;
			        			break;
			        		}
			        		else
			        		{
			        			setFCN(3);
			        			ck=false;
			        		    break;
			        		}
			            }
			        	rs.close();
				    	smt.close();
			        	return (ck);
			        }
			        else
			        {
			        	setFCN(3);
			        	rs.close();
			        	smt.close();
			        	return (false);
			        } 	
			  }
		     }catch(SQLException se)
		           {
		    	       se.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	       setFCN(7);
		    	   }
		      catch(Exception e)
		           {
		    	       e.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	       setFCN(7);
		    	   }
		      finally
		           {
		    	      try
		    	        {
		    	           if(smt != null)
		    	              smt.close();
		    	        }
		    	        catch(SQLException se2)
		    	        {
		    	        	se2.printStackTrace();
		    	        	setFCN(7);
		    	        	//return (true);
		    	        } 
		           }
		       return (true);
	  }
	  
	  public static boolean isExistsUID(String uid,Connection con)
	  { 
		  Statement smt = null;
		  String sql = "";
		  ResultSet rs = null;
		  try
		    {
			   smt = con.createStatement();
		       sql = "SELECT * FROM USER_DETAILS";
		       rs = smt.executeQuery(sql);
		       if(rs.isBeforeFirst() == false)
		       {
		    	   rs.close();
		    	   smt.close();
		    	   return (false);
		       }
		       else
		       {
			        sql = "SELECT USER_ID FROM USER_DETAILS WHERE USER_ID=\'"+uid+"\'";
			        rs=smt.executeQuery(sql);
			        if(rs.isBeforeFirst() == true)
			        {
			        	rs.close();
				    	smt.close();
			        	return (true);
			        }
			        else
			        {
			        	rs.close();
			        	smt.close();
			        	return (false);
			        } 	
			   }
		     }catch(SQLException se)
		           {
		    	       se.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	       setFCN(0);
		    	   }
		      catch(Exception e)
		           {
		    	       e.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	       setFCN(0);
		    	   }
		      finally
		           {
		    	      try
		    	        {
		    	           if(smt != null)
		    	              smt.close();
		    	        }
		    	        catch(SQLException se2)
		    	        {
		    	        	se2.printStackTrace();
		    	        	setFCN(0);
		    	        	return (false);
		    	        } 
		           }
		       return (false);
	  }
	  
	  public static boolean isAddressExists(String loc,String cty,String pin,String state,String country,Connection con)
	  { 
		  Statement smt = null;
		  String sql = "";
		  ResultSet rs = null;
		  try
		    {
			   smt = con.createStatement();
		       sql = "SELECT * FROM ADDRESS_DETAILS";
		       rs = smt.executeQuery(sql);
		       if(rs.isBeforeFirst() == false)
		       {
		    	   rs.close();
		    	   smt.close();
		    	   return (false);
		       }
		       else
		       {
			        sql = "SELECT AD_ID FROM ADDRESS_DETAILS WHERE LOCALITY=\'"+loc+"\' AND CITY=\'"+cty+"\' AND PIN=\'"+pin+"\' AND STATE=\'"+state+"\' AND COUNTRY=\'"+country+"\'";
			        rs=smt.executeQuery(sql);
			        if(rs.isBeforeFirst() == true)
			        {
			        	rs.next();
			        	EXISTINGAD_ID=rs.getString("AD_ID");
			        	rs.close();
				    	smt.close();
			        	return (true);
			        }
			        else
			        {
			        	rs.close();
			        	smt.close();
			        	return (false);
			        } 	
			   }
		     }catch(SQLException se)
		           {
		    	       se.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	       adc=1;
		    	   }
		      catch(Exception e)
		           {
		    	       e.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	       adc=1;
		    	   }
		      finally
		           {
		    	      try
		    	        {
		    	           if(smt != null)
		    	              smt.close();
		    	        }
		    	        catch(SQLException se2)
		    	        {
		    	        	se2.printStackTrace();
		    	        	adc=1;
		    	        	return (false);
		    	        } 
		           }
		       return (false);
	  }
	  
	  public static String getProductId(String pname,Connection con)
	  { 
		  Statement smt = null;
		  String sql = "";
		  ResultSet rs = null;
		  try
		    {
			   smt = con.createStatement();
		       sql = "SELECT PRODUCT_ID FROM PRODUCT_DETAILS WHERE PRODUCT_NAME=\'"+pname+"\'";
		       rs = smt.executeQuery(sql);
		       if(rs.isBeforeFirst() == true)
		       {
		    	   String pid = "";
		    	   while(rs.next())
		    	      pid = rs.getString("PRODUCT_ID");
		    	   rs.close();
		    	   smt.close();
		    	   return (pid);
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
		    	           if(smt != null)
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

