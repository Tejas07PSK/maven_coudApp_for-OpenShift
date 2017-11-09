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

public final class OrderDetails implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String ad_id;
	private String od_id;
    private String product_id;
    private String usr_id;
    private String od_date;
    private String delv_date;
    private String status;
    
    public OrderDetails()
    {
    	ad_id="";
    	od_id="";
    	product_id="";
    	usr_id="";
    	od_date="";
    	delv_date="";
    	status="";
    }

	public String getAd_id()
	{
		 return (ad_id);
	}
	
	public void setAd_id(String ad_id)
	{
		 this.ad_id = ad_id;
	}
	
    public String getOd_id() 
	{
		return (od_id);
	}

	public void setOd_id(String od_id) 
	{
		this.od_id = od_id;
	}

	public String getProduct_id() 
	{
		return (product_id);
	}

	public void setProduct_id(String product_id) 
	{
		this.product_id = product_id;
	}

	public String getUsr_id() 
	{
		return (usr_id);
	}

	public void setUsr_id(String usr_id) 
	{
		this.usr_id = usr_id;
	}

	public String getOd_date() 
	{
		return (od_date);
	}

	public void setOd_date(String od_date) 
	{
		this.od_date = od_date;
	}

	public String getDelv_date() 
	{
		return (delv_date);
	}

	public void setDelv_date(String delv_date) 
	{
		this.delv_date = delv_date;
	}

	public String getStatus() 
	{
		return (status);
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}
}
