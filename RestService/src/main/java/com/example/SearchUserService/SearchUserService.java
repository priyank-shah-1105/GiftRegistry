package com.example.SearchUserService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Path("/getUserService")
public class SearchUserService 
{
	@Path("/searchUser")
	@POST
	@Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
	public Response searchUser(String query) 
	{
		String json=null;
		ClientResponse restResponse=null;
		boolean response=false;
		try
		{
			String query1=query;
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/RestMicroServices/searachUsermicro/searchUser");				
			restResponse = webResource
			    .type(MediaType.APPLICATION_JSON)
			    .accept(MediaType.APPLICATION_JSON)
			    .post(ClientResponse.class,query1);
			json=restResponse.getEntity(String.class);
			
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
		if(restResponse.getStatus()==200)
		{
			response=true;
			return Response.ok(json).build();
		}
		else
		{
			response=false;
			return Response.ok(String.valueOf(response)).build();
		}
	}
	//search shared user
	
	@Path("/searchSharedUser")
	@POST
	@Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
	public Response searchSharedUser(String query) 
	{
		String json=null;
		ClientResponse restResponse=null;
		boolean response=false;
		try
		{
			String query1=query;
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/RestMicroServices/searchSharedUsermicro/searchSharedUser");				
			restResponse = webResource
			    .type(MediaType.APPLICATION_JSON)
			    .accept(MediaType.APPLICATION_JSON)
			    .post(ClientResponse.class,query1);
			json=restResponse.getEntity(String.class);
			
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
		if(restResponse.getStatus()==200)
		{
			response=true;
			return Response.ok(json).build();
		}
		else
		{
			response=false;
			return Response.ok(String.valueOf(response)).build();
		}
	}

	
	
	
	
	
	
}
