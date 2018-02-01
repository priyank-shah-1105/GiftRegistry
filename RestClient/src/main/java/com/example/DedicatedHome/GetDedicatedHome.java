package com.example.DedicatedHome;

import java.io.IOException;
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

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Servlet implementation class GetDedicatedHome
 */
@WebServlet("/GetDedicatedHomeController")
public class GetDedicatedHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDedicatedHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String product_id=request.getParameter("product_id");
		HttpSession session = request.getSession();
		String username=(String)session.getAttribute("USER");
		ArrayList<HashMap<String,String>> lans=null;
		ArrayList<ArrayList<String>> out =null;
		boolean status=false;
		status=(Boolean) request.getAttribute("status");
		try
		{
			Client client1 = Client.create();
			WebResource webResource1 = client1.resource("http://localhost:8080/RestService/getHomeService/getHome");
			//MultivaluedMap formData = new MultivaluedMapImpl();
			//formData.add("username", name);
			//formData.add("password", password);
			ClientResponse restResponse1 = webResource1
			    .type(MediaType.APPLICATION_JSON)
			    .accept(MediaType.APPLICATION_JSON)
			    .post(ClientResponse.class);
			String json=restResponse1.getEntity(String.class);
			//System.out.println(json+"in servlet");
			/*if(restResponse1.getStatus()!=200)
			{
				throw new RuntimeException("Failed : HTTP error code : " + restResponse1.getStatus());
			}*/
			//File[] list=webResource.accept("application/json").type("application/json").post(new GenericType<File[]>(){});
			//String statusString = restResponse.getEntity(String.class);
			//status = Boolean.parseBoolean(statusString);
			//JsonParser parser = new JsonParser();
			Gson gs= new Gson();
			ObjectMapper mapper = new ObjectMapper();
			lans = mapper.readValue(json, new TypeReference<ArrayList<HashMap<String,String>>>(){});
			//System.out.println("Response before"+restResponse1.getStatus());
			//getting category, brand and color service from here
			request.setAttribute("product_details", lans);
			int reg_id=Integer.parseInt(request.getAttribute("reg_id").toString());
			if(restResponse1.getStatus()==200 && status)
			{
				
				request.setAttribute("Product_details", request.getAttribute("product_details"));
				request.setAttribute("reg_id", reg_id);
				request.setAttribute("success", "Success");
				RequestDispatcher rd=request.getRequestDispatcher("AddParticularItem.jsp");
				rd.forward(request, response);
			}
			else
			{
				//request.setAttribute("listans", lans);
				request.setAttribute("Product_details", request.getAttribute("product_details"));
				request.setAttribute("reg_id", reg_id);
				request.setAttribute("failure", "failure");
				RequestDispatcher rd=request.getRequestDispatcher("AddParticularItem.jsp");
				rd.forward(request, response);
			}
			Client client2 = Client.create();
			WebResource webResource2 = client2.resource("http://localhost:8080/RestService/getFilterService/getFilter");
			//MultivaluedMap formData = new MultivaluedMapImpl();
			//formData.add("username", name);
			//formData.add("password", password);
			ClientResponse restResponse2 = webResource2
			    .type(MediaType.APPLICATION_JSON)
			    .accept(MediaType.APPLICATION_JSON)
			    .post(ClientResponse.class);
			String json2=restResponse2.getEntity(String.class);
			//System.out.println(json2+"in servlet");
			/*if(restResponse1.getStatus()!=200)
			{
				throw new RuntimeException("Failed : HTTP error code : " + restResponse1.getStatus());
			}*/
			//File[] list=webResource.accept("application/json").type("application/json").post(new GenericType<File[]>(){});
			//String statusString = restResponse.getEntity(String.class);
			//status = Boolean.parseBoolean(statusString);
			//JsonParser parser = new JsonParser();
			//Gson gs= new Gson();
			ObjectMapper mapper1 = new ObjectMapper();
			out = mapper1.readValue(json, new TypeReference<ArrayList<ArrayList<String>>>(){});
			//System.out.println("Response before"+restResponse2.getStatus());
			
			
			
		if(restResponse1.getStatus()==200 && status && restResponse2.getStatus()==200)
		{
			
			request.setAttribute("listans", lans);
			request.setAttribute("success", "Success");
			request.setAttribute("filterans", out);
			RequestDispatcher rd=request.getRequestDispatcher("welcome_page.jsp");
			rd.forward(request, response);
		}
		else
		{
			request.setAttribute("listans", lans);
			request.setAttribute("filterans", out);
			request.setAttribute("failure", "failure");
			RequestDispatcher rd=request.getRequestDispatcher("welcome_page.jsp");
			rd.forward(request, response);
		}
	}
		
		catch(Exception e)
		{
			//System.out.println(e);
		}
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}
}
