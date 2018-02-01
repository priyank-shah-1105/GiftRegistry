package com.example.addfriend;

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

@Path("/addfriend")
public class AddFriend 
{
	@Path("/addfriend")
	@POST
	public Response addfriend(MultivaluedMap<String, String> formParam) {
		boolean response = false;
		boolean status=false;
		boolean status1=false;
		String json=null;
		try
		{		
			//redirecting to microservice
			Client client = Client.create();
			//System.out.println(formParam.getFirst("name")+"service");
			WebResource webResource = client.resource("http://localhost:8080/RestMicroServices/addfriend/addfriend");
			/*.*/ClientResponse restResponse = webResource
			    .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
			    .post(ClientResponse.class, formParam);
			if(restResponse.getStatus()!=200)
			{
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			
			json=restResponse.getEntity(String.class);
			//	//System.out.println("Here");
				return Response.ok().entity(json).build();
		}
		catch(Exception e)
		{
			//System.out.println(e+" "+"Connection error");						
			//response=true;
		}
		//System.out.println(response+"webservice");
		return Response.ok().entity(String.valueOf(status)).build();
	}
	

	
	
}
