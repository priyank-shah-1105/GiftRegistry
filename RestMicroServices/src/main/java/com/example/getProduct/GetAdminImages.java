package com.example.getProduct;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;

@Path("/getImagesmicro")
public class GetAdminImages 
{
	@Path("/getImage")
	@POST
	//@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
	public Response getAdminImages()
	{
		boolean response = false;
		File[] list=null;
		GenericEntity<File[]> ge=null;
		List<String> ans=new ArrayList<String>();
		String json=null;
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
			Statement st=conn.createStatement();
			String name="admin";
			ResultSet rs=st.executeQuery("select url from giftregistry.image where name='"+name+"'");
			String adminurl=null;
			if(rs.next())
			{
				
				adminurl=rs.getString("url");
				File f= new File(adminurl);
				list=f.listFiles();
				for(int i=0;i<list.length;i++)
				{
					ans.add(list[i].toString());
				}
				//printing
				
				json= new Gson().toJson(ans);
				System.out.print(json);
			}
			else
			{
				//System.out.println("path doesn't exist");
				response=false;
			}
		}
		catch(Exception e)
		{
			//System.out.println(e+" "+"Connection error");						
			//response=true;
		}

		//return list;
		return Response.ok().entity(json).build();
	}

}
