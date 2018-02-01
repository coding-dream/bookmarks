package com.less.bookmarks.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.apache.commons.dbutils.QueryRunner;

public class JdbcUtils {

	public static QueryRunner getQueryRunner(String dbName) {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			// dataSource.setUser("root");
			// dataSource.setPassword("root");
			// dataSource.setJdbcUrl( "jdbc:mysql://localhost:3306/" + dbName);
			// dataSource.setDriverClass( "com.mysql.jdbc.Driver");
			dataSource.setDriverClass("org.sqlite.JDBC");
			dataSource.setJdbcUrl("jdbc:sqlite:" + dbName);
			dataSource.setInitialPoolSize(5);
			dataSource.setMinPoolSize(3);
			dataSource.setMaxPoolSize(10);
			dataSource.setAcquireIncrement(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new QueryRunner(dataSource);
	}
}
