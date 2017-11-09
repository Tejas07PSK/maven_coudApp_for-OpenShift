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
 //import javax.servlet.annotation.WebServlet;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import com.sgp.coudapp.logic.*;
 import java.util.Random;
 //import java.util.Date;
 //import java.text.DateFormat;
 //import java.text.SimpleDateFormat;
 import com.sgp.coudapp.datahandling.*;
 import org.json.simple.JSONObject;
 import org.json.simple.parser.JSONParser;
 import org.json.simple.parser.ParseException;
 import org.json.simple.JSONArray;
 import com.sgp.coudapp.pojos.*;
 import com.sgp.coudapp.servlets.MainServlet;

final public class AndroServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	private static String ca_city = "";
	private static String ca_state = "";
	private static String ca_country = "";
    private static Connection con = null;
    private static Random r; //= new Random ();
    
    private static String adr [] = new String [6];
	
    @Override
    public void init()
    {
    	if(con == null)
      	    con = MainServlet.getCon();
    	r = new Random ();
    }
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("Name -"+request.getParameter("Name"));
		System.out.println("Message -"+request.getParameter("Msg"));
		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		response.setBufferSize(8192);
		try
		{
		    PrintWriter out = response.getWriter();
		    out.println("Good Luck!!");
		    out.println(request.getParameter("Name")+"\n"+request.getParameter("Msg"));
		    out.flush();
		    out.close();
		}catch(Exception e)
		  {
			  e.printStackTrace();
		  }
    }
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		//con=MainServlet.getCon();
		String a_id = request.getParameter("agentid");
		String pass = request.getParameter("password");
		
		response.setContentType("text/javascript");
		response.setCharacterEncoding("UTF-8");
		response.setBufferSize(8192);
		PrintWriter out = response.getWriter(); 
		
		if(validateUser(a_id,pass) == true)
		{
			 if( OrderAssigner.verify_exists(ca_city, ca_state, ca_country) == false );
			       OrderAssigner.setArrLstOfAssObjs(ca_city, ca_state, ca_country, con);
			 int cse = OrderAssigner.getCs();
			 switch (cse)
			 {
			         case 1 : out.println("[ null ]");
			                  out.println("[ null ]");
			        	      out.println("No Orders Available for You to deliver!!Sorry.");
			                  out.flush();
			                  out.close();
			        	      break;
			         case 2 : out.println("[ null ]");
			                  out.println("[ null ]");
			        	      out.println("Internal System/SQL ERROR!!!");
			                  out.flush();
			                  out.close();
			        	      break;
			         default : int count = 0;
			                   JSONArray od_ar = new JSONArray ();
			                   JSONArray ad_ar = new JSONArray ();
			                   
			                   JSONObject hub = new JSONObject ();
			                   hub.put("ADD_ID", adr[0]);
		                	   hub.put("LOCALITY", adr[1]);
		                	   hub.put("CITY", adr[2]);
		                	   hub.put("PIN", adr[3]);
		                	   hub.put("STATE", adr[4]);
		                	   hub.put("COUNTRY", adr[5]);
			                   ad_ar.add(hub);
			                   
		                	   while (count <= 50)
			                   {
			                	   //count++;
			                	   int x = r.nextInt(OrderAssigner.ar.size());
			                	  
			                	   while(OrderAssigner.ar.get(x).getIs_assigned()==true && count <= 50)
			                	   {
			                		   System.out.println(x);
			                		   x = r.nextInt(OrderAssigner.ar.size());
			                		   count++;
			                	   }
			                	   if (count > 50)
			                		    break;
			                	   count ++;
			                	   OrderAssigner.ar.get(x).setIs_assigned(true);
			                	   Assign a = OrderAssigner.ar.get(x);
			                	  
			                	   JSONObject od = new JSONObject ();
			                	   od.put("ORDER_ID", a.getOd_id());
			                	   od.put("PRODUCT_ID", a.getPro_id());
			                	   od.put("PRODUCT_NAME", a.getPro_name());
			                	   od.put("PRICE", a.getOd_price());
			                	   od.put("ORDER_DATE", a.getOd_date());
			                	   od.put("NAME", a.getFname()+" "+a.getLname());
			                	   od.put("MOBILE_NO", a.getMob_no());
			                	   od.put("EMAIL", a.getEmail());
			                	   od.put("USER_ID", a.getU_id());
			                	   od.put("ADDRESS_ID",a.getAd_id());
			                	  
			                	   JSONObject ad = new JSONObject ();
			                	   ad.put("ADD_ID", a.getAd_id());
			                	   ad.put("LOCALITY", a.getLocality());
			                	   ad.put("CITY", a.getCity());
			                	   ad.put("PIN", a.getPin());
			                	   ad.put("STATE", a.getState());
			                	   ad.put("COUNTRY", a.getCountry());
			                	  
			                	   od_ar.add(od);
			                	   ad_ar.add(ad);
			                	   setAssigned(a_id,a.getOd_id());
			                  }
			                  StringWriter wr = new StringWriter();
			                  od_ar.writeJSONString(wr);
			                  out.println(wr.toString());
			                  wr.flush();
			                  wr.close();
			                  wr = new StringWriter();
			                  ad_ar.writeJSONString(wr);
			                  out.println(wr.toString());
			                  wr.flush();
			                  wr.close();
			                  out.println("Welcome!! You have new orders!!");
			                  out.flush();
			                  out.close();
			                  break;
		       }
		 }
		 else
		 {
			 out.println("[ null ]");
			 out.println("[ null ]");
			 out.println("AgentId/Password Notfound" + "Or Invalid Input" + "Or Internal Sql Error!!!" + " Please enter correct credentials!!");
			 out.flush();
		     out.close();
		 }
     }
	
	 @Override
	 protected void doPut(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	 {
		 //con=MainServlet.getCon();
		 String agname="";String loc="";String cty="";String pin="";String st="";String cntry="";String pass="";
		 BufferedReader rd = new BufferedReader(new InputStreamReader(request.getInputStream()));
		 StringBuffer req = new StringBuffer();
		 String line = "";
		 while ((line = rd.readLine()) != null) 
		 {
			 req.append(line);
		 }
		 rd.close();
		
         JSONParser parser = new JSONParser ();
	     try 
	       {
	           Object obj = parser.parse(req.toString());
	           JSONObject jsobj = (JSONObject)obj;
	           agname = (String)jsobj.get("agent_name");
	           loc = (String)jsobj.get("locality");
	           cty = (String)jsobj.get("city");
	           pin = (String)jsobj.get("pincode");
	           st = (String)jsobj.get("state");
	           cntry = (String)jsobj.get("country");
	           pass = (String)jsobj.get("password");
	       }catch(ParseException pe){
	                                   System.out.println(pe);
	                                }
		
		   response.setContentType("text");
		   response.setCharacterEncoding("UTF-8");
		   response.setBufferSize(8192);
		   
		   PrintWriter out = response.getWriter();
		
		   if(AgentChecker.isAgentExists(agname,loc,cty,pin,st,cntry,pass,con)==true)
		   {
			   //cannot register;
			   int cs = AgentChecker.getCas();
			
			   switch (cs)
			   {
			        case 1 : out.println("You have already been registered as an agent!!!");
			                 out.flush();
	    		             out.close();
	    		             break;
			        case 2 : out.println("Sorry the password you entered is being used by another agent!!!");
			                 out.flush();
			                 out.close();
			                 break;
			        default : out.println("Internal SQL Error/Server Error!!!!");
			                  out.flush();
			                  out.close();
			                  break;
			   }
		    }
		    else
		    {
			    int cs = AgentChecker.getCas();
			 
			    switch (cs)
			    {
			        case 303 : out.println("Internal SQL Error/Server Error!!!!");
	                           out.flush();
	                           out.close();
	                           break;
	                default :  String agid = generateAG_ID();
	                	       Insert.insertAgent(agname, agid, loc, cty, pin, st, cntry, pass, con);
	                           out.println("Welcome on board Agent!!! \n You have been successfully registered.");
	                           out.flush();
	                           out.close();
	            }
		    }
	  }
	
	private boolean validateUser(String a_id,String pass)
	{
		 Statement smt = null;
		 String sql = "";
		 ResultSet rs = null;
		 try
		   {
			  smt = con.createStatement();
		      sql = "SELECT * FROM AGENT_DETAILS WHERE AG_ID=\'"+a_id+"\' AND Password=\'"+pass+"\'";
		      rs = smt.executeQuery(sql);
		      if(rs.isBeforeFirst()==true)
		      {
		    	  while(rs.next())
		    	  {
		    	       ca_city = rs.getString("AG_City");
		    	       ca_state = rs.getString("AG_State");
		    	       ca_country = rs.getString("AG_Country");
		    	       
		    	       adr[0]="HUB";
		    	       adr[1]=rs.getString("AG_Locality");
		    	       adr[2]=rs.getString("AG_City");
		    	       adr[3]=rs.getString("AG_Pin");
		    	       adr[4]=rs.getString("AG_State");
		    	       adr[5]=rs.getString("AG_Country");
		    	  }
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
		    }catch(SQLException se)
		           {
		    	       se.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	       return (false);
		    	   }
		     catch(Exception e)
		           {
		    	       e.printStackTrace();
		    	       System.out.println("ERROR!!!");
		    	       return (false);
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
		    	        	return (false);
		    	        }
		           }
        }
	
	private static void setAssigned(String agnt_id, String od_id)
	{
	      Statement smt = null;
		  String sql = "";
		  try
			{
				 smt = con.createStatement();
			     sql = "UPDATE ORDER_DETAILS SET ASG_AG=\'"+agnt_id+"\' WHERE ORDER_ID=\'"+od_id+"\'";
			     smt.executeUpdate(sql);
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
			  return ;
	  }
	
	   private static String generateAG_ID() 
	   {
		   Random r = new Random ();
		   int x = r.nextInt(50000);
		   String agid = "AG"+x;
			    return (agid);
	   }
	    
	private void stopDBConnection()
    {
	      try
		    {
			    con.close();
		    }catch(SQLException se)
	               {
			           se.printStackTrace();
		           }
		      con = null;
    }
	       
    @Override
	public void destroy() 
	{
	   	  stopDBConnection();
	   	  ca_city = null;
	      ca_state = null;
	   	  ca_country = null;
	   	  r = null;
	}
}
