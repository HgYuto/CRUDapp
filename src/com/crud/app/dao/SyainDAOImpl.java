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

import com.crud.app.model.Syain;
import com.crud.app.util.DButil;

public class SyainDAOImpl implements SyainDAO {

	Connection connection = null;

	public SyainDAOImpl() throws FileNotFoundException, IOException, SQLException {

		try {
			connection = DButil.getConnection();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
			System.out.println("unconnection");

		}
	}

	//社員コードが重複しているか確認。
	@Override
	public int findCount(Syain syain) {
		try {
			String sql = "SELECT COUNT(*) FROM M_SYAIN MS WHERE MS.SYAIN_CD = ? ;";

			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, syain.getSyainCode());
			ResultSet rs = pst.executeQuery();

			rs.next();
			return rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}

	}

	@Override
	public void insertSyain(Syain syain)throws SQLSyntaxErrorException,SQLException {

		try {

			String sql = "INSERT INTO M_SYAIN(SYAIN_CD,POSITION,SYAIN_NAME,MAILADDRESS,TEL) VALUES (?, ?, ?, ?, ?) ;";

			PreparedStatement pst = connection.prepareStatement(sql);
			//インデクス番号、値
			pst.setString(1, syain.getSyainCode());
			pst.setShort(2, syain.getPosition());
			pst.setString(3, syain.getSyainName());
			pst.setString(4, syain.getMailAddress());
			pst.setString(5, syain.getTel());

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
	public void updateSyain(Syain syain)throws SQLSyntaxErrorException,SQLException {

		try {
			String sql = "UPDATE M_SYAIN SET POSITION = ? ,SYAIN_NAME = ? ,MAILADDRESS = ?,TEL = ? WHERE SYAIN_CD = ?";

			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setShort(1, syain.getPosition());
			pst.setString(2, syain.getSyainName());
			pst.setString(3, syain.getMailAddress());
			pst.setString(4, syain.getTel());
			pst.setString(5, syain.getSyainCode());

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
	public void deleteSyain(Syain syain)throws SQLSyntaxErrorException,SQLException {

		try {

			String sql = "DELETE FROM M_SYAIN WHERE SYAIN_CD = ? ;";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, syain.getSyainCode());

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
	public List<Syain> getAllSyains()throws SQLSyntaxErrorException,SQLException {

		List<Syain> syains = new ArrayList<Syain>();

		try {

			String sql = "SELECT * FROM M_SYAIN ";
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				Syain syain = new Syain();
				//インデクス番号、値
				syain.setSyainCode(rs.getString(1));
				syain.setPosition(rs.getShort(2));
				syain.setSyainName(rs.getString(3));
				syain.setMailAddress(rs.getString(4));
				syain.setTel(rs.getString(5));

				syains.add(syain);

			}

		} catch (SQLSyntaxErrorException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return syains;
	}

	public String decisionWhere(String sql) {
		if(sql.contains("WHERE")) {
			return " AND ";
		}else {
			return " WHERE ";
		}
	}

	@Override
	public List<Syain> searchSyains(Syain syain) {

		List<Syain> syains = new ArrayList<Syain>();

		try {
			String sql = "SELECT * FROM M_SYAIN ";

			String sc = syain.getSyainCode();
			short po = syain.getPosition();
			String sn = syain.getSyainName();
			String ma = syain.getMailAddress();
			String te = syain.getTel();

			String[][] sArray = {{"SYAIN_CD ","POSITION ","SYAIN_NAME ","MAILADDRESS ","TEL "},{sc,String.valueOf(po),sn,ma,te}};
			for(int i = 0 ; sArray[1].length > i ; i++) {
				if(sArray[1][i].isEmpty()){
					;
				}else if(sArray[1][i]==sArray[1][1]){
					if(po < 0) {
						;
					}else {
						sql += decisionWhere(sql)+ sArray[0][1] + "=" +sArray[1][1];
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
				Syain syainCol = new Syain();
				syainCol.setSyainCode(rs.getString(1));
				syainCol.setPosition(rs.getShort(2));
				syainCol.setSyainName(rs.getString(3));
				syainCol.setMailAddress(rs.getString(4));
				syainCol.setTel(rs.getString(5));

				syains.add(syainCol);
			}
			System.out.println("検索成功");


		} catch (SQLSyntaxErrorException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return syains;
	}

	@Override
	public Syain getSyainByCode(String syain_code) {

		Syain syain = null;

		try {
				String sql = "SELECT * FROM M_SYAIN WHERE SYAIN_CD = ? ";

				PreparedStatement pst = connection.prepareStatement(sql);
				pst.setString(1, syain_code);
				ResultSet rs = pst.executeQuery();

				while (rs.next()) {
					syain = new Syain();
					syain.setSyainCode(rs.getString(1));
					syain.setPosition(rs.getShort(2));
					syain.setSyainName(rs.getString(3));
					syain.setMailAddress(rs.getString(4));
					syain.setTel(rs.getString(5));
				}


		} catch (SQLSyntaxErrorException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return syain;
	}


}