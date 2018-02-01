package com.restservice.getProduct;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Path("/getimages")
public class GetImagesAdmin 
{
	@Path("/getImagesAdmin")
	@POST
	@Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
	public Response getImages(String ans) 
	{
		GenericEntity ge=null;
		boolean response=false;
		File[] list=null;
		String json=null;
		try
		{
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/RestMicroServices/getImagesmicro/getImage");
					
			ClientResponse restResponse = webResource
			    .type(MediaType.APPLICATION_JSON)
			    .accept(MediaType.APPLICATION_JSON)
			    .post(ClientResponse.class);
			
			json=restResponse.getEntity(String.class);
			
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
		return Response.ok().entity(json).build();
	}
}
