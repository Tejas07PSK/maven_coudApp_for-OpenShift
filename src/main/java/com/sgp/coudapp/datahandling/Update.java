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
import java.sql.*;

public final class Update implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	public static void updtOdStat(String od_id,String dd,String stu,Connection con)
	{
		Statement st = null;
		
		try 
		{
			  st = con.createStatement();
		      String sql = "UPDATE ORDER_DETAILS "+" SET DELIVERY_DATE = \""+dd+"\" , STATUS = \""+stu+"\" WHERE ORDER_ID = \""+od_id+"\"";
		      st.executeUpdate(sql);
		
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
 	                       if(st!=null)
 	                            st.close();
 	                 }
 	                 catch(SQLException se2)
 	                      {
 	        	              se2.printStackTrace();
 	                      } 
               }
	  }
}
