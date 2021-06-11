package com.crud.app.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

import com.crud.app.model.User;
import com.crud.app.util.DButil;

public class UserDAOImpl implements UserDAO {

	Connection connection = null;

	public UserDAOImpl() throws FileNotFoundException, IOException, SQLException {

		try {
			connection = DButil.getConnection();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
			System.out.println("unconnection");
		}
	}

	//社員コードが重複しているか確認。
	@Override
	public int findCount(User user) {
		try {
			String sql = "SELECT COUNT(*) FROM M_USER MU WHERE MU.SYAIN_CD = ? OR MU.USER_ID = ? ;";

			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, user.getSyainCode());
			pst.setString(2, user.getUserId());
			ResultSet rs = pst.executeQuery();

			rs.next();
			return rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}

	}

	@Override
	public void insertUser(User user) {

		try {

			String sql = "INSERT INTO M_USER(SYAIN_CD,USER_ID,PASSWORD,AUTHORITY) VALUES (?, ?, ?, ?) ;";

			PreparedStatement pst = connection.prepareStatement(sql);
			//インデクス番号、値
			pst.setString(1, user.getSyainCode());
			pst.setString(2, user.getUserId());
			pst.setString(3, user.getPassword());
			pst.setShort(4, user.getAuthority());

			if(findCount(user) > 0) {
				user.setErrResult("社員コードもしくは、ユーザIDが重複しています。再度見直してください。");
			}
			else if(findCount(user) < 0) {
				user.setErrResult("接続エラー：ネットワーク不良");
			}
			else {
				int res = pst.executeUpdate();
				if(res > 0) {
					user.setErrResult("");
					System.out.println("入力完了");
				}
			}
		}
		catch (SQLSyntaxErrorException e) {
			e.printStackTrace();
			user.setErrResult("構文エラー：データベースへの問い合わせに、不正な構文が検知されました。");
		}
		catch (SQLException e) {
			e.printStackTrace();
			user.setErrResult("接続エラー：ネットワーク不良");
		}
	}

	@Override
	public void updateUser(User user) {

		try {
			String sql = "UPDATE M_USER SET USER_ID = ? ,PASSWORD = ? ,AUTHORITY = ? WHERE SYAIN_CD = ? AND USER_ID = ? ;";

			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setString(1, user.getUserId());
			pst.setString(2, user.getPassword());
			pst.setShort(3, user.getAuthority());
			pst.setString(4, user.getSyainCode());
			pst.setString(5,user.getPreUserId());

			if(findCount(user) > 1) {
				user.setErrResult("社員コードもしくは、ユーザIDが重複しています。再度見直してください。");
			}
			else if(findCount(user) <= 0) {
				user.setErrResult("接続エラー：ネットワーク不良");
			}
			else {
				int res = pst.executeUpdate();
				if (res > 0) {
					user.setErrResult("");
					System.out.println("更新成功");
				}
			}

		} catch (SQLSyntaxErrorException e) {
			e.printStackTrace();
			user.setErrResult("構文エラー：データベースへの問い合わせに、不正な構文が検知されました。");
		}
		catch (SQLException e) {
			e.printStackTrace();
			user.setErrResult("接続エラー：ネットワーク不良");
		}
	}

	@Override
	public void deleteUser(User user) {

		try {

			String sql = "DELETE FROM M_USER WHERE SYAIN_CD = ? AND USER_ID =?;";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, user.getSyainCode());
			pst.setString(2, user.getUserId());

			int res = pst.executeUpdate();

			if (res > 0) {
				user.setErrResult("");
				System.out.println("削除完了");
			}

		} catch (SQLSyntaxErrorException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAllUsers() {

		List<User> users = new ArrayList<User>();

		try {
			//ユーザテーブル＋社員名
			String sql = "SELECT u.*,s.SYAIN_NAME FROM M_USER u LEFT OUTER JOIN M_SYAIN s ON u.SYAIN_CD = s.SYAIN_CD; ";
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				User user = new User();
				//インデクス番号、値
				//ユーザマスターのデータ
				user.setSyainCode(rs.getString(1));
				user.setUserId(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setAuthority(rs.getShort(4));
				//社員マスターの社員名のデータ
				user.setSyainName(rs.getString(5));

				users.add(user);

			}

		}  catch (SQLSyntaxErrorException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	@Override
	public String decisionWhere(String sql) {
		if(sql.contains("WHERE")) {
			return " AND ";
		}else {
			return " WHERE ";
		}
	}

	@Override
	public List<User> searchUsers(User user) {

		List<User> users = new ArrayList<User>();

		try {
			String sql = "SELECT u.*,s.SYAIN_NAME FROM M_USER u LEFT OUTER JOIN M_SYAIN s ON u.SYAIN_CD = s.SYAIN_CD ";

			String sc = user.getSyainCode();
			String id = user.getUserId();
			short ay = user.getAuthority();

			String[][] sArray = {{"u.SYAIN_CD ","USER_ID ","AUTHORITY "},{sc,id,String.valueOf(ay)}};
			for(int i = 0 ; sArray[1].length > i ; i++) {
				if(sArray[1][i].isEmpty()){
					;
				}else if(sArray[1][i]==sArray[1][2]){
					if(ay < 0) {
						;
					}else {
						sql += decisionWhere(sql)+ sArray[0][2] + "=" +sArray[1][2];
					}
				}
				else {
					sql += decisionWhere(sql)+ sArray[0][i] + "= \"" + sArray[1][i] + "\"";
				}
			}

			sql += ";";

			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				//インデクス番号、値
				User userCol = new User();
				userCol.setSyainCode(rs.getString(1));
				userCol.setUserId(rs.getString(2));
				userCol.setPassword(rs.getString(3));
				userCol.setAuthority(rs.getShort(4));
				userCol.setSyainName(rs.getString(5));

				users.add(userCol);
			}
				user.setErrResult("");
				System.out.println("検索成功");

		} catch (SQLSyntaxErrorException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	@Override
	public User getUserByCode(String syain_code,String user_id) {

		User user = null;

		try {
			String sql = "SELECT u.*,s.SYAIN_NAME FROM M_USER u LEFT OUTER JOIN M_SYAIN s ON u.SYAIN_CD = s.SYAIN_CD WHERE u.SYAIN_CD = ? AND USER_ID = ? ;";

			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, syain_code);
			pst.setString(2, user_id);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setSyainCode(rs.getString(1));
				user.setUserId(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setAuthority(rs.getShort(4));
				user.setSyainName(rs.getString(5));
			}

		} catch (SQLSyntaxErrorException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}


}