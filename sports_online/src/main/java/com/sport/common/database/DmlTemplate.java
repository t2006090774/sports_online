package com.sport.common.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * @author a_kai
 */
@Repository
public class DmlTemplate {  
		//获取链接
	    private static Connection conn = null;  
	    
	    @Autowired
	    private DatabaseLink databaseLink; //= new DatabaseLink();
	    
	    /**
	     * 插入数据
	     */
		public int insert(String sql) {
	        ResultSet results = null;
	        int num = 0;
	        PreparedStatement pstmt;
	        // DatabaseLink databaseLink = new DatabaseLink();
	        try {
	        	conn = databaseLink.getConn();
	            pstmt = (PreparedStatement) conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	            pstmt.executeUpdate();
	            results = pstmt.getGeneratedKeys();
	            if(results.next())
	            {
	                num = results.getInt(1);
	            }
	            pstmt.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return num;
	    }
		
		/**
	     * 插入数据
	     */
		public int truncate(String sql) {
	        ResultSet results = null;
	        int num = 0;
	        PreparedStatement pstmt;
	        // DatabaseLink databaseLink = new DatabaseLink();
	        try {
	        	conn = databaseLink.getConn();
	            pstmt = (PreparedStatement) conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	            pstmt.executeUpdate();
	            results = pstmt.getGeneratedKeys();
	            if(results.next())
	            {
	                num = results.getInt(1);
	            }
	            pstmt.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return num;
	    }
		
		public List<TreeMap<String,String>> getListMap(String sql,Map<Integer,String> paramMap,String[] sArray) {
			System.out.println("-sql:"+sql);
        	//DatabaseLink databaseLink = new DatabaseLink();
        	List<TreeMap<String,String>> lists = new ArrayList<TreeMap<String,String>>();
        	TreeMap<String, String> map = null;
	        PreparedStatement pstmt;
	        try {
				conn = databaseLink.getConn();
	            pstmt = (PreparedStatement) conn.prepareStatement(sql);
	            //设置参数
	            for (Map.Entry<Integer, String> entry : paramMap.entrySet()) {  
	            	pstmt.setString(entry.getKey(), entry.getValue()); 
	            }
	            ResultSet rs = pstmt.executeQuery();
	            int col = rs.getMetaData().getColumnCount();
	            while (rs.next()) {
	            	map = new TreeMap<String,String>();
	                for (int i = 1; i <= col; i++) {
	                	for(String s:sArray) {
	                		if(!"".equals(s)) {
	                			map.put(s, rs.getString(s));
	                		}else {
	                			//空的字段不放入
	                		}
	                	}
	                }
	                lists.add(map);
	            }
	            rs.close();
	            pstmt.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			return lists;
	    }
		
		public List<TreeMap<String, String>> getListMaps(String sql,Map<Integer, String> paramMap,String[] sArray,String[] cArray) {
			System.out.println("*************sql:"+sql);
        	DatabaseLink databaseLink = new DatabaseLink();
        	List<TreeMap<String, String>> list = null;
	        PreparedStatement pstmt;
	        try {
				conn = databaseLink.getConn();
	            pstmt = (PreparedStatement) conn.prepareStatement(sql);
	            //设置参数
	            for (Map.Entry<Integer, String> entry : paramMap.entrySet()) {  
	            	pstmt.setString(entry.getKey(), entry.getValue()); 
	              
	            }
	            ResultSet rs = pstmt.executeQuery();
	            list = new ArrayList<TreeMap<String,String>>();
	            //字段数
	            // int col = rs.getMetaData().getColumnCount();
	            TreeMap<String, String> map = null;
	            while (rs.next()) {
	            	map = new TreeMap<String,String>();
	            	for(int i = 0;i <sArray.length;i++) {
            			map.put(cArray[i], rs.getString(sArray[i]));
	            	}
	            	list.add(map);
	            }
	            rs.close();
	            pstmt.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			return list;
	    }
		
		/**
		 * 获取logo
		 * @return 
		 */
		public Map<String, String> getLogoMaps(String sql,Map<Integer, String> paramMap) {
			System.out.println("*************sql:"+sql);
        	DatabaseLink databaseLink = new DatabaseLink();
        	 Map<String, String> map = new HashMap<String,String>();
	        PreparedStatement pstmt;
	        try {
				conn = databaseLink.getConn();
	            pstmt = (PreparedStatement) conn.prepareStatement(sql);
	            //设置参数
	            for (Map.Entry<Integer, String> entry : paramMap.entrySet()) {  
	            	pstmt.setString(entry.getKey(), entry.getValue()); 
	            }
	            ResultSet rs = pstmt.executeQuery();
	            //字段数
	            // int col = rs.getMetaData().getColumnCount();
	           
	            while (rs.next()) {
        			map.put(rs.getString("keyName"), rs.getString("valueName"));
	            }
	            rs.close();
	            pstmt.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			return map;
	    }
		
		public List<String> getArrayList(String sql,HashMap<Integer,String> paramMap) {
			System.out.println("-sql:"+sql);
        	DatabaseLink databaseLink = new DatabaseLink();
        	List<String> list = new ArrayList<String>();
	        PreparedStatement pstmt;
	        try {
				conn = databaseLink.getConn();
	            pstmt = (PreparedStatement) conn.prepareStatement(sql);
	            //设置参数
	            for (Map.Entry<Integer, String> entry : paramMap.entrySet()) {  
	            	pstmt.setString(entry.getKey(), entry.getValue()); 
	            }
	            ResultSet rs = pstmt.executeQuery();
	            int col = rs.getMetaData().getColumnCount();
	            while (rs.next()) {
	                for (int i = 1; i <= col; i++) {
	                	list.add(rs.getString(i));
	                }
	            }
	            rs.close();
	            pstmt.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			return list;
	    }

	
	}  