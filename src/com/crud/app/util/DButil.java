package com.crud.app.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {

	private static Connection connection = null;

	public static Connection getConnection() throws ClassNotFoundException, SQLException, InstantiationException,
			IllegalAccessException, FileNotFoundException, IOException {

		if (connection != null)
			return connection;
		else {
			try {
				//Properties prop = new Properties();

				Class.forName("com.mysql.jdbc.Driver");
				// DBのコネクションとテーブル名、ユーザー名 、パスワード
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb", "root", "rootroot");

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return connection;
		}
	}
}