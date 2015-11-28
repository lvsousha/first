package com.stone.test.database;

import java.sql.*;

public class Sqlserver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://localhost:1433;databaseName=stone";
		String name = "stone";
		String password = "stone"; 
		String sql = "select * from stone";
		try{
			Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, name, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println(rs.next());
            if(rs != null)
            	rs.close();
            if(conn != null)
            	conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
