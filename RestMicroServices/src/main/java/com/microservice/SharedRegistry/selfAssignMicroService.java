package com.microservice.SharedRegistry;

import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

@Path("/selfassignMicroService")
public class selfAssignMicroService {
	final static Logger logger = Logger.getLogger(selfAssignMicroService.class);
	
	@Path("/selfAssign")
	@POST
	@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
	public Response insertProductDetails(MultivaluedMap<String, String> formParam) {
		//System.out.println("Enter");
		logger.debug("Assign Items to self in Micro");
		
		boolean response = false;
		Statement st;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/giftregistry?autoReconnect=true&useSSL=false","root","root");
			st=con.createStatement();
			//System.out.println("before");
			
			String productid=formParam.getFirst("productid");
			String friend=formParam.getFirst("friend");
			String reg_name=formParam.getFirst("reg_name");
			String username=formParam.getFirst("user");
			
			String check="select registryid from `giftregistry`.`registry` where name='"+friend+"'and reg_name='"+reg_name+"'";
			//System.out.println(check);
ResultSet rs=st.executeQuery(check);
int reg_id=0;
			if(rs.next()) {
			reg_id=Integer.parseInt(rs.getString("registryid"));
			}
			
			//System.out.println(reg_id);
			if(reg_id!=0) {
			String sql="update  `giftregistry`.`registryitems` set assignTo='"+username+"' where registryid="+reg_id+" and product_id="+productid;
			
			//System.out.println(sql);
			//	(`name`, `email_id`, `password`) VALUES ('"+formParam.getFirst("username")+"', '"+formParam.getFirst("email")+"', '"+formParam.getFirst("pass")+"')";
				//System.out.println(sql);
				int i=st.executeUpdate(sql);
				////System.out.println(formParam.getFirst("shared")+"microservice checkbox value");
				
				// sql="update  `giftregistry`.`registry` set shared="+formParam.getFirst("shared")+" where name='"+formParam.getFirst("name")+"'";
				//	(`name`, `email_id`, `password`) VALUES ('"+formParam.getFirst("username")+"', '"+formParam.getFirst("email")+"', '"+formParam.getFirst("pass")+"')";
					////System.out.println(sql +"update query");
					//int j=st.executeUpdate(sql);
					if(i>0 ) {
						response = true;
							
					}
					else {
				//	//System.out.println("user doesn't exist");
					response = false;
					}
		
		}else {
			//	//System.out.println("user doesn't exist");
			response = false;
			}}
		catch(Exception e) {
			e.printStackTrace();
			//System.out.println("here i am");
		}
		//System.out.println("hadihas");
		logger.debug("Item successfully assign to User");
		
		return Response.ok().entity(String.valueOf(response)).build();

	}
		}

