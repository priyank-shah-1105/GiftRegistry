package com.miroservice.MicroGetUserReg;

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

@Path("/MicroGetUser")
public class MicroGetUserReg 
{
	final static Logger logger = Logger.getLogger(MicroGetUserReg.class);
	
	@Path("/getUserReg")
	@POST
	//@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
	public Response getUserReg(String username1)
	{
		String json=null;
		ArrayList<HashMap<String,String>> out= new ArrayList<HashMap<String,String>>();
		
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
			
			//System.out.print("In micro service username is"+username1);
			Statement st=conn.createStatement();
			
			
			
			
			String sql="select * from registry r, registryitems rs,product p,brand b,category c, color co  where rs.registryid=r.registryid and rs.product_id=p.product_id and c.category_id=p.product_category and b.brand_id=p.product_brand and co.color_id=p.product_color and r.name='"+username1+"'";
			//String sql="select p.product_id,p.product_name,p.product_desc,p.product_price,b.brand_name,c.color_name,cat.category_name from giftregistry.registry r, giftregistry.registryitems ri, giftregistry.product p,giftregistry.category cat,giftregistry.brand b, giftregistry.color c where r.name='"+username1+"' and  p.product_id=ri.product_id and ri.registryid=r.registryid and p.product_category=cat.category_id and p.product_brand=b.brand_id and p.product_color=c.color_id;";
			//System.out.println(sql);
			ResultSet rs=st.executeQuery(sql);
			while(rs.next())
			{
				HashMap<String,String>hm= new HashMap<String,String>();
				String reg_name=rs.getString("reg_name");
				String registryid=rs.getString("registryid");
				
				String product_id=rs.getString("product_id");
				String productname=rs.getString("product_name");
				String prod_desc=rs.getString("product_desc");
				String pp=rs.getString("product_price");
				String brand_name=rs.getString("brand_name");
				String category_name=rs.getString("category_name");
				String color_name=rs.getString("color_name");
				
				/*String brandsql="select * from brand where brand_id="+brandid;
				//System.out.println(brandsql);
				ResultSet rs1=st.executeQuery(brandsql)	;
				String brand_name=null;
				//System.out.println(rs1);
				while(rs1.next()) {
					brand_name=rs1.getString("brand_name");
					//System.out.println(brand_name);
				}
				////System.out.println(brand_name);
				String categorysql="select * from category where category_id="+categoryid;
				//System.out.println(categorysql); 
				rs1=st.executeQuery(categorysql)	;
				//System.out.println(rs1);
				 String category_name=null;
				while(rs1.next()) {
					
					category_name=rs1.getString("category_name");
					//System.out.println(category_name);
				}
				
				
				
				String colorsql="select * from color where color_id="+cn;
				 rs1=st.executeQuery(colorsql)	;
				String color_name=null;
				while(rs1.next()) {
					color_name=rs1.getString("color_name");
					
				}*/
				//System.out.println(category_name);
				//System.out.println(brand_name);
				//System.out.println(color_name);
					
				hm.put("reg_name",reg_name);
				hm.put("registryid",registryid);
				hm.put("product_id",product_id);
				hm.put("product_name", productname);
				hm.put("product_desc", prod_desc);
				hm.put("product_price", pp);
				hm.put("product_brand", brand_name);
				hm.put("product_category", category_name);
				hm.put("product_color", color_name);
				out.add(hm);
			}
			json= new Gson().toJson(out);
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
		logger.debug("Getting User Registry");
		
		return Response.ok(json).build();
	}

}
