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

package com.sgp.coudapp.pojos;

import com.sgp.coudapp.pojos.PrevAssign;
import java.io.Serializable;

public final class Assign extends PrevAssign implements Serializable
{
	private static final long serialVersionUID = 1L;  
	
	  private String ad_id ;
	  private String u_id ;
	  private String locality ;
      private String city ;
      private String pin ;
      private String state ;
      private String country ;
      private String fname ;
      private String lname ;
      private String mob_no ;
      private String email ;
      private String pro_id ;
      private String pro_name ;
      private String od_date ;
      private String od_id ;
      private String od_price ;
      private Boolean is_assigned ;
      private static int count = 0 ;
      
      public Assign()
      {
    	  if (count == 0)
    		  super.int_set();
    	  ad_id = "";
    	  u_id = "";
    	  locality = "";
    	  city = "";
    	  pin = "";
    	  state = "";
    	  country = "";
    	  fname = "";
    	  lname = "";
    	  mob_no = "";
    	  email = "";
    	  pro_id = "";
    	  pro_name = "";
    	  is_assigned = false;
    	  od_date = "";
    	  count ++;
      }

	public String getOd_id()
	{
		 return (od_id);
	}
	
	public void setOd_id(String od_id)
	{
		this.od_id = od_id;
	}
	
    public String getAd_id() 
	{
		return (ad_id);
	}

	public void setAd_id(String ad_id) 
	{
		this.ad_id = ad_id;
	}

	public String getU_id() 
	{
		return (u_id);
	}

	public void setU_id(String u_id) 
	{
		this.u_id = u_id;
	}

	public String getLocality() 
	{
		return (locality);
	}

	public void setLocality(String locality) 
	{
		this.locality = locality;
	}

	public String getCity() 
	{
		return (city);
	}

	public void setCity(String city) 
	{
		this.city = city;
	}

	public String getPin() 
	{
		return (pin);
	}

	public void setPin(String pin) 
	{
		this.pin = pin;
	}

	public String getState() 
	{
		return (state);
	}

	public void setState(String state) 
	{
		this.state = state;
	}

	public String getCountry() 
	{
		return (country);
	}

	public void setCountry(String country) 
	{
		this.country = country;
	}

	public String getFname() 
	{
		return (fname);
	}

	public void setFname(String fname)
	{
		this.fname = fname;
	}

	public String getLname() 
	{
		return (lname);
	}

	public void setLname(String lname) 
	{
		this.lname = lname;
	}

	public String getMob_no() 
	{
		return (mob_no);
	}

	public void setMob_no(String mob_no) 
	{
		this.mob_no = mob_no;
	}

	public String getEmail() 
	{
		return (email);
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getPro_id() 
	{
		return (pro_id);
	}

	public void setPro_id(String pro_id) 
	{
		this.pro_id = pro_id;
	}

	public String getPro_name() 
	{
		return (pro_name);
	}

	public void setPro_name(String pro_name) 
	{
		this.pro_name = pro_name;
	}

	public String getOd_date() 
	{
		return (od_date);
	}

	public void setOd_date(String od_date) 
	{
		this.od_date = od_date;
	}

	public static int getCount() 
	{
		return (count);
	}

	public static void setCount(int count) 
	{
		Assign.count = count;
	}

	public String getOd_price() 
	{
		return od_price;
	}

	public void setOd_price(String od_price) 
	{
		this.od_price = od_price;
	}

	public Boolean getIs_assigned() 
	{
		return (is_assigned);
	}

	public void setIs_assigned(Boolean is_assigned) 
	{
		this.is_assigned = is_assigned;
	}
}
