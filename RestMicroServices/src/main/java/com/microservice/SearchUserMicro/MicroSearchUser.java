package com.microservice.SearchUserMicro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.microservices.addRegistryValues.AddRegistryValuesMicro;

@Path("/searachUsermicro")
public class MicroSearchUser 
{
	final static Logger logger = Logger.getLogger(MicroSearchUser.class);

	@Path("/searchUser")
	@POST
	//@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
	public Response getSearchUser(String query)
	{
		String json=null;
		ArrayList<HashMap<String,String>> out= new ArrayList<HashMap<String,String>>();
		//query="priyank shah";
		String[] array=query.split(" ");
		try
		{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/giftregistry";
			String username = "root";
			String password1 = "root";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password1);
			//response=true;			
			//System.out.println("connection established");
			Statement st=conn.createStatement();
			String sql="select * from userdetails where ";
			for(int i=0;i<array.length;i++) {
				sql+=" first_name='"+array[i]+"' or last_name='"+array[i]+"'";
				if(i!=array.length-1) {
					sql+="or";
				}
			}
			//System.out.println(sql);
			//String sql="SELECT product_id,product_name,product_desc,product_price,brand_name,category_name,color_name FROM giftregistry.product,giftregistry.category,giftregistry.brand,giftregistry.color where (product_category=category_id and product_brand=brand_id and product_color=color_id);";
			ResultSet rs=st.executeQuery(sql);
			while(rs.next())
			{
				HashMap<String,String>hm= new HashMap<String,String>();
				String username1=rs.getString("username");
				String fname=rs.getString("first_name");
				String lname=rs.getString("last_name");
				String email_id=rs.getString("email_id");
				hm.put("username",username1);
				hm.put("first_name", fname);
				hm.put("last_name", lname);
				hm.put("email", email_id);
				out.add(hm);
			}
			json= new Gson().toJson(out);
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
		return Response.ok(json).build();
	}

}
