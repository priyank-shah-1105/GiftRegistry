package com.example.DeleteRegItemService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Path("/deleteRegItemService")
public class DeleteRegItem 
{
	@Path("/deleteRegitem")
	@POST
	@Consumes({"application/json","application/x-www-form-urlencoded"})
    @Produces(MediaType.TEXT_PLAIN)
	public Response addRegistry(MultivaluedMap<String, String> formParam) 
	{
		String json=null;
		ClientResponse restResponse=null;
		boolean response=false;
		boolean status=false;
		try
		{
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/RestMicroServices/deleteRegItemMicro/deleteRegItem");
			
			restResponse = webResource
			    .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)			    
			    .post(ClientResponse.class,formParam);
			if(restResponse.getStatus()!=200)
			{
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			String statusString = restResponse.getEntity(String.class);
			status = Boolean.parseBoolean(statusString);
			if(status)
			{
				response=true;
			}
			else
				response=false;
			return Response.ok().entity(String.valueOf(response)).build();
			
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
