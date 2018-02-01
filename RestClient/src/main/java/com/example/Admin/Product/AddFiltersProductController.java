package com.example.Admin.Product;

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

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Servlet implementation class AddFiltersProductController
 */
@WebServlet("/AddFiltersProductController")
public class AddFiltersProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFiltersProductController() {
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
		//System.out.println("brand--------------"+request);
		String brand = (String)request.getParameter("brand");
		String category =(String) request.getParameter("category");
		String color = (String)request.getParameter("color");
		//System.out.println("brand"+brand);
		//System.out.println("category"+category);
		//System.out.println("color"+color);
		ProductBean pBean = new ProductBean();
		Client client;
		WebResource webResource;
		MultivaluedMap formData1;
		ClientResponse restResponse = null;
		Boolean status = false;
		String a="";
		ArrayList<HashMap<String,String>> lans=null;
		try {
	//		//System.out.println("im here");
			if(!brand.equals("")) {
				pBean.setBrand(brand);
				request.setAttribute("ProductBean", pBean);
				client = Client.create();
				webResource = client.resource("http://localhost:8080/RestService/productFiltersAndDetailsService/addBrand");
				formData1 = new MultivaluedMapImpl();
				formData1.add("brand", brand);
				restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData1);
				//System.out.println("Brand added");
			}
			if(!category.equals("")) {
				pBean.setBrand(category);
				request.setAttribute("ProductBean", pBean);
				client = Client.create();
				webResource = client.resource("http://localhost:8080/RestService/productFiltersAndDetailsService/addCategory");
				formData1 = new MultivaluedMapImpl();
				formData1.add("category", category);
				restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData1);
			}
			if(!color.equals("")) {
				pBean.setBrand(color);
				request.setAttribute("ProductBean", pBean);
				client = Client.create();
				webResource = client.resource("http://localhost:8080/RestService/productFiltersAndDetailsService/addColor");
				formData1 = new MultivaluedMapImpl();
				formData1.add("color", color);
				restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData1);
					
			}
			if(restResponse.getStatus()!=200)
			{
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			String statusString = restResponse.getEntity(String.class);
			status = Boolean.parseBoolean(statusString);
			
			

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
				//System.out.println("Response before"+restResponse.getStatus());
				
				
			
			if(restResponse1.getStatus()==200 && status)
			{
				
				request.setAttribute("listans", lans);
				
				RequestDispatcher rd=request.getRequestDispatcher("AdminHome.jsp");
				rd.forward(request, response);
			}
			else
			{
				request.setAttribute("listans", lans);
				RequestDispatcher rd=request.getRequestDispatcher("AdminHome.jsp");
				rd.forward(request, response);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
		catch(Exception e)
		{
			//System.out.println(e);
		}
	}

}
