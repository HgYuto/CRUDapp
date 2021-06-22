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
	public int findCount(Hanyo hanyo) {
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
			String sql = "UPDATE M_HANYO ";
			for(int i = 0 ; 3 > i ; i++) {
				if(hanyoArraySet(hanyo,1,i).isEmpty()){
					;
				}else {
					sql += decisionSet(sql) + hanyoArraySet(hanyo,0,i) + "= \"" + hanyoArraySet(hanyo,1,i) + "\"";
				}
			}
			for(int i = 0 ; 3 > i ; i++) {
				sql = hanyoWhere(sql,hanyo,2,i) ;
			}

			sql += ";";

			PreparedStatement pst = connection.prepareStatement(sql);
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
	public String decisionSet(String sql) {
		if(sql.contains("SET")) {
			return ",";
		}else {
			return "SET ";
		}
	}

	@Override
	public List<Hanyo> searchHanyos(Hanyo hanyo) {

		List<Hanyo> hanyos = new ArrayList<Hanyo>();

		try {
			String sql = "SELECT * FROM M_HANYO ";
			for(int i = 0 ; 2 > i ; i++) {
				hanyoWhere(sql,hanyo,1,i);
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
	public Hanyo getHanyoByCode(String hanyo_code,String value_code) {

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

	@Override
	public String hanyoArraySet(Hanyo hanyo, int i, int j) {

		String hc = hanyo.getHanyoCode();
		String vc = hanyo.getValueCode();
		String vn = hanyo.getValueName();

		String oldhc = hanyo.getOldHanyoCode();
		String oldvc = hanyo.getOldValueCode();
		String oldvn = hanyo.getOldValueName();

		String[][] h = {{"HANYO_CODE ","VALUE_CODE ","VALUE_NAME "},{hc,vc,vn},{oldhc,oldvc,oldvn}};
		return h[i][j];
	}

	@Override
	public String hanyoWhere( String sql, Hanyo hanyo, int i, int j) {
		if(hanyoArraySet(hanyo,i,j).isEmpty()){
			;
		}else {
			sql += decisionWhere(sql) + hanyoArraySet( hanyo, 0, j) + "= \"" + hanyoArraySet( hanyo, i, j) + "\"";
		}

		return sql;
	}

}