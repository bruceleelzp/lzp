package com.lzp.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
	//提供一个数据源
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	//返回一个连接
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	/*public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}*/

}
