
package com.example.Admin.Product;

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

import com.example.Register.RegisterBean;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.xml.bind.api.TypeReference;

@WebServlet("/fetchcontroller")
public class FetchData extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FetchData() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		////System.out.println("Enter");
		
		try
		{
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/RestService/fetchdata/alldata");
			
			ClientResponse restResponse = webResource
			    .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
			    .post(ClientResponse.class);
		//	//System.out.println("Here");
			String statusString = restResponse.getEntity(String.class);
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
			request.getRequestDispatcher("product.jsp").forward(request, response);
			if(restResponse.getStatus()!=200)
			{
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
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
