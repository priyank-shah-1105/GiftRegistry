package com.microservices.Register;

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

@Path("/regusernamemicroservice")
public class checkusernameservice 
{
	final static Logger logger = Logger.getLogger(checkusernameservice.class);
	
	@Path("/checkusernamereg")
	@POST
	@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
	public Response isUserNameExists(MultivaluedMap<String, String> formParam)
	{
		boolean response = false;
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
			ResultSet rs=st.executeQuery("SELECT username FROM giftregistry.userdetails where username='"+formParam.getFirst("username")+"'");
			if(rs.next())
			{
				//System.out.println("The username exists and is"+rs.getString("username"));
				response=true;
			}
			else
			{
				//System.out.println("user doesn't exist");
				response=false;
			}
		}
		catch(Exception e)
		{
			//System.out.println(e+" "+"Connection error");						
			//response=true;
		}

		return Response.ok().entity(String.valueOf(response)).build();
		
	}
}
