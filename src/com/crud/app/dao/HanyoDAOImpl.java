package com.crud.app.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crud.app.model.Hanyo;
import com.crud.app.util.DButil;

public class HanyoDAOImpl implements HanyoDAO {

	Connection connection = null;

	public HanyoDAOImpl() throws FileNotFoundException, IOException {

		try {
			connection = DButil.getConnection();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException
				| IOException e) {
			e.printStackTrace();
		}

		System.out.println("connection");
	}

	@Override
	public void insertHanyo(Hanyo hanyo) {

		try {

			String sql = "INSERT INTO M_HANYO(HANYO_CODE,VALUE_CODE,VALUE_NAME) VALUES (?, ?, ?) ;";

			PreparedStatement pst = connection.prepareStatement(sql);
			//インデクス番号、値
			pst.setString(1, hanyo.getHanyoCode());
			pst.setString(2, hanyo.getValueCode());
			pst.setString(3, hanyo.getValueName());

			int res = pst.executeUpdate();

			if (res > 0) {
				System.out.println("入力完了");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateHanyo(Hanyo hanyo) {

		try {
			String sql = "UPDATE M_HANYO SET VALUE_CODE = ? ,VALUE_NAME = ? WHERE HANYO_CODE = ? ;";

			PreparedStatement pst = connection.prepareStatement(sql);
			//インデクス番号、値
			pst.setString(1,hanyo.getValueCode());
			pst.setString(2, hanyo.getValueName());
			pst.setString(3, hanyo.getHanyoCode());


			int res = pst.executeUpdate();

			if (res > 0) {
				System.out.println("更新成功");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteHanyo(Hanyo hanyo) {

		try {

			String sql = "DELETE FROM M_HANYO WHERE HANYO_CODE = ? ;";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, hanyo.getHanyoCode());

			int res = pst.executeUpdate();

			if (res > 0) {
				System.out.println("削除完了");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Hanyo> getAllHanyos() {

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

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return hanyos;
	}

	@Override
	public String decisionWhere(String sql) {
		if(sql.contains("where")) {
			return sql + "AND ";
		}else {
			return sql + "Where ";
		}
	}

	@Override
	public List<Hanyo> searchHanyos(Hanyo likeHanyo) {

		List<Hanyo> hanyos = new ArrayList<Hanyo>();

		try {
			String sql = "SELECT * FROM M_HANYO ";
			String hc = likeHanyo.getHanyoCode();
			String vc = likeHanyo.getValueCode();
			String vm = likeHanyo.getValueName();
			String lh[][] = {{"HANYO_CODE ","VALUE_CODE ","VALUE_NAME "},{hc,vc,vm}};
			for(int i=0;lh[1][i].length() > 0;i++) {
				if(lh[1][i].isEmpty()){
					;
				}
				else {
					sql += decisionWhere(sql)+lh[0][i]+ "=" +lh[1][i];
				}
			}
			sql +=";";
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Hanyo hanyo = new Hanyo();
				//インデクス番号、値
				hanyo.setHanyoCode(rs.getString(1));
				hanyo.setValueCode(rs.getString(2));
				hanyo.setValueName(rs.getString(3));

				hanyos.add(hanyo);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return hanyos;
	}


	@Override
	public Hanyo getHanyoByCode(String hanyo_code) {

		Hanyo hanyo = null;

		try {

			String sql = "SELECT * FROM M_HANYO WHERE HANYO_CODE = ? ";

			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, hanyo_code);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				hanyo = new Hanyo();
				hanyo.setHanyoCode(rs.getString(1));
				hanyo.setValueCode(rs.getString(2));
				hanyo.setValueName(rs.getString(3));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return hanyo;
	}

}