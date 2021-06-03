package com.crud.app.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crud.app.model.Customer;
import com.crud.app.util.DButil;

public class CustomerDAOImpl implements CustomerDAO {

	Connection connection = null;

	public CustomerDAOImpl() throws FileNotFoundException, IOException {

		try {
			connection = DButil.getConnection();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException
				| IOException e) {
			e.printStackTrace();
		}

		System.out.println("connection");
	}

	//取引先コードが重複しているか確認。
	@Override
	public boolean findCount(Customer customer) {
		try {
			String sql = "SELECT COUNT(*) FROM M_CUSTOMER MC WHERE MC.CUST_CODE = ? ;";

			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, customer.getCustCode());
			ResultSet rs = pst.executeQuery();

			if(rs.getInt(1) > 0) {
				return false;
			}else {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public String insertCustomer(Customer customer) {
		String result;
		try {

			String sql = "INSERT INTO M_CUSTOMER(CUST_CODE,CUST_NAME,URL,PAYMENT_SITE) VALUES (? , ? , ? , ?) ;";

			PreparedStatement pst = connection.prepareStatement(sql);
			//インデクス番号、値
			pst.setString(1, customer.getCustCode());
			pst.setString(2, customer.getCustName());
			pst.setString(3, customer.getUrl());
			pst.setString(4, customer.getPaymentSite());

			if (pst ) {
				if(findCount(customer)== true) {
					int res = pst.executeUpdate();
					System.out.println("入力完了");
					return result = "";
				}else {
					return result = "取引先コードが重複しています。再度見直してください。";
				}
			}
			else {
				return result = "接続エラー：ネットワーク不良";
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return result = "接続エラー：ネットワーク不良";
		}
	}

	@Override
	public void updateCustomer(Customer customer) {

		try {
			String sql = "UPDATE M_CUSTOMER SET CUST_NAME = ? ,URL = ? ,PAYMENT_SITE = ? WHERE CUST_CODE = ? ;";

			PreparedStatement pst = connection.prepareStatement(sql);
			//インデクス番号、値
			pst.setString(1, customer.getCustName());
			pst.setString(2, customer.getUrl());
			pst.setString(3, customer.getPaymentSite());
			pst.setString(4,customer.getCustCode());

			int res = pst.executeUpdate();

			if (res > 0) {
				System.out.println("更新成功");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCustomer(Customer customer) {

		try {

			String sql = "DELETE FROM M_CUSTOMER WHERE CUST_CODE = ? ;";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, customer.getCustCode());

			int res = pst.executeUpdate();

			if (res > 0) {
				System.out.println("削除完了");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Customer> getAllCustomers() {

		List<Customer> customers = new ArrayList<Customer>();

		try {
			//取引先テーブル＋社員名
			String sql = "SELECT * FROM M_CUSTOMER ";
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				Customer customer = new Customer();
				//インデクス番号、値
				customer.setCustCode(rs.getString(1));
				customer.setCustName(rs.getString(2));
				customer.setUrl(rs.getString(3));
				customer.setPaymentSite(rs.getString(4));

				customers.add(customer);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customers;
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
	public List<Customer> searchCustomers(Customer customer) {

		List<Customer> customers = new ArrayList<Customer>();

		try {
			String sql = "SELECT * FROM M_CUSTOMER ";

			String cc = customer.getCustCode();
			String cn = customer.getCustName();
			String ul = customer.getUrl();
			String ps = customer.getPaymentSite();

			String[][] sArray = {{"CUST_CODE ","CUST_NAME ","URL ","PAYMENT_SITE "},{cc,cn,ul,ps}};
			for(int i = 0 ; sArray[1].length > i ; i++) {
				if(sArray[1][i].isEmpty()){
					;
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
				Customer customerCol = new Customer();
				customerCol.setCustCode(rs.getString(1));
				customerCol.setCustName(rs.getString(2));
				customerCol.setUrl(rs.getString(3));
				customerCol.setPaymentSite(rs.getString(4));

				customers.add(customerCol);
			}
			if (!customers.isEmpty()) {
				System.out.println("検索成功");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customers;
	}

	@Override
	public Customer getCustomerByCode(String cust_code) {

		Customer customer = null;

		try {

			String sql = "SELECT * FROM M_CUSTOMER WHERE CUST_CODE = ? ";

			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, cust_code);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				customer = new Customer();
				customer.setCustCode(rs.getString(1));
				customer.setCustName(rs.getString(2));
				customer.setUrl(rs.getString(3));
				customer.setPaymentSite(rs.getString(4));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customer;
	}

}