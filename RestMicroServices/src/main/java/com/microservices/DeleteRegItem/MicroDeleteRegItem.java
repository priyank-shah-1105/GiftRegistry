package com.microservices.DeleteRegItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.microservices.GetHomeValues.GetHomeValues;

@Path("/deleteRegItemMicro")
public class MicroDeleteRegItem 
{
	final static Logger logger = Logger.getLogger(MicroDeleteRegItem.class);

	@Path("/deleteRegItem")
	@POST
	@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
	public Response deleteRegItem(MultivaluedMap<String, String> formParam)
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
			String registryid=formParam.getFirst("registryid");

			//System.out.println(registryid +username1);
			
			int product_id=Integer.parseInt(formParam.getFirst("productid"));
			//System.out.println(product_id);
			//check if username already there or not			
			Statement st0= conn.createStatement();
			
			/*String sql0="SELECT registryid from registry where name='"+username1+"'";
			ResultSet rs0=st0.executeQuery(sql0);
			//int reg_id=0;
			if(rs0.next()==true)
			{
				reg_id=rs0.getInt("registryid");
			}
			*///now we have regid and the product id also
			String sql1="delete from giftregistry.registryitems where registryid='"+registryid+"'and product_id='"+product_id+"'";
			PreparedStatement pst=conn.prepareStatement(sql1);
			////System.out.println("micro"+pst.execute());
			pst.execute();
			//item deleted from the registry
			response=true;
			
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
		logger.debug("Item deleted from perticular registry");
		
		return Response.ok(String.valueOf(response)).build();
	}
}
