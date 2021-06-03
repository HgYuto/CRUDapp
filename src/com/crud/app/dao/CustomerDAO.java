package com.crud.app.dao;

import java.util.List;

import com.crud.app.model.Customer;

public interface CustomerDAO {
	boolean findCount(Customer customer);

	String insertCustomer(Customer customer);

	void updateCustomer(Customer customer);

	void deleteCustomer(Customer customer);

	List<Customer> getAllCustomers();

	String decisionWhere(String sql);

	List<Customer> searchCustomers(Customer customer);

	Customer getCustomerByCode(String cust_code);

}