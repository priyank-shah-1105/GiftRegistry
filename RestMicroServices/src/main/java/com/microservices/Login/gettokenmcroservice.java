package com.microservices.Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.microservices.Register.checkusernameservice;
@Path("/gettokenmcroservice1")
public class gettokenmcroservice {
	final static Logger logger = Logger.getLogger(gettokenmcroservice.class);
	
	@Path("/gettoken")
	@POST
	@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
	public Response getToken(MultivaluedMap<String, String> formParam)
	{
		String json = null;
		boolean response = false;
		int tokenOut=0;
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
			//System.out.println("SELECT token FROM giftregistry.userdetails where token="+formParam.getFirst("token")+"and username='"+formParam.getFirst("username")+"'");
			ResultSet rs=st.executeQuery("SELECT token FROM giftregistry.userdetails where token="+formParam.getFirst("token")+"and username='"+formParam.getFirst("username")+"'");
			
			if(rs.next())
			{
				tokenOut = rs.getInt("token");
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
		}
		catch(Exception e)
		{
			//System.out.println(e+" "+"Connection error");						
			//response=true;
		}

		return Response.ok(json).build();
		
	}
}