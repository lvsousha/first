package com.stone.test.database;

import java.sql.*;

public class Mysql {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/test";
		String name = "root";
		String password = "root"; 
		String sql = "select * from user";
		try{
			Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, name, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs != null)
            	rs.close();
            if(conn != null)
            	conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
