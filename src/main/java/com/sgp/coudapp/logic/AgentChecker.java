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

public final class AgentChecker implements Serializable
{
	private static final long serialVersionUID = 1L; 
	
	 public static int cas = 0;
    
     public static int getCas() 
     {
		 return (cas);
	 }

     public static void setCas(int cas) 
	 {
		  AgentChecker.cas = cas;
	 }

     public static boolean isAgentExists(String agname,String loc,String cty,String pin,String st,String cntry,String pass,Connection con)
     {
    	   Statement smt = null;
		   String sql = "";
		   ResultSet rs = null;
		   System.out.println(agname);
		   System.out.println(pass);
		   try
		     {
			    //System.out.println(con);
			    smt = con.createStatement();
		        sql = "SELECT * FROM AGENT_DETAILS";
		        rs = smt.executeQuery(sql);
		        if(rs.isBeforeFirst()==false)
		        {
		    	    rs.close();
		    	    smt.close();
		    	    setCas(0);
		    	    //System.out.println("IN");
		    	    return (false);
		        }
		        else
		        { 
			         sql = "SELECT FullName,Password FROM AGENT_DETAILS WHERE AG_Locality=\'"+loc+"\' AND AG_City=\'"+cty+"\' AND AG_Pin=\'"+pin+"\' AND AG_State=\'"+st+"\' AND AG_Country=\'"+cntry+"\'";
			         rs=smt.executeQuery(sql);
			         if(rs.isBeforeFirst()==true)
			         {
			        	 Boolean flag = false;
			        	 while(rs.next())
			        	 {
			        		 if(agname.equalsIgnoreCase(rs.getString("FullName"))==true)
			        		 {
			        			 if(pass.equalsIgnoreCase(rs.getString("Password"))==true)
			        			 {
			        				 setCas(1);
			        				 System.out.println("IN2");
			        				 flag = true;
			        				 break;
			        			 }
			        			 else
			        			 {
			        			     setCas(0);
			        			     flag = false;
			        	             //continue;
			        			 }
			        		 }
			        		 else
			        		 {
			        			 if(pass.equalsIgnoreCase(rs.getString("Password"))==true)
			        			 {
			        				 setCas(2);
			        				 flag = true;
			        				 break;
			        			 }
			        			 else
			        			 {
			        				 setCas(0);
			        				 flag = false;
			        				 //continue;
			        			 }
			        		 }
			        	 }
			        	 if (getCas()==1)
			        	 {
			        		 rs.close();
					    	 smt.close();
			        		 return (flag);
			        	 }
			        	 if (getCas()==2)
			        	 {
			        		 System.out.println("SET");
			        		 rs.close();
					    	 smt.close();
			        		 return (flag);
			        	 }
			        	 if (getCas()==0)
			        	 {
			        		 if(isPassExists(pass,con)==true)
			        		 {
			        			 setCas(2);
			        			 rs.close();
						    	 smt.close();
			        		     return (true);
			        		 }
			        		 else
			        		 {
			        			 setCas(0);
			        			 rs.close();
						    	 smt.close();
						    	 return (false);
			        		 }
			        	 }
			        	 else
			        	 {
			        	    rs.close();
				    	    smt.close();
				    	    setCas(303);
				    	    System.out.println("Error!!");
			        	    return (true);
			        	 }
			         }
			         else
			         { 
			        	
			        	 if(isPassExists(pass,con)==true)
		        		 {
		        			 setCas(2);
		        			 rs.close();
					    	 smt.close();
		        		     return (true);
		        		 }
		        		 else
		        		 {
		        			 setCas(0);
		        			 rs.close();
					    	 smt.close();
					    	 return (false);
		        		 }
			        }
		        }
		      }catch(SQLException se)
		             {
		    	        se.printStackTrace();
		    	        System.out.println("ERROR!!!");
		    	        setCas(303);
		    	     }
		       catch(Exception e)
		             {
		    	        e.printStackTrace();
		    	        System.out.println("ERROR!!!");
		    	        setCas(303);
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
		    	        	      setCas(303);
		    	        	      System.out.println("WTF");
		    	        	      //return (true);
		    	               } 
		             }
		    return (false);
      }

     public static boolean isPassExists(String pass,Connection con)
     {
		 
    	  Statement smt = null;
		  String sql = "";
		  ResultSet rs = null;
		  try
		    {
			   smt = con.createStatement();
		       sql = "SELECT * FROM AGENT_DETAILS";
		       rs = smt.executeQuery(sql);
		       if(rs.isBeforeFirst() == false)
		       {
		    	   rs.close();
		    	   smt.close();
		    	   return (false);
		       }
		       else
		       {
			        sql = "SELECT * FROM AGENT_DETAILS WHERE Password=\'"+pass+"\'";
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
		    	        	 System.out.println("Pass Error!!!!");;
		    	         } 
		             }
		      return (true);
          }
 
 }


    
