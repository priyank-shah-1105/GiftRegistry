
package com.example.sharedRegistry;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
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

import com.example.Register.RegisterBean;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.xml.bind.api.TypeReference;

@WebServlet("/SelfAssign")
public class SelfAssignController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SelfAssignController() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: hii ").append(request.getContextPath());
		String productid=request.getParameter("productid");
		//System.out.println(productid);
		String friend=request.getParameter("friend");
		//System.out.println(friend);
		String reg_name=request.getParameter("regname");
	//System.out.println(reg_name);
		HttpSession session = request.getSession();
		String username=(String)session.getAttribute("USER");
		
		
		
		try
		{
			
			Client client = Client.create();
			//WebResource webResource = client.resource("http://localhost:8002/RestService/fetchdata/alldata");
			WebResource webResource = client.resource("http://localhost:8080/RestService/assignReg/assignReg");
			MultivaluedMap formData1 = new MultivaluedMapImpl();
			formData1.add("productid", request.getParameter("productid"));
			formData1.add("friend", request.getParameter("friend"));
			formData1.add("reg_name", request.getParameter("regname"));
			formData1.add("user", username);
			
			ClientResponse restResponse = webResource
			    .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
			    .post(ClientResponse.class,formData1);
		
			//System.out.println(restResponse.getStatus() +"controller");
		if(restResponse.getStatus()!=200)
			{
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
		
			//request.getRequestDispatcher("index.jsp").forward(request, response);;
			
			if(restResponse.getStatus()==200)
			{
				RequestDispatcher rd=request.getRequestDispatcher("sharedRegistry");
			//	request.setAttribute(arg0, arg1);
				rd.forward(request, response);	
			}
				
	}
		catch(Exception e)
		{
			//System.out.println(e);
		}
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		////System.out.println("Enter");
		
		try
		{
			
			Client client = Client.create();
			//WebResource webResource = client.resource("http://localhost:8002/RestService/fetchdata/alldata");
			WebResource webResource = client.resource("http://localhost:8080/RestService/insertAndDeleteProductsService/insertProduct");
			MultivaluedMap formData1 = new MultivaluedMapImpl();
			formData1.add("productname", request.getParameter("productname"));
			formData1.add("productdesc", request.getParameter("productdesc"));
			formData1.add("brand", request.getParameter("brand"));
			formData1.add("color", request.getParameter("color"));
			formData1.add("price", request.getParameter("price"));
			formData1.add("category", request.getParameter("category"));
			ClientResponse restResponse = webResource
			    .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
			    .post(ClientResponse.class,formData1);
		
			//System.out.println(restResponse.getStatus() +"controller");
			//	//System.out.println("Here");
		/*	String statusString = restResponse.getEntity(String.class);
			//System.out.println(statusString);
			//ArrayList<String> a=new ArrayList<String>();
			//String[] a=statusString.split(",");
			HashMap<String,ArrayList<String>> list = new Gson().fromJson(statusString,  (new HashMap<String,ArrayList<String>>()).getClass());//HashMap<String,String[]>(){}.getClass());
			//System.out.println(list);
			////System.out.println(list[0]+"here");
			//request.setAttribute("brand",(String[]) list.get("brand").toArray());
			
			request.setAttribute("brand", list.get("brand").toArray(new String[0]));
			////System.out.println(list.get("brand").toArray(new String[0]).length);
			request.setAttribute("category", list.get("category").toArray(new String[0]));
			request.setAttribute("color",list.get("color").toArray(new String[0]));
			
			//RequestDispatcher rd=
			request.getRequestDispatcher("admin.jsp").forward(request, response);
			*/if(restResponse.getStatus()!=200)
			{
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
		
			//request.getRequestDispatcher("index.jsp").forward(request, response);;
			
			if(restResponse.getStatus()==200)
			{
				//check for password
				HttpSession session = request.getSession();
				session.setAttribute("USER", "admin");
				
				//get all images of admin
				try
				{
					 client = Client.create();
				webResource = client.resource("http://localhost:8080/RestService/getHomeService/getHome");
					//MultivaluedMap formData = new MultivaluedMapImpl();
					//formData.add("username", name);
					//formData.add("password", password);
				 restResponse = webResource
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
					//ArrayList<HashMap<String,String>> lans = mapper.readValue(json, new TypeReference<ArrayList<HashMap<String,String>>>(){});
					ArrayList<HashMap<String,String>> lans = mapper.readValue(json, new ArrayList<HashMap<String,String>>().getClass());
					
					if(restResponse.getStatus()==200)
					{
						RequestDispatcher rd=request.getRequestDispatcher("AdminHome.jsp");
						
						request.setAttribute("listans", lans);
						
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
			
			
			
			
			
			
			
			
		//	String statusString = restResponse.getEntity(String.class);
			

		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
		/*if(status)
		{
			//check for password
			HttpSession session = request.getSession();
			session.setAttribute("USER", username);
			RequestDispatcher rd=request.getRequestDispatcher("welcome_page.jsp");
			rd.forward(request, response);
		}
		else{
			RequestDispatcher rd=request.getRequestDispatcher("Register.jsp");
			rd.forward(request, response);
			PrintWriter out=response.getWriter();
			out.println("username already exists");
		}*/
	}
}
