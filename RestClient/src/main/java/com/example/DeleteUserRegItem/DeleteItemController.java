package com.example.DeleteUserRegItem;

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
import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Servlet implementation class DeleteItem
 */
@WebServlet("/DeleteItemController")
public class DeleteItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteItemController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String productid=request.getParameter("productid");
		String registryid=request.getParameter("regid");
		
		HttpSession session = request.getSession();
		String username=(String)session.getAttribute("USER");
		ArrayList<HashMap<String,String>> lans=null;
		boolean status=false;
		try
		{
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/RestService/deleteRegItemService/deleteRegitem");
			MultivaluedMap formData1 = new MultivaluedMapImpl();
			formData1.add("productid", productid);
			formData1.add("username", username);
			formData1.add("registryid", registryid);
			
			//formData1.add("password", pass);
			//formData1.add("email", email);
			ClientResponse restResponse = webResource
			    .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
			    .post(ClientResponse.class, formData1);
			/*if(restResponse.getStatus()!=200)
			{
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}*/
			String statusString = restResponse.getEntity(String.class);
			status = Boolean.parseBoolean(statusString);
			//System.out.println("delete controller"+status);
			if(status)
			{
				//call webservice to display the data
				Client client1 = Client.create();
				WebResource webResource1 = client1.resource("http://localhost:8080/RestService/getUserRegService/getUserReg");
				//MultivaluedMap formData = new MultivaluedMapImpl();
				//formData.add("username", name);
				//formData.add("password", password);
				ClientResponse restResponse1 = webResource1
				    .type(MediaType.APPLICATION_JSON)
				    .accept(MediaType.APPLICATION_JSON)
				    .post(ClientResponse.class,username);
				String json=restResponse1.getEntity(String.class);
				//System.out.println(json+"in servlet");
				
				Gson gs= new Gson();
				ObjectMapper mapper = new ObjectMapper();
				lans = mapper.readValue(json, new TypeReference<ArrayList<HashMap<String,String>>>(){});
				//System.out.println("Response before"+restResponse.getStatus());
				if(restResponse1.getStatus()==200 && status)
				{
					
					request.setAttribute("listans", lans);
					request.setAttribute("success", "Success");
					RequestDispatcher rd=request.getRequestDispatcher("User_Reg.jsp");
					rd.forward(request, response);
				}
				else
				{
					request.setAttribute("listans", lans);
					request.setAttribute("failure", "failure");
					RequestDispatcher rd=request.getRequestDispatcher("User_Reg.jsp");
					rd.forward(request, response);
				}
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
