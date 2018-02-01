package com.example.Login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
	final static Logger logger = Logger.getLogger(UpdateProfile.class);
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		
		////System.out.println(email);
		////System.out.println(username);
		////System.out.println(pass);
	
		Boolean status = false;
		try
		{
			//System.out.println("I ma here");
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/RestService/fetch/updateprofile");
			MultivaluedMap formData1 = new MultivaluedMapImpl();
			formData1.add("name", request.getParameter("name"));
			formData1.add("emailid", request.getParameter("emailid"));
			formData1.add("mobileno", request.getParameter("mobileno"));
			formData1.add("address", request.getParameter("address"));
			formData1.add("securityans", request.getParameter("securityans"));
			formData1.add("first_name", request.getParameter("first_name"));
			formData1.add("last_name", request.getParameter("last_name"));
			String shared="false";
			//System.out.println(request.getParameter("shared")+"checkbox");
			
			if(null != request.getParameter("shared")) {
				shared="true";
			}
			formData1.add("shared", shared);
			
			ClientResponse restResponse = webResource
			    .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
			    .post(ClientResponse.class, formData1);
			if(restResponse.getStatus()!=200)
			{
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			
			String statusString = restResponse.getEntity(String.class);
			status = Boolean.parseBoolean(statusString);

		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
		if(status)
		{
			//check for password
			HttpSession session = request.getSession();
			session.setAttribute("USER", request.getParameter("name"));
			
			//get all images of admin
			try
			{
				Client client = Client.create();
				WebResource webResource = client.resource("http://localhost:8080/RestService/getHomeService/getHome");
				//MultivaluedMap formData = new MultivaluedMapImpl();
				//formData.add("username", name);
				//formData.add("password", password);
				ClientResponse restResponse = webResource
				    .type(MediaType.APPLICATION_JSON)
				    .accept(MediaType.APPLICATION_JSON)
				    .post(ClientResponse.class);
				String json=restResponse.getEntity(String.class);
				//System.out.println(json+"in servlet");
				if(restResponse.getStatus()!=200)
				{
					throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
				}
				//File[] list=webResource.accept("application/json").type("application/json").post(new GenericType<File[]>(){});
				//String statusString = restResponse.getEntity(String.class);
				//status = Boolean.parseBoolean(statusString);
				//JsonParser parser = new JsonParser();
				Gson gs= new Gson();
				ObjectMapper mapper = new ObjectMapper();
				ArrayList<HashMap<String,String>> lans = mapper.readValue(json, new TypeReference<ArrayList<HashMap<String,String>>>(){});
				if(restResponse.getStatus()==200)
				{
					
					request.setAttribute("listans", lans);
					RequestDispatcher rd=request.getRequestDispatcher("welcome_page.jsp");
					rd.forward(request, response);
				}
				else
				{
					//System.out.println("exception");
				}
				
			}
			catch(Exception e)
			{
				//System.out.println(e);
			}
			
		}
		else{
			RequestDispatcher rd=request.getRequestDispatcher("login-error.jsp");
			rd.forward(request, response);
		}
	}

}
