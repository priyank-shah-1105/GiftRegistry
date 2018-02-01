package com.microservices.Register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.miroservice.MicroGetUserReg.MicroInsertRegName;

@Path("/insertusernamemicroservice")
public class InsertRegmicroService 
{
	final static Logger logger = Logger.getLogger(InsertRegmicroService.class);
	
	@Path("/insertusernamereg")
	@POST
	@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
	public Response insertDetails(MultivaluedMap<String, String> formParam)
	{
		boolean response = false;
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
			int token = (int )(Math.random() * 50 + 1);
			//System.out.println(token);
			String sql="INSERT INTO `giftregistry`.`userdetails` (`username`, `email_id`, `password`,`token`) VALUES ('"+formParam.getFirst("username")+"', '"+formParam.getFirst("email")+"', '"+formParam.getFirst("pass")+"',"+token+")";
			PreparedStatement pst=conn.prepareStatement(sql);
			//System.out.println(sql);
			/*ResultSet rs=st.executeQuery("INSERT INTO `giftregistry`.`userdetails` (`username`, `email_id`, `password`) VALUES ('"+formParam.getFirst(username)+"', '', 'admin');\r\n" + 
					"");*/
			pst.execute();
			response=true;
			conn.close();
		}
		catch(Exception e)
		{
			//System.out.println(e+" "+"duffer check your sql");						
			//response=true;
		}

		logger.debug("User successfully Registered");
		
		return Response.ok().entity(String.valueOf(response)).build();
		
	}

}
