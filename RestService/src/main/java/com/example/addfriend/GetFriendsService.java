package com.example.addfriend;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Path("/GetFriendsService")
public class GetFriendsService 
{
	@Path("/GetFriends")
	@POST
	@Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
	public Response getUserReg(String user) 
	{
		String json=null;
		ClientResponse restResponse=null;
		boolean response=false;
		try
		{
			String username=user;
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/RestMicroServices/GetfriendsMicro/GetFriends");				
			restResponse = webResource
			    .type(MediaType.APPLICATION_JSON)
			    .accept(MediaType.APPLICATION_JSON)
			    .post(ClientResponse.class,username);
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
