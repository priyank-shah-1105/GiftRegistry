package com.example.Login;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
//import com.google.gson.Gson.Gson;
/**
 * Servlet implementation class LoginController
 */
@WebServlet("/fetchprofile")
public class fetchProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(LoginController.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fetchProfile() {
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
		/*String newp=request.getParameter("newPassword");
		String oldp=request.getParameter("confirmPassword");*/
		String name=request.getParameter("name");
		//System.out.println(request.getParameter("name") +"controller");
		/*if(!newp.equals(oldp)){
			RequestDispatcher rd=request.getRequestDispatcher("ResetPassword.jsp");
			request.setAttribute("message", "Password Doesn't Match");
			request.setAttribute("name", name);
			
			rd.forward(request, response);
		}else {
			
		*/
		/*LoginBean bean=new LoginBean();
		 * 
		bean.setName(name);
		bean.setPassword(password);
		request.setAttribute("bean",bean);*/
		
		//get tocken
		String c=null;
		try {
			logger.debug("Fetching Token in Controller");
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
			 c = String.valueOf(mapper.readValue(json, int.class));
			
		}
		catch(Exception e) {
			//System.out.println(e);
			
		}
		
		
		
		
		
		
		
		
		Boolean status = false;
		
		//System.out.println("controller"+c);
		try
		{
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/RestService/fetch/fetchprofile");
			MultivaluedMap formData = new MultivaluedMapImpl();
			/*//System.out.println(newp +"new password in controller");
			formData.add("password", newp);*/
			formData.add("name", name);
			formData.add("token", c);
			logger.debug("Passing Token to Webservice");
			
			//System.out.println("token " +c);
//			//System.out.println();
			ClientResponse restResponse = webResource
			    .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
			    .post(ClientResponse.class, formData);
			//System.out.println(restResponse.getStatus()+"controller why");
			
			String statusString = restResponse.getEntity(String.class);
			//System.out.println(statusString);
			//ArrayList<String> a=new ArrayList<String>();
			//String[] a=statusString.split(",");
			HashMap<String,String> list = new Gson().fromJson(statusString,  (new HashMap<String,String>()).getClass());//HashMap<String,String[]>(){}.getClass());
			//System.out.println(list);
			
			if(restResponse.getStatus()!=200)
			{
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			else {
				request.setAttribute("name", list.get("name"));
				request.setAttribute("emailid", list.get("emailid"));
				request.setAttribute("mobileno", list.get("mobileno"));
				request.setAttribute("securityans", list.get("securityans"));
				request.setAttribute("address", list.get("address"));
				request.setAttribute("last_name", list.get("last_name"));
				request.setAttribute("first_name", list.get("first_name"));
			//	request.setAttribute("shared", list.get("shared"));
				
				RequestDispatcher rd=request.getRequestDispatcher("profile.jsp");
				request.setAttribute("message", "Password changed");
				//System.out.println(request.getAttribute("emailid")+"client");
				rd.forward(request, response);
			}
			
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
	
	}

}
