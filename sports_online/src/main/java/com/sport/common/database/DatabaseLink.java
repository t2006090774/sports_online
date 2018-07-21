package com.sport.common.database;

import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Properties;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;

/**
 * @author a_kai
 */
@Repository
public class DatabaseLink {  
		Properties property = new Properties();
	    private Connection conn = null;  
	  
	    public DatabaseLink() {  
	        try {  
	        	InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("/jdbc.properties"); 
	        	property.load(is);
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }
	    
	    public Connection getConn() {
	    	try {  
	            Class.forName(property.getProperty("driver"));//指定连接类型  
	            conn = (Connection) DriverManager.getConnection(property.getProperty("url"), 
	            												property.getProperty("uname"), 
	            												property.getProperty("password"));//获取连接  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    	
			return conn;  
	    }
	}  