package com.example.Register;

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

@Path("/registerservices")
public class RegisterServices {
	@Path("/checkuser")
	@POST
	@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
	public Response isUserExists(MultivaluedMap<String, String> formParam) {
		boolean response = false;
		boolean status=false;
		boolean status1=false;
		try
		{		
			//redirecting to microservice
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/RestMicroServices/regusernamemicroservice/checkusernamereg");
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
				//System.out.println("The username already exists and redirect to login");
				response=false;
			}
			catch(Exception e)
			{
				//System.out.println(e+" "+"Connection error");						
				//response=true;
			}
			
		}
		else
		{
			////System.out.println("The user name doesn't exist and now do inserting part");
			try
			{		
				//redirecting to microservice
				Client client = Client.create();
				WebResource webResource = client.resource("http://localhost:8080/RestMicroServices/insertusernamemicroservice/insertusernamereg");
				MultivaluedMap formData = new MultivaluedMapImpl();
				formData.add("username", formParam.getFirst("username"));
				formData.add("email", formParam.getFirst("email"));
				formData.add("pass", formParam.getFirst("password"));
				ClientResponse restResponse = webResource
				    .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
				    .post(ClientResponse.class, formData);
				if(restResponse.getStatus()!=200)
				{
					throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
				}
				
				String statusString = restResponse.getEntity(String.class);
				status1 = Boolean.parseBoolean(statusString);
			}
			catch(Exception e)
			{
				//System.out.println(e+" "+"Connection error");						
				//response=true;
			}
			if(status1)
			response=true;
			else
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
