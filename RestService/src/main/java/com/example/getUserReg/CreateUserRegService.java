package com.example.getUserReg;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Path("/CreateUserRegService")
public class CreateUserRegService 
{

	@Path("/CreateUserReg")
	@POST
	@Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
	public Response getUserReg(String combine) 
	{
		String json=null;
		String json2=null;
		String finaljson=null;
		ClientResponse restResponse=null;
		ClientResponse restResponse1=null;
		ClientResponse restResponse2=null; 
		boolean response=false;
		try
		{
			String json1=combine;
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/RestMicroServices/MicroCheckRegName/checkRegName");				
			restResponse = webResource
			    .type(MediaType.APPLICATION_JSON)
			    .accept(MediaType.APPLICATION_JSON)
			    .post(ClientResponse.class,json1);
			json=restResponse.getEntity(String.class);
			ObjectMapper mapper = new ObjectMapper();
			boolean flag = mapper.readValue(json, new TypeReference<Boolean>(){});
			if(flag)
			{
				////System.out.println("here");
				//call another microservice to insert the data
				Client client1 = Client.create();
				WebResource webResource1 = client1.resource("http://localhost:8080/RestMicroServices/MicroInsertRegName/insertRegName");				
				restResponse1 = webResource1
				    .type(MediaType.APPLICATION_JSON)
				    .accept(MediaType.APPLICATION_JSON)
				    .post(ClientResponse.class,json1);
				json2=restResponse1.getEntity(String.class);
				//json2 will contain all the registry names
				
				Client client2 = Client.create();
				WebResource webResource2 = client2.resource("http://localhost:8080/RestMicroServices/getHomeValuesmicro/getHomeValues");				
				restResponse2 = webResource2
				    .type(MediaType.APPLICATION_JSON)
				    .accept(MediaType.APPLICATION_JSON)
				    .post(ClientResponse.class);
				String json3 = restResponse2.getEntity(String.class);
				//System.out.println(json3);
				//json3 will contain all the info regarding the products
				//converting both json to original format and again passing it to previous page
				ArrayList<String> finalans=new ArrayList<String>();
				finalans.add(json2);
				finalans.add(json3);
				finaljson=new Gson().toJson(finalans);
			}
			else
			{
				//System.out.println("here in else");
			}
			
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
		if(restResponse2.getStatus()==200)
		{
			response=true;
			return Response.ok(finaljson).build();
		}
		else
		{
			response=false;
			return Response.ok(String.valueOf(finaljson)).build();
		}
	}
}
