package com.example.getProduct;

import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.sql.PreparedStatement;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

@Path("/addAndDeleteProductDetailsMicroService")
public class AddAndDeleteProductDetailsMicroService {
	final static Logger logger = Logger.getLogger(AddAndDeleteProductDetailsMicroService.class);

	@Path("/insertProductDetails")
	@POST
	@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
	public Response insertProductDetails(MultivaluedMap<String, String> formParam) {
		boolean response = false;
		//System.out.println("here you go ");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/giftregistry?autoReconnect=true&useSSL=false","root","root");
			Statement st;
			/*bean.setProductName("laptop");
			bean.setProductDesc("i5");
			bean.setPrice(700);
			bean.setBrand("Apple");
			bean.setColor("spacegrey");
			bean.setCategory("electronics");*/
			//System.out.println("in");
				st = con.createStatement();
				
				String brand1="select brand_id from brand where brand_name='"+formParam.getFirst("brand")+"'";
				//System.out.println(brand1);
				ResultSet rs=st.executeQuery(brand1);
				String brand=null;
				while(rs.next()) {
					brand=rs.getString(1);
						
				}
				String category1="select category_id from category where category_name='"+formParam.getFirst("category")+"'";
				//System.out.println(category1);		
				rs=st.executeQuery(category1);
				String category=null;
				while(rs.next()) {
							
		
				 category=rs.getString(1);
				}		
				String color1="select color_id from color where color_name='"+formParam.getFirst("color")+"'";
			//System.out.println(color1);
				rs=st.executeQuery(color1);
				String color=null;
				while(rs.next()) {
					
					
					color=rs.getString(1);
				}
				
				
				
				String insertQuery = "insert into product (product_name,product_desc,product_price,product_brand,product_color,product_category)"
						
						+"values ('"+ formParam.getFirst("productname") + "', '" + formParam.getFirst("productdesc") + "', '" + formParam.getFirst("price") +"', '" + Integer.parseInt(brand) + "', '" + Integer.parseInt(color) + "', '" + Integer.parseInt(category) + "')"; 
				st.executeUpdate(insertQuery);
				//System.out.println("in_out");
				response = true;
				con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		logger.debug("Product Added SuccessFully");
		
		return Response.ok().entity(String.valueOf(response)).build();
	}
		/*Bean bean = new Bean();
		insertProductDetails(bean);
		ArrayList<String> idOfProduct = new ArrayList<String>();
		bean.setProductId("1");
		bean.setProductId("2");
		//idOfProduct.add("1");
		idOfProduct.add("2");
		deleteProducts(idOfProduct,bean);
		insertBrands(bean);*/

	@Path("/insertBrand")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response insertBrand(MultivaluedMap<String, String> formParam) {
		//System.out.println("Enter");
		boolean response = false;
		Statement st;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/giftregistry?autoReconnect=true&useSSL=false","root","root");
			st=con.createStatement();
			//System.out.println("before");
			
			String sql="select * from brand where brand_name='"+formParam.getFirst("brand")+"'";
			ResultSet rs=st.executeQuery(sql);
			
			if(rs.next()) {
				response=false;
			}else {
			String insertQuery = "insert into brand(brand_name) values('"+formParam.getFirst("brand") + "')";
			//System.out.println(insertQuery);
			st.executeUpdate(insertQuery);
			response = true;}
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			//System.out.println("here i am");
		}
		//System.out.println("hadihas");
		logger.debug("Brand Added SuccessFully");
		
		return Response.ok().entity(String.valueOf(response)).build();
	}
	@Path("/insertCategory")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response insertCategory(MultivaluedMap<String, String> formParam) {
		boolean response = false;
		Statement st;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/giftregistry?autoReconnect=true&useSSL=false","root","root");
			st=con.createStatement();
			String sql="select * from category where category_name='"+formParam.getFirst("category")+"'";
			ResultSet rs=st.executeQuery(sql);
			
			if(rs.next()) {
				response=false;
			}else {
			String insertQuery = "insert into category(category_name) values('"+formParam.getFirst("category") + "')";
			st.executeUpdate(insertQuery);
			response = true;}
			con.close();
			response = true;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		logger.debug("Category Added SuccessFully");
		
		return Response.ok().entity(String.valueOf(response)).build();
	}
	
	@Path("/insertColor")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response insertColor(MultivaluedMap<String, String> formParam) {
		boolean response = false;
		Statement st;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/giftregistry?autoReconnect=true&useSSL=false","root","root");
			st=con.createStatement();
			String sql="select * from color where color_name='"+formParam.getFirst("color")+"'";
			ResultSet rs=st.executeQuery(sql);
			
			if(rs.next()) {
				response=false;
			}else {
			String insertQuery = "insert into color(color_name) values('"+formParam.getFirst("color") + "')";
			st.executeUpdate(insertQuery);
			response = true;}
			con.close();
			//response = true;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		logger.debug("Color Added SuccessFully");
		
		return Response.ok().entity(String.valueOf(response)).build();
	}
	
	@Path("/deleteProduct")
	@POST
	@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
	public Response deleteProduct(MultivaluedMap<String, String> formParam)
	{
		boolean response = false;
		int reg_id=0;
		
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
			String username1=formParam.getFirst("username");
			int product_id=Integer.parseInt(formParam.getFirst("product_id"));
			//System.out.println(product_id);
			//check if username already there or not			
			/*Statement st0= conn.createStatement();
			String sql0="SELECT registryid from registry where name='"+username1+"'";
			ResultSet rs0=st0.executeQuery(sql0);
			//int reg_id=0;
			if(rs0.next()==true)
			{
				reg_id=rs0.getInt("registryid");
			}
			//now we have regid and the product id also
*/			String sql1="delete from giftregistry.product where product_id="+product_id+"";
//System.out.println(sql1);
			PreparedStatement pst=conn.prepareStatement(sql1);
			pst.execute();
			//item deleted from the registry
			response=true;
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
		logger.debug("Product Deleted SuccessFully");
		
		return Response.ok(String.valueOf(response)).build();
	}
		
		
	
	
}

