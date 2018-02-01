package com.microservice.SharedRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

@Path("/MicroGetSharedReg")
public class MicroGetSharedReg 
{
	final static Logger logger = Logger.getLogger(MicroGetSharedReg.class);
	  
	@Path("/getSharedReg")
	@POST
	//@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
	public Response getUserReg(String username1)
	{
		String json=null;
		
				//ArrayList(String,String<HashMap<String,String>>());
		//HashMap<HashMap<String,String>,HashMap<String,String>> out=new HashMap<HashMap<String,String>,HashMap<String,String>>();
		//HashMap<String,HashMap<String,String>> out=new HashMap<String,HashMap<String,String>>();
		ArrayList<HashMap<String,String>> out=new ArrayList<HashMap<String,String>>();
			
		logger.debug("Fetching Shared Registry in Micro Service ");
		
		try
		{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/giftregistry";
			String username = "root";
			String password1 = "root";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password1);
			//response=true;			
			//System.out.println("connection established");
			
			//System.out.print("In micro service username is"+username1);
			Statement st=conn.createStatement();
			String sql1="select * from sharedregistry s,registryitems r,registry rd, product p,color co,brand b,category c where friends='"+username1+"' and s.registryid=r.registryid and p.product_id=r.product_id and rd.registryid=r.registryid and c.category_id=p.product_category and b.brand_id=p.product_brand and co.color_id=p.product_color";
		
			//System.out.println(sql1);
			//	String sql="select p.product_id,p.product_name,p.product_desc,p.product_price,b.brand_name,c.color_name,cat.category_name from giftregistry.registry r, giftregistry.registryitems ri, giftregistry.product p,giftregistry.category cat,giftregistry.brand b, giftregistry.color c where r.name='"+username1+"' and  p.product_id=ri.product_id and ri.registryid=r.registryid and p.product_category=cat.category_id and p.product_brand=b.brand_id and p.product_color=c.color_id;";
			ResultSet rs=st.executeQuery(sql1);
			while(rs.next())
			{
				HashMap<String,String>key= new HashMap<String,String>();
				HashMap<String,String>value= new HashMap<String,String>();
				
				String friend=rs.getString("name");
				////System.out.println(product_id);
				String productname=rs.getString("product_name");
				String prod_desc=rs.getString("product_desc");
				String pp=rs.getString("product_price");
				String brandname=rs.getString("brand_name");
				//System.out.println(brandname);
				String categoryname=rs.getString("category_name");
				String colorname=rs.getString("color_name");
				String reg_name=rs.getString("reg_name");
				String registryid=rs.getString("reg_name");
				String productid=rs.getString("product_id");
				String assignTo=rs.getString("assignTo");
				//System.out.println(assignTo);
				key.put("friend",friend);
				key.put("productid",productid);
				key.put("reg_name", reg_name);
				
				value.put("product_name", productname);
				value.put("product_desc", prod_desc);
				value.put("product_price", pp);
				value.put("brandname", brandname);
				value.put("category_name", categoryname);
				value.put("colorname", colorname);
				value.put("registryid", registryid);
				
				value.put("friend",friend);
				value.put("productid",productid);
				value.put("reg_name", reg_name);
				value.put("assignTo", assignTo);
				
				if(assignTo==null) {
					value.put("assignTo", "1");
					
				}
				
				out.add(value);//(friend+reg_name+productid, value);
			}
			
			//System.out.println("me"+out);
			json= new Gson().toJson(out);
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
		logger.debug("Fetched details of shared registry details");
		
		return Response.ok(json).build();
	}

}
