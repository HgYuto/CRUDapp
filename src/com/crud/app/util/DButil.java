package com.crud.app.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {

	private static Connection connection = null;
//	private static PreparedStatement pst = null;
//	private static ResultSet rs = null;

	//接続共有処理
	public static Connection getConnection() throws ClassNotFoundException, SQLException, InstantiationException,
			IllegalAccessException, FileNotFoundException, IOException {

/*		if (connection != null) {
			System.out.println("connection(接続しているよ)");
			return connection;
		}
		else {
*/			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				// DBのコネクションとテーブル名、ユーザー名 、パスワード
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb", "root", "rootroot");
				System.out.println("connection(今、接続したよ)");
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return connection;
//		}
	}
/*	public static void rsConnection() {
		try {
			//切断
			if ( rs != null) {
				connection.close();
				System.out.println("ResultSet disconnection");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void pstConnection() {
		try {
			//ステートメント切断
			if ( pst != null) {
				connection.close();
				System.out.println("PreparedStatement disconnection");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
*/	//切断共有処理
	public static void closeConnection() {
		try {
			//DB切断
			if (connection != null) {
				connection.close();
				System.out.println("disconnection(接続切れているよ)");
			}
		}
		catch (SQLException e) {
				e.printStackTrace();
		}
	}
}