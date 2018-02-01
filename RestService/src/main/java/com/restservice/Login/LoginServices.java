package com.restservice.Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Path("/loginservices")
public class LoginServices {
	@Path("/checkuservalidity")
	@POST
	@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
	public Response isValidUser(MultivaluedMap<String, String> formParam) {
		boolean response = false;
		boolean status=false;
		boolean status1=false;
		try
		{		
			//redirecting to microservice
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/RestMicroServices/usernamemicroservice/checkusername");
			MultivaluedMap formData = new MultivaluedMapImpl();
			formData.add("username", formParam.getFirst("username"));			
			ClientResponse restResponse = webResource
			    .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
			    .post(ClientResponse.class, formData);
			if(restResponse.getStatus()!=200)
			{
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			
			String statusString = restResponse.getEntity(String.class);
			status = Boolean.parseBoolean(statusString);
		}
		catch(Exception e)
		{
			//System.out.println(e+" "+"Connection error");						
			//response=true;
		}
		if(status)
		{
			////System.out.println("the username exists in loginwebservice");
			//checkng for password
			try
			{		
				//redirecting to microservice
				Client client = Client.create();
				WebResource webResource = client.resource("http://localhost:8080/RestMicroServices/userpassmicroservice/checkpassword");
				MultivaluedMap formData = new MultivaluedMapImpl();	
				formData.add("username", formParam.getFirst("username"));
				formData.add("password", formParam.getFirst("password"));
				ClientResponse restResponse = webResource
				    .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
				    .post(ClientResponse.class, formData);
				if(restResponse.getStatus()!=200)
				{
					throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
				}
				
				String statusString1 = restResponse.getEntity(String.class);
				status1 = Boolean.parseBoolean(statusString1);
			}
			catch(Exception e)
			{
				//System.out.println(e+" "+"Connection error");						
				//response=true;
			}
			if(status1)
			{
				response=true;
				//System.out.println("The password is true");
			}			
			else
				response=false;
		}
		else
		{
			//System.out.println("The user name doesn't exist");
			response=false;
		}
		/*if(formParam.getFirst("password").equals("admin")){
			response = true;
		}
		else{
			response = false;
		}*/
		return Response.ok().entity(String.valueOf(response)).build();
	}
	
	@Path("/availableusername/{username}")
	@GET
	public String availableUsername(@PathParam("username") String username) {
		
		return username + "001";
	}
	
	
	
	
	
}
