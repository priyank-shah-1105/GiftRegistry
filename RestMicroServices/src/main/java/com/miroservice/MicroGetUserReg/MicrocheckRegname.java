package com.miroservice.MicroGetUserReg;

import java.io.IOException;
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
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


import com.google.gson.Gson;

@Path("/MicroCheckRegName")
public class MicrocheckRegname 
{
	final static Logger logger = Logger.getLogger(MicrocheckRegname.class);
	
	@Path("/checkRegName")
	@POST
	//@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
	public Response getUserReg(String combine) throws JsonParseException, JsonMappingException, IOException
	{
		String json=null;
		ArrayList<HashMap<String,String>> out= new ArrayList<HashMap<String,String>>();
		//ArrayList<String> arr=new 
		
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<String> arr = mapper.readValue(combine, new TypeReference<ArrayList<String>>(){});
		String username1=arr.get(0);
		////System.out.println(arr.get(2));
		String f=arr.get(2).replaceAll("\\s", "");
		//System.out.println(f.length());
		String mod=f.substring(1, arr.get(2).length()-2);
		
		String[] friends= mod.split(",");
		
		String reg_name=arr.get(1);
		boolean response=false;
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
			//String sql="select p.product_id,p.product_name,p.product_desc,p.product_price,b.brand_name,c.color_name,cat.category_name from giftregistry.registry r, giftregistry.registryitems ri, giftregistry.product p,giftregistry.category cat,giftregistry.brand b, giftregistry.color c where r.name='"+username1+"' and  p.product_id=ri.product_id and ri.registryid=r.registryid and p.product_category=cat.category_id and p.product_brand=b.brand_id and p.product_color=c.color_id;";
			String sql="SELECT reg_name FROM giftregistry.registry where name='"+username1+"' and reg_name='"+reg_name+"'";
			ResultSet rs=st.executeQuery(sql);
			if(rs.next())
			{
				logger.debug("Registry name is exist");
				
				//response="false";
			}
			else
			{
				
				response=true;
			}
			json= new Gson().toJson(String.valueOf(response));
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
		return Response.ok(json).build();
	}

}
