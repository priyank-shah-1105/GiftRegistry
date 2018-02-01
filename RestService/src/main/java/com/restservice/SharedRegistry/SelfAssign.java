package com.restservice.SharedRegistry;


import java.util.ArrayList;

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

@Path("/assignReg")
public class SelfAssign {
	@Path("/assignReg")
	@POST
	@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
	public Response insertProduct(MultivaluedMap<String, String> formParam) {
		//System.out.println("Hey Man come here");
		boolean response = false;
		boolean status = false;
		try {
			//System.out.println("webservice"
	//				+ "");
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/RestMicroServices/selfassignMicroService/selfAssign");
			/*MultivaluedMap formData = new MultivaluedMapImpl();
			
			formData.add("productname", formParam.getFirst("productname"));	
			formData.add("productdesc", formParam.getFirst("productdesc"));	
			formData.add("price", formParam.getFirst("price"));	
			formData.add("color", formParam.getFirst("color"));	
			formData.add("category", formParam.getFirst("category"));	*/
			ClientResponse restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formParam);
			//System.out.println(restResponse.getStatus());
			if(restResponse.getStatus()!=200)
			{
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			
			String statusString = restResponse.getEntity(String.class);
			status = Boolean.parseBoolean(statusString);
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		if(status) {
			response = true;
			
		}
		else {
			response = false;
			
		}
		return Response.ok().entity(String.valueOf(response)).build();
	}

	@Path("/deleteProduct")
	@POST
	@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
	public Response deleteProduct(MultivaluedMap<String, String> formParam) {
		boolean response = false;
		boolean status = false;
		try {
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/RestMicroServices/addAndDeleteProductDetailsMicroService/deleteProduct");
			MultivaluedMap formData = new MultivaluedMapImpl();
			
			formData.add("product_id", formParam.getFirst("product_id"));	
			formData.add("productdesc", formParam.getFirst("productdesc"));	
			formData.add("price", formParam.getFirst("price"));	
			formData.add("color", formParam.getFirst("color"));	
			formData.add("category", formParam.getFirst("category"));	
			ClientResponse restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData);
			//System.out.println("here or not");
			if(restResponse.getStatus()!=200)
			{
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			
			String statusString = restResponse.getEntity(String.class);
			status = Boolean.parseBoolean(statusString);
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		if(status) {
			response = true;
			
		}
		else {
			response = false;
			
		}
		return Response.ok().entity(String.valueOf(response)).build();
	}
}
