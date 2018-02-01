package com.example.sharedRegistry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

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
import com.google.gson.internal.StringMap;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Servlet implementation class DisplayRegController
 */
@WebServlet("/sharedRegistry")
public class SharedRegistryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(SharedRegistryController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SharedRegistryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		////System.out.println("you are in dopost");
		//System.out.println("In do post");
		HttpSession session = request.getSession();
		String username=(String)session.getAttribute("USER");
		//System.out.println(username);
		try
		{
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8080/RestService/getSharedReg/getSharedReg");
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
		//	ArrayList<HashMap<String,String>> lans = mapper.readValue(json, new HashMap<HashMap<String,String>,HashMap<String,String>>().getClass());
			
			//HashMap<HashMap<String,String>,HashMap<String,String>> list = new Gson().fromJson(json,  (new HashMap<HashMap<String,String>,HashMap<String,String>>().getClass()));//HashMap<String,String[]>(){}.getClass());
			//HashMap<String,HashMap<String,String>> list = new Gson().fromJson(json,  (new HashMap<String,HashMap<String,String>>().getClass()));//HashMap<String,String[]>(){}.getClass());
			//ArrayList<HashMap<String,String>> list = new Gson().fromJson(json,  (new ArrayList<HashMap<String,String>>().getClass()));//HashMap<String,String[]>(){}.getClass());
			ArrayList<HashMap<String,String>> list = mapper.readValue(json, new TypeReference<ArrayList<HashMap<String,String>>>(){});
				
			
			
			////System.out.println("controller"+list);
		
			ArrayList<String[]> namereg=new ArrayList<String[]>();
			//System.out.println(list.size());
			//System.out.println("sharedregstrycontroller");
			for(int i=0;i<list.size();i++)
			{
			HashMap<String,String>hm= list.get(i);		
			//System.out.println("insode");
				//System.out.println(hm.get("friend"));
				String[] a=new String[2];
				a[0]=list.get(i).get("friend");
				a[1]=list.get(i).get("reg_name");
				//System.out.println(a[0]);
				if(!namereg.contains(a))
				{namereg.add(a);
				//System.out.println(a);
				}
			}
			//System.out.println("controllernamereg"+namereg);
			
			//System.out.println("controller shared"+list);
			/*Set<String> key=list.keySet();
			//ArrayList<HashMap<String,String>> a=(List)key.toArray();
			//System.out.println("client"+key);
			 Iterator<String> it = key.iterator();
			 ArrayList<HashMap<String,String>> ans=new ArrayList<HashMap<String,String>>();
			 
		     while(it.hasNext()){
		    	 //System.out.println("inside");
		    	 HashMap<String,String> temp=new HashMap<String, String>();
		    	// ans.add(it.next());
		    	 HashMap<String, String> keyValuePairs = list.get(it.next());//.get("productid");  
		    	 //System.out.println(keyValuePairs.get("productid"));
		    	// //System.out.println(list.get(it.next().get("productid")));
		    	 String[]a=list.get(it.next()).toString().split(",");
		    	 
		    	 for(int i=0;i<a.length;i++) {
		    		 String[] b=a[i].split("=");
		    		 temp.put(b[0], b[1]);
		    	 }
		    	 //System.out.println(temp);
		    	 //System.out.println(a.length);
		    	 HashMap<String, String> keyValuePairs = list.get(it.next());//.get("productid");  
		    	 //System.out.println(keyValuePairs.get("productid"));
		    	 //System.out.println(it.next().get("productid"));
		    	 temp.put("productid", it.next().get("productid"));
		    	 temp.put("product_desc", it.next().get("product_desc"));
		    	 temp.put("product_price", it.next().get("product_price"));
		    	 temp.put("category_name", it.next().get("category_name"));
		    	 temp.put("brand_name", it.next().get("brand_name"));
		    	 temp.put("color_name", it.next().get("color_name"));
		    	 ans.add(temp);
		    	 //System.out.println(ans);
		    	 
		    	 //System.out.println(it.next());
		    	 String keyValuePairs = it.next().get("productid");  
		    	 //System.out.println(keyValuePairs);
		    	
		    	 //System.out.println(list.get(it.next()));
		    	 HashMap<String, String> h=(HashMap<String, String>)it.next();
		        //System.out.println("hey"+h.get("friend"));
		        //System.out.println(it.next().get("productid"));
		        //System.out.println(it.next().get("reg_name"));
		        
		     }*/
			if(restResponse.getStatus()==200)
			{
				
				request.setAttribute("listans", list);
				RequestDispatcher rd=request.getRequestDispatcher("SharedReg.jsp");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				//doGet(request, response);
				////System.out.println("you are in dopost");
				//System.out.println("In do post");
				HttpSession session = request.getSession();
				String username=(String)session.getAttribute("USER");
				//System.out.println(username);
				
				
				
				logger.debug("Fetching Shared Registry");
				
				try
				{
					Client client = Client.create();
					WebResource webResource = client.resource("http://localhost:8080/RestService/getSharedReg/getSharedReg");
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
				//	ArrayList<HashMap<String,String>> lans = mapper.readValue(json, new HashMap<HashMap<String,String>,HashMap<String,String>>().getClass());
					
					//HashMap<HashMap<String,String>,HashMap<String,String>> list = new Gson().fromJson(json,  (new HashMap<HashMap<String,String>,HashMap<String,String>>().getClass()));//HashMap<String,String[]>(){}.getClass());
					//HashMap<String,HashMap<String,String>> list = new Gson().fromJson(json,  (new HashMap<String,HashMap<String,String>>().getClass()));//HashMap<String,String[]>(){}.getClass());
					//ArrayList<HashMap<String,String>> list = new Gson().fromJson(json,  (new ArrayList<HashMap<String,String>>().getClass()));//HashMap<String,String[]>(){}.getClass());
					ArrayList<HashMap<String,String>> list = mapper.readValue(json, new TypeReference<ArrayList<HashMap<String,String>>>(){});
						
					
					
				//	//System.out.println("controller"+list);
					ArrayList<String> name=new ArrayList<String>();
					ArrayList<String> reg=new ArrayList<String>();
					
					//System.out.println(list.size());
					////System.out.println("sharedregstrycontroller");
					for(int i=0;i<list.size();i++)
					{
					HashMap<String,String>hm= list.get(i);		
					////System.out.println("insode");
						//System.out.println(hm.get("friend"));
						String[] a=new String[2];
						a[0]=hm.get("friend");
						a[1]=hm.get("reg_name");
						//System.out.println(a[0]);
						if(!name.contains(a[0]))
						{name.add(a[0]);
					//	//System.out.println(a);
						}
						if(!reg.contains(a[0]))
						{reg.add(a[1]);
				//		//System.out.println(a);
						}
					}
					//System.out.println("controllernamereg"+name);
					//System.out.println("controllernamereg"+reg);
					
					
					
					
					
					/*Set<String> key=list.keySet();
					//ArrayList<HashMap<String,String>> a=(List)key.toArray();
					//System.out.println("client"+key);
					 Iterator<String> it = key.iterator();
					 ArrayList<HashMap<String,String>> ans=new ArrayList<HashMap<String,String>>();
					 
				     while(it.hasNext()){
				    	 //System.out.println("inside");
				    	 HashMap<String,String> temp=new HashMap<String, String>();
				    	// ans.add(it.next());
				    	 HashMap<String, String> keyValuePairs = list.get(it.next());//.get("productid");  
				    	 //System.out.println(keyValuePairs.get("productid"));
				    	// //System.out.println(list.get(it.next().get("productid")));
				    	 String[]a=list.get(it.next()).toString().split(",");
				    	 
				    	 for(int i=0;i<a.length;i++) {
				    		 String[] b=a[i].split("=");
				    		 temp.put(b[0], b[1]);
				    	 }
				    	 //System.out.println(temp);
				    	 //System.out.println(a.length);
				    	 HashMap<String, String> keyValuePairs = list.get(it.next());//.get("productid");  
				    	 //System.out.println(keyValuePairs.get("productid"));
				    	 //System.out.println(it.next().get("productid"));
				    	 temp.put("productid", it.next().get("productid"));
				    	 temp.put("product_desc", it.next().get("product_desc"));
				    	 temp.put("product_price", it.next().get("product_price"));
				    	 temp.put("category_name", it.next().get("category_name"));
				    	 temp.put("brand_name", it.next().get("brand_name"));
				    	 temp.put("color_name", it.next().get("color_name"));
				    	 ans.add(temp);
				    	 //System.out.println(ans);
				    	 
				    	 //System.out.println(it.next());
				    	 String keyValuePairs = it.next().get("productid");  
				    	 //System.out.println(keyValuePairs);
				    	
				    	 //System.out.println(list.get(it.next()));
				    	 HashMap<String, String> h=(HashMap<String, String>)it.next();
				        //System.out.println("hey"+h.get("friend"));
				        //System.out.println(it.next().get("productid"));
				        //System.out.println(it.next().get("reg_name"));
				        
				     }*/
					if(restResponse.getStatus()==200)
					{
						
						request.setAttribute("listans", list);
						request.setAttribute("listname", name);
						request.setAttribute("listreg", reg);
						
						RequestDispatcher rd=request.getRequestDispatcher("SharedReg.jsp");
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
