package com.crud.app.dao;

import java.util.List;

import com.crud.app.model.Customer;

public interface CustomerDAO {

	int findCount(Customer customer);

	void insertCustomer(Customer customer);

	void updateCustomer(Customer customer);

	void deleteCustomer(Customer customer);

	List<Customer> getAllCustomers();

	String decisionWhere(String sql);

	List<Customer> searchCustomers(Customer customer);

	Customer getCustomerByCode(String cust_code);

}