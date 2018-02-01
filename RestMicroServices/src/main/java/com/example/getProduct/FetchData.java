package com.example.getProduct;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.microservice.AddFriend.AddFriend;

@Path("/fetchdatamicro")
public class FetchData {
	final static Logger logger = Logger.getLogger(FetchData.class);

	
	@Path("/alldata")
	@POST
	public Response allData()
	{
		//System.out.println("Thappo");
		boolean response = false;
		
		ArrayList<String> temp=new ArrayList<String>();
		HashMap<String,ArrayList<String>> ans=new HashMap<String,ArrayList<String>>();
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
			ResultSet rs=st.executeQuery("select brand_name from giftregistry.brand");
			////System.out.println(rs);
			int i=0;
			
			while(rs.next())
			{
				
				//ans.add(rs.getString("brand_name"));
				//temp[i]=rs.getString("brand_name");
				temp.add(rs.getString("brand_name"));
				//System.out.print(json);
			}
			ans.put("brand", temp);
			temp=new ArrayList<String>();
			rs=st.executeQuery("select color_name from giftregistry.color");
			////System.out.println(rs);
			i=0;
	
			while(rs.next())
			{
				
				//ans.add(rs.getString("brand_name"));
				temp.add(rs.getString("color_name"));
				
				//System.out.print(json);
			}
			ans.put("color", temp);
			temp=new ArrayList<String>();
			rs=st.executeQuery("select category_name from giftregistry.category");
			////System.out.println(rs);
			i=0;
		
			while(rs.next())
			{
				
				//ans.add(rs.getString("brand_name"));
				temp.add(rs.getString("category_name"));
				
				//System.out.print(json);
			}
			ans.put("category", temp);
			json= new Gson().toJson(ans);
			
			
		}
		catch(Exception e)
		{
			//System.out.println(e+" "+"Connection error");						
			//response=true;
		}

		//return list;
		logger.debug("Items Add Page is Ready");
		
		return Response.ok().entity(json).build();

	}
}
