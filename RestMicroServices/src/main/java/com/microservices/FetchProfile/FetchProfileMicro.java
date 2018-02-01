package com.microservices.FetchProfile;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Path("/fetchprofile")
public class FetchProfileMicro 
{
	final static Logger logger = Logger.getLogger(FetchProfileMicro.class);
	@Path("/fetchprofile")
	@POST
	public Response fetchprofile(MultivaluedMap<String, String> formParam) {
		//System.out.println("webservicce fetch");
		logger.debug("Start Authentication in Micro Service");
		String name=formParam.getFirst("name");
		String reqToken=formParam.getFirst("token");
		String ActualToken=null;
		
		try {
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/RestMicroServices/gettokenmcroservice/gettoken");
			MultivaluedMap formData = new MultivaluedMapImpl();
			/*//System.out.println(newp +"new password in controller");
			formData.add("password", newp);*/
			formData.add("name", name);
			
			/*.*/ClientResponse restResponse = webResource
			    .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
			    .post(ClientResponse.class, formData);
			String json = restResponse.getEntity(String.class);
			Gson gs= new Gson();
			ObjectMapper mapper = new ObjectMapper();
			ActualToken = String.valueOf(mapper.readValue(json, int.class));
			
		}
		catch(Exception e) {
			//System.out.println(e);
			
		}
		String json=null;
		boolean response = false;
		HashMap<String,String> ans=new HashMap<String,String>();
		if(ActualToken.equals(reqToken)) {
			//System.out.println("Authentication Micro");
			logger.debug("User has been Authenticated");
			
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/giftregistry";
			String username = "root";
			String password1 = "root";
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(url, username, password1);
			// response=true;

			//System.out.println("connection established");
			Statement st = conn.createStatement();
			//System.out.println(formParam.getFirst("name")+"micro");
			String query="select username, email_id,address,mobileno,securityans,first_name,last_name from giftregistry.userdetails  where username='"+formParam.getFirst("name") +"'";
			
			//System.out.println(query);
			
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
			ans.put("name", rs.getString(1));
			ans.put("emailid", rs.getString(2));
			ans.put("address", rs.getString(3));
			ans.put("mobileno", rs.getString(4));
			ans.put("securityans", rs.getString(5));
			ans.put("first_name", rs.getString(6));
			ans.put("last_name", rs.getString(7));
			
			}
			
	// query="select shared from giftregistry.registry  where name='"+formParam.getFirst("name") +"'";
			
			////System.out.println(query);
			
		//	 rs = st.executeQuery(query);
			 /*while(rs.next()) {
				 ans.put("shared", rs.getString(1));
						 
			 }
			*///System.out.println(json);
			json= new Gson().toJson(ans);
			
			
			
		//int i=st.executeUpdate(query);
			
		} catch (Exception e) {
			//System.out.println(e + " " + "Connection error");
			// response=true;
		}}
	//	//System.out.println(json+"hey I am here");

//System.out.println(response+"Microservice");
		logger.debug("Fetched User Profile");
		
		return Response.ok().entity(String.valueOf(json)).build();

	}

	@Path("/updateprofile")
	@POST
	@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
	public Response insertDetails(MultivaluedMap<String, String> formParam)
	{
		boolean response = false;
		try
		{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/giftregistry";
			String username = "root";
			String password1 = "root";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password1);
			//response=true;
			Statement st = conn.createStatement();

			//System.out.println(formParam+"micro");
			//System.out.println("connection established");
			String sql="update  `giftregistry`.`userdetails` set email_id='"+formParam.getFirst("emailid")+"',address='"+formParam.getFirst("address")+"',mobileno='"+Integer.parseInt(formParam.getFirst("mobileno"))+"',securityans='"+formParam.getFirst("securityans")+"',first_name='"+formParam.getFirst("first_name")+"',last_name='"+formParam.getFirst("last_name")+"'  where username='"+formParam.getFirst("name")+"'";
		//	(`name`, `email_id`, `password`) VALUES ('"+formParam.getFirst("username")+"', '"+formParam.getFirst("email")+"', '"+formParam.getFirst("pass")+"')";
			//System.out.println(sql);
			int i=st.executeUpdate(sql);
			//System.out.println(formParam.getFirst("shared")+"microservice checkbox value");
			
			 sql="update  `giftregistry`.`registry` set shared="+formParam.getFirst("shared")+" where name='"+formParam.getFirst("name")+"'";
			//	(`name`, `email_id`, `password`) VALUES ('"+formParam.getFirst("username")+"', '"+formParam.getFirst("email")+"', '"+formParam.getFirst("pass")+"')";
				//System.out.println(sql +"update query");
				int j=st.executeUpdate(sql);
				if(i>0 && j>0) {
					response = true;
						
				}
				else {
			//	//System.out.println("user doesn't exist");
				response = false;
				}
			
			
		}
		catch(Exception e)
		{
			//System.out.println(e+" "+"duffer check your sql");						
			//response=true;
		}
		logger.debug("User profile is updated");
		
		return Response.ok().entity(String.valueOf(response)).build();
		
	}
	@Path("/fetchfriends")
	@POST
	@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
	public Response FetchFriends(MultivaluedMap<String, String> formParam)
	{
		boolean response = false;
		String json=null;
		try
		{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/giftregistry";
			String username = "root";
			String password1 = "root";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password1);
			//response=true;
			Statement st = conn.createStatement();

			//System.out.println(formParam+"micro");
			//System.out.println("connection established");
			//String sql="update  `giftregistry`.`userdetails` set emailid='"+formParam.getFirst("emailid")+"',address='"+formParam.getFirst("address")+"',mobileno='"+formParam.getFirst("mobileno")+"',securityans='"+formParam.getFirst("securityans")+"',first_name='"+formParam.getFirst("first_name")+"',last_name='"+formParam.getFirst("last_name")+"'  where name='"+formParam.getFirst("name")+"'";
			 String query="select friends from giftregistry.sharedregistry  where name='"+formParam.getFirst("name") +"'";
				
			ArrayList<String> ans=new ArrayList<String>();
			 ResultSet rs = st.executeQuery(query);
				while(rs.next()) {
				ans.add(rs.getString(1));
				}
				//System.out.println(ans);
				 json= new Gson().toJson(ans);
					
		}
		catch(Exception e)
		{
			//System.out.println(e+" "+"duffer check your sql");						
			//response=true;
		}
		logger.debug("Fetched friends of User");
		
		return Response.ok().entity(String.valueOf(json)).build();

	}
	
	
	@Path("/checkuseranswer")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response isValidUserAnswer(MultivaluedMap<String, String> formParam) {
		//System.out.println("webservicce");
		boolean response = false;
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/giftregistry";
			String username = "root";
			String password1 = "root";
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(url, username, password1);
			// response=true;

			//System.out.println("connection established");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT securityans FROM giftregistry.userdetails where username='"
					+ formParam.getFirst("username") + "'");
			//System.out.println("SELECT securityans FROM giftregistry.userdetails where username='"
				//	+ formParam.getFirst("username") + "'");
			while(rs.next()) {
				//System.out.println(rs.getString("securityans").equals(formParam.getFirst("friend")));
				//System.out.println(formParam.getFirst("friend"));
				//System.out.println(rs.getString("securityans"));
				if (rs.getString("securityans").equals(formParam.getFirst("friend"))) {
					response = true;
				}
			
			else {
		//	//System.out.println("user doesn't exist");
			response = false;
			}}
		} catch (Exception e) {
			//System.out.println(e + " " + "Connection error");
			// response=true;
		}
//System.out.println(response+"Microservice");
		logger.debug("Security answer is correct");
		
		return Response.ok().entity(String.valueOf(response)).build();

	}

	@Path("/setpassword")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response setpassword(MultivaluedMap<String, String> formParam) {
		//System.out.println("webservicce");
		boolean response = false;
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/giftregistry";
			String username = "root";
			String password1 = "root";
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(url, username, password1);
			// response=true;

			//System.out.println("connection established");
			Statement st = conn.createStatement();
			String query="UPDATE giftregistry.userdetails SET password ='"+formParam.getFirst("password") +"' where username='"+formParam.getFirst("name") +"'";
			
			//System.out.println(query);
			//ResultSet rs = st.executeQuery(query);
		int i=st.executeUpdate(query);
			if(i>0) {
				response = true;
					
			}
			else {
		//	//System.out.println("user doesn't exist");
			response = false;
			}
		} catch (Exception e) {
			//System.out.println(e + " " + "Connection error");
			// response=true;
		}
//System.out.println(response+"Microservice");
		logger.debug("Password has been set succesfully");
		
		return Response.ok().entity(String.valueOf(response)).build();

	}
	
}
