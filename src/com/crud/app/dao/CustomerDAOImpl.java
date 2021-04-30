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

	@Override
	public void insertCustomer(Customer customer) {

		try {

			String sql = "INSERT INTO M_CUSTOMER(CUST_CODE,CUST_NAME,URL,PAYMENT_SITE) VALUES (? , ? , ? , ?) ;";

			PreparedStatement pst = connection.prepareStatement(sql);
			//インデクス番号、値
			pst.setString(1, customer.getCustCode());
			pst.setString(2, customer.getCustName());
			pst.setString(3, customer.getUrl());
			pst.setString(4, customer.getPaymentSite());

			int res = pst.executeUpdate();

			if (res > 0) {
				System.out.println("入力完了");
			}

		} catch (SQLException e) {
			e.printStackTrace();
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