package com.crud.app.dao;

import java.util.List;

import com.crud.app.model.Customer;

public interface CustomerDAO {

	void insertCustomer(Customer costomer);

	void updateCustomer(Customer costomer);

	void deleteCustomer(Customer costomer);

	List<Customer> getAllCustomers();

	Customer getCustomerByCode(String cust_code);

}