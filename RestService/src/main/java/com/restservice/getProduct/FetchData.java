package com.restservice.getProduct;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Path("/fetchdata")
public class FetchData {

	@Path("/alldata")
	@POST
	
	public Response alldata() {
		String json=null;
		try
		{
			Client client = Client.create();
			
			WebResource webResource = client.resource("http://localhost:8080/RestMicroServices/fetchdatamicro/alldata");
			//WebResource webResource = client.resource("http://localhost:8002/RestService/fetchdata/alldata");
			
			ClientResponse restResponse = webResource
			    .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
			    .post(ClientResponse.class);
			json=restResponse.getEntity(String.class);
		//	//System.out.println("Here");
			return Response.ok().entity(json).build();
		//	String statusString = restResponse.getEntity(String.class);
			

		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
		
		return null;
	
		
	}
}
