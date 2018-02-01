package com.example.getHome;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Path("/getHomeService")
public class GetHomeService 
{
	@Path("/getHome")
	@POST
	@Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
	public Response getHome(String ans) 
	{
		String json=null;
		ClientResponse restResponse=null;
		boolean response=false;
		try
		{
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/RestMicroServices/getHomeValuesmicro/getHomeValues");				
			restResponse = webResource
			    .type(MediaType.APPLICATION_JSON)
			    .accept(MediaType.APPLICATION_JSON)
			    .post(ClientResponse.class);
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
