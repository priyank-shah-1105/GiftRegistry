package com.example.Registry;

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
 * Servlet implementation class CreateRegController
 */
@WebServlet("/CreateRegController")
public class CreateRegController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRegController() {
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
		HttpSession session = request.getSession();
		String username=(String)session.getAttribute("USER");
		
		try
		{
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/RestService/GetFriendsService/GetFriends");
			//MultivaluedMap formData = new MultivaluedMapImpl();
			//formData.add("username", name);
			//formData.add("password", password);
			ClientResponse restResponse = webResource
			    .type(MediaType.APPLICATION_JSON)
			    .accept(MediaType.APPLICATION_JSON)
			    .post(ClientResponse.class,username);
			String json=restResponse.getEntity(String.class);
			//System.out.println(json+"in servlet");
			Gson gs= new Gson();
			ObjectMapper mapper = new ObjectMapper();
			ArrayList<String> lans = mapper.readValue(json, new TypeReference<ArrayList<String>>(){});
			if(restResponse.getStatus()==200)
			{
				
				request.setAttribute("listans", lans);
				RequestDispatcher rd=request.getRequestDispatcher("chooseFriends.jsp");
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

}
