package com.miroservice.MicroGetUserReg;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

@Path("/MicroInsertRegName")
public class MicroInsertRegName 
{
	final static Logger logger = Logger.getLogger(MicroInsertRegName.class);
	
	@Path("/insertRegName")
	@POST
	//@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
	public Response insertRegName(String combine) throws JsonParseException, JsonMappingException, IOException
	{
		String json=null;
		
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
		String response=null;
		int reg_id=0;
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
			String sql="INSERT INTO `giftregistry`.`registry` (`name`, `reg_name`) VALUES ('"+username1+"', '"+reg_name+"')";
			
			PreparedStatement pst1=conn.prepareStatement(sql);
			pst1.execute();
			String sql1="SELECT registryid FROM giftregistry.registry where name='"+username1+"' and reg_name='"+reg_name+"'";
			ResultSet rs=st.executeQuery(sql1);
			if(rs.next())
			{
				reg_id=rs.getInt("registryid");
			}
			
			//now we have registry id and now i will insert this id with all friends over here in for loop.
			for(int i=0;i<friends.length;i++)
			{
				String sql2="INSERT INTO `giftregistry`.`sharedregistry` (`name`, `friends`, `registryid`) VALUES ('"+username1+"', '"+friends[i]+"', '"+reg_id+"')";
				PreparedStatement pst2=conn.prepareStatement(sql2);
				pst2.execute();
			}
			//inserted into the shared registry the vlues;
			//get all the registries of particular user.
			ArrayList<HashMap<String,String>> out= new ArrayList<HashMap<String,String>>();			
			//String sql3="SELECT registryid,reg_name FROM giftregistry.registry where name='"+username1+"'";
			//ResultSet rs3=st.executeQuery(sql3);
			//while(rs3.next())
			
				HashMap<String,String>hm=new HashMap<String,String>();
				hm.put("reg_id", String.valueOf(reg_id));
				hm.put("reg_name","reg_name");
				out.add(hm);
			
			json= new Gson().toJson(out);
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
		logger.debug("Creating New Registry");
		
		return Response.ok(json).build();
	}

}
