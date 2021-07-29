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

import com.crud.app.model.Hanyo;
import com.crud.app.util.DButil;

public class HanyoDAOImpl implements HanyoDAO {

	Connection connection = null;

	public HanyoDAOImpl() throws FileNotFoundException, IOException, SQLException {

		try {
			connection = DButil.getConnection();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
			System.out.println("unconnection");
		}
	}

	//汎用コードが重複しているか確認。
	@Override
	public int findCount(Hanyo hanyo)throws SQLSyntaxErrorException,SQLException {
		try {
			String sql = "SELECT COUNT(*) FROM M_HANYO MH WHERE MH.HANYO_CODE = ? AND MH.VALUE_CODE = ? ;";

			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, hanyo.getHanyoCode());
			pst.setString(2, hanyo.getValueCode());
			ResultSet rs = pst.executeQuery();

			rs.next();
			return rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}

	}

	@Override
	public void insertHanyo(Hanyo hanyo)throws SQLSyntaxErrorException,SQLException{

		try {
			String sql = "INSERT INTO M_HANYO(HANYO_CODE,VALUE_CODE,VALUE_NAME) VALUES (?, ?, ?) ;";

			PreparedStatement pst = connection.prepareStatement(sql);
			//インデクス番号、値
			pst.setString(1, hanyo.getHanyoCode());
			pst.setString(2, hanyo.getValueCode());
			pst.setString(3, hanyo.getValueName());

			int res = pst.executeUpdate();
			if(res > 0) {
				System.out.println("入力完了");
			}
		}
		catch (SQLSyntaxErrorException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateHanyo(Hanyo hanyo)throws SQLSyntaxErrorException,SQLException {

		try {
			String sql = "UPDATE M_HANYO SET VALUE_NAME = ? WHERE HANYO_CODE = ? AND VALUE_CODE = ?;";

			PreparedStatement pst = connection.prepareStatement(sql);
			//インデクス番号、値
			pst.setString(1, hanyo.getValueName());
			pst.setString(2, hanyo.getHanyoCode());
			pst.setString(3, hanyo.getValueCode());

			int res = pst.executeUpdate();

			if (res > 0) {
				System.out.println("更新成功");
			}


		} catch (SQLSyntaxErrorException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteHanyo(Hanyo hanyo)throws SQLSyntaxErrorException,SQLException {

		try {

			String sql = "DELETE FROM M_HANYO WHERE HANYO_CODE = ? AND VALUE_CODE = ? ;";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, hanyo.getHanyoCode());
			pst.setString(2, hanyo.getValueCode());

			int res = pst.executeUpdate();

			if (res > 0) {
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
	public List<Hanyo> getAllHanyos()throws SQLSyntaxErrorException,SQLException {

		List<Hanyo> hanyos = new ArrayList<Hanyo>();

		try {

			String sql = "SELECT * FROM M_HANYO ";
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				Hanyo hanyo = new Hanyo();
				//インデクス番号、値
				hanyo.setHanyoCode(rs.getString(1));
				hanyo.setValueCode(rs.getString(2));
				hanyo.setValueName(rs.getString(3));

				hanyos.add(hanyo);
			}

		} catch (SQLSyntaxErrorException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return hanyos;
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
	public List<Hanyo> searchHanyos(Hanyo hanyo) {

		List<Hanyo> hanyos = new ArrayList<Hanyo>();

		try {
			String sql = "SELECT * FROM M_HANYO ";

			String hc = hanyo.getHanyoCode();
			String vc = hanyo.getValueCode();
			String vn = hanyo.getValueName();

			String[][] h = {{"HANYO_CODE ","VALUE_CODE ","VALUE_NAME "},{hc,vc,vn}};

			for(int i = 0 ; h[1].length > i ; i++) {
				if(h[1][i].isEmpty()){
					;
				}
				else {
					sql += decisionWhere(sql)+ h[0][i] + "= \"" + h[1][i] + "\"";
				}
			}

			sql += ";";

			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				//インデクス番号、値
				Hanyo hanyo1 = new Hanyo();
				hanyo1.setHanyoCode(rs.getString(1));
				hanyo1.setValueCode(rs.getString(2));
				hanyo1.setValueName(rs.getString(3));

				hanyos.add(hanyo1);
			}
			System.out.println("検索成功");

		} catch (SQLSyntaxErrorException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return hanyos;
	}

	@Override
	public Hanyo getHanyoByCode(String hanyo_code,String value_code)throws SQLSyntaxErrorException,SQLException {

		Hanyo hanyo = null;

		try {
				String sql = "SELECT * FROM M_HANYO WHERE HANYO_CODE = ? AND VALUE_CODE = ? ";

				PreparedStatement pst = connection.prepareStatement(sql);
				pst.setString(1, hanyo_code);
				pst.setString(2, value_code);
				ResultSet rs = pst.executeQuery();

				while (rs.next()) {
					hanyo = new Hanyo();
					hanyo.setHanyoCode(rs.getString(1));
					hanyo.setValueCode(rs.getString(2));
					hanyo.setValueName(rs.getString(3));
				}


		} catch (SQLSyntaxErrorException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return hanyo;
	}

}