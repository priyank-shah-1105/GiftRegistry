package com.microservices.Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

@Path("/gettokenmcroservice")
public class GetToken
{
	final static Logger logger = Logger.getLogger(GetToken.class);
	
	@Path("/gettoken")
	@POST
	public Response fetchprofile(MultivaluedMap<String, String> formParam) {
		//System.out.println("webservicce fetch");
		String json=null;
		boolean response = false;
		HashMap<String,String> ans=new HashMap<String,String>();
		
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/giftregistry";
			String username = "root";
			String password1 = "root";
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(url, username, password1);
			// response=true;

			//System.out.println("connection established");
			Statement st = conn.createStatement();
			String name=formParam.getFirst("name");
			//System.out.println(formParam.getFirst("name")+"micro");
			String sql="SELECT * FROM giftregistry.userdetails where  username='"+name+"'";
			//System.out.println(sql);
			ResultSet rs=st.executeQuery(sql);
			String tokenOut = null;
			if(rs.next())
			{
				tokenOut = rs.getString("token");
				//System.out.println("user has valid token");
				//System.out.println(tokenOut);
				response=true;
			}
			else
			{
				//System.out.println("user doesnt have valid token");
				response=false;
			}
			json = new Gson().toJson(tokenOut);
			
			
			
		//int i=st.executeUpdate(query);
			
		} catch (Exception e) {
			//System.out.println(e + " " + "Connection error");
			// response=true;
		}
	//	//System.out.println(json+"hey I am here");

//System.out.println(response+"Microservice");
		logger.debug("Get token");
		
		return Response.ok().entity(String.valueOf(json)).build();

	}

	
}
