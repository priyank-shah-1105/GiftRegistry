package com.microservice.AddFriend;

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

@Path("/addfriend")
public class AddFriend 
{
	final static Logger logger = Logger.getLogger(AddFriend.class);

	@Path("/addfriend")
	@POST
	
	public Response addfriend(MultivaluedMap<String, String> formParam)
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
			String friend=formParam.getFirst("friend");
			//check if username already there or not
			
			Statement st1= conn.createStatement();
			String sql0="SELECT * from sharedregistry where name='"+username1+"' and friends='"+friend+"'";
			//System.out.println(sql0);
			ResultSet rs1=st1.executeQuery(sql0);
			
			//System.out.println("add friend micro");
			
			if(rs1.next() || username1.equals(friend))
			{
				response=false;
			}
			else {

				String sql="Insert into giftregistry.sharedregistry  values('"+username1+"','"+friend+"');";
				PreparedStatement pst=conn.prepareStatement(sql);
				pst.execute();
				//System.out.println("added friend pefectly");
				//reg_id=rs1.getInt("registryid");
				response=true;
				
			}
			
			/*		if(rs1.next()==false)
			{
				
				String sql="Insert into giftregistry.registry (`name`) values('"+formParam.getFirst("username")+"');";
				PreparedStatement pst=conn.prepareStatement(sql);
				pst.execute();
				//System.out.println("Creating for the first time");
				//reg_id=rs1.getInt("registryid");
				response=true;
			}
			else
			{
				//System.out.println("The registry for user already exist");
				Statement st=conn.createStatement();
				String sql1="SELECT registryid FROM giftregistry.registry where name='"+username1+"'";
				ResultSet rs=st.executeQuery(sql1);
				if(rs.next())
				{
					reg_id=rs.getInt("registryid");
				}
				//System.out.println(reg_id+" "+"is the reg_id"+" "+product_id);
				//checking here if the products exists or not
				Statement st4=conn.createStatement();
				String sql4="SELECT * FROM giftregistry.registryitems where registryid='"+reg_id+"' and product_id='"+product_id+"'";
				ResultSet rs4=st4.executeQuery(sql4);
				if(rs4.next()==false)
				{
					String sql2="Insert into giftregistry.registryitems (`registryid`,`product_id`,`shared`) values('"+reg_id+"','"+product_id+"','1');";
					PreparedStatement pst1=conn.prepareStatement(sql2);
					pst1.execute();
					response=true;
				}
				else
				response=false;
			}	
					//successfull insert, now select the registry id the query
					
			*/
			
		}
		catch(Exception e)
		{
			response=false;
			//System.out.println(e);
		}
		logger.debug("Friends has been added");
		
		return Response.ok(String.valueOf(response)).build();
	}
}
