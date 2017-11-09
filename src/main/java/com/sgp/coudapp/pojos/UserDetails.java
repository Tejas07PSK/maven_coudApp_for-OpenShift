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

import java.io.Serializable;

public final class UserDetails implements Serializable
{
	 private static final long serialVersionUID = 1L;
	 
	 public class AddressDetails
	 {
		   private String add_id;
		   private String locality;
		   private String city;
		   private String pincode;
		   private String state;
		   private String country;
		   
		   AddressDetails()
		   {
			    add_id="";
			    locality="";
			    city="";
			    pincode="";
			    state="";
			    country="";
		   }

		   public String getAdd_id()
		   {
			    return (add_id);
		   }
		   
		   public void setAdd_id(String add_id)
		   {
			   this.add_id = add_id;
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

		   public String getPincode() 
		   {
			    return (pincode);
		   }

		   public void setPincode(String pincode) 
		   {
			    this.pincode = pincode;
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
			    return country;
		   }

		   public void setCountry(String country) 
		   {
			    this.country = country;
		   }
	 }
	 
	 public AddressDetails ad;
	 private String usr_id;
	 private String firstname;
	 private String lastname;
	 private String email_id;
	 private long mob_no;
	 
	 public UserDetails()
	 {
		 ad = new AddressDetails();
	     usr_id="";
	     firstname="";
	     lastname="";
	     email_id="";
	     mob_no=0;
	 }

	public String getUsr_id() 
	{
		return (usr_id);
	}

	public void setUsr_id(String usr_id) 
	{
		this.usr_id = usr_id;
	}

	public String getFirstname() 
	{
		return (firstname);
	}

	public void setFirstname(String firstname) 
	{
		this.firstname = firstname;
	}

	public String getLastname() 
	{
		return (lastname);
	}

	public void setLastname(String lastname) 
	{
		this.lastname = lastname;
	}

	public String getEmail_id() 
	{
		return (email_id);
	}

	public void setEmail_id(String email_id) 
	{
		this.email_id = email_id;
	}

	public long getMob_no() 
	{
		return (mob_no);
	}

	public void setMob_no(long mob_no) 
	{
		this.mob_no = mob_no;
	}
}
