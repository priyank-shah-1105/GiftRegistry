package com.example.Admin.Product;

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

@Path("/productFiltersAndDetailsService")
public class ProductFiltersAndDetailsService {
	@Path("/addBrand")
	@POST
	@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
	public Response addBrand(MultivaluedMap<String, String> formParam) {
		boolean response = false;
		boolean status = false;
		try {
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/RestMicroServices/addAndDeleteProductDetailsMicroService/insertBrand");
			MultivaluedMap formData = new MultivaluedMapImpl();
			formData.add("brand", formParam.getFirst("brand"));			
			ClientResponse restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData);
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
	@Path("/addCategory")
	@POST
	@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
	public Response addCategory(MultivaluedMap<String, String> formParam) {
		boolean response = false;
		boolean status = false;
		try {
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/RestMicroServices/addAndDeleteProductDetailsMicroService/insertCategory");
			MultivaluedMap formData = new MultivaluedMapImpl();
			formData.add("category", formParam.getFirst("category"));			
			ClientResponse restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData);
			
			if(restResponse.getStatus()!=200)
			{
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			
			String statusString = restResponse.getEntity(String.class);
			status = Boolean.parseBoolean(statusString);
			
		}
		catch(Exception e) {
			
		}
		if(status) {
			response = true;
			
		}
		else {
			response = false;
			
		}
		return Response.ok().entity(String.valueOf(response)).build();
	}
	@Path("/addColor")
	@POST
	@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
	public Response addColor(MultivaluedMap<String, String> formParam) {
		boolean response = false;
		boolean status = false;
		try {
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/RestMicroServices/addAndDeleteProductDetailsMicroService/insertColor");
			MultivaluedMap formData = new MultivaluedMapImpl();
			formData.add("color", formParam.getFirst("color"));			
			ClientResponse restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData);
			
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
