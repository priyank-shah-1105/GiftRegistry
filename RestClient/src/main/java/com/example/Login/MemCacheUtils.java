package com.example.Login;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;


import net.spy.memcached.MemcachedClient;

public class MemCacheUtils {

    //private static final Logger logger = LogManager.getLogger(MemCacheUtil.class);
    //private static MemCachedClient client = null;
    private  MemcachedClient mcc = null;
    public MemCacheUtils(){
    	   //System.out.println("in constr");

   		try {
   			mcc = new MemcachedClient(new
   			  InetSocketAddress("127.0.0.1", 11211));
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
    	   }
    public  void putInCache(String key, String value) {
    	

	      //System.out.println("Connection to server sucessfully");
	      
	      
	      //not set data into memcached server
	      ////System.out.println("set status:"+mcc.add("tutorialspoint", 900, "memcached"));
	      
	      //Get value from cache
	      ////System.out.println("Get from Cache:"+mcc.get("tutorialspoint"));
    	
    	
        //System.out.println("cache put");
        mcc.add(key, 900, value);
        ////System.out.println(mcc.get(key));
        //return client.set(key, value);
       
    }
    public String getFromCache(String key) {
    	String value = (String) mcc.get(key);
    	if(value==null) {
    	}
    	else {
    		
    	}
    	return value;
    }
    }