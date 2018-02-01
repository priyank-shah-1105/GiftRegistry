package com.microservice.SearchItemMicro;

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
import com.microservice.SearchUserMicro.MicroSearchSharedUser;

@Path("/searchItemmicro")
public class MicroSearchItem 
{
	final static Logger logger = Logger.getLogger(MicroSearchItem.class);

	@Path("/searchItem")
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
			String sql="SELECT product_id,product_name,product_desc,product_price,brand_name,category_name,color_name FROM giftregistry.product,giftregistry.category,giftregistry.brand,giftregistry.color where";
			for(int i=0;i<array.length;i++) {
				sql+="(product_name LIKE '%"+array[i]+"%' or product_desc LIKE '%"+array[i]+"%') and (product_category=category_id and product_brand=brand_id and product_color=color_id)";
				if(i!=array.length-1) {
					sql+="or";
				}
			}
			//String sql="SELECT product_id,product_name,product_desc,product_price,brand_name,category_name,color_name FROM giftregistry.product,giftregistry.category,giftregistry.brand,giftregistry.color where (product_category=category_id and product_brand=brand_id and product_color=color_id);";
			//System.out.println(sql);
			ResultSet rs=st.executeQuery(sql);
			while(rs.next())
			{
				HashMap<String,String>hm= new HashMap<String,String>();
				String product_id=rs.getString("product_id");
				String productname=rs.getString("product_name");
				String prod_desc=rs.getString("product_desc");
				String pp=rs.getString("product_price");
				String brandname=rs.getString("brand_name");
				String categoryname=rs.getString("category_name");
				String cn=rs.getString("color_name");
				hm.put("product_id",product_id);
				hm.put("product_name", productname);
				hm.put("product_desc", prod_desc);
				hm.put("product_price", pp);
				hm.put("brand_name", brandname);
				hm.put("category_name", categoryname);
				hm.put("color_name", cn);
				out.add(hm);
			}
			json= new Gson().toJson(out);
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
		logger.debug("Searched Items is fetched");
		
		return Response.ok(json).build();
	}

}
