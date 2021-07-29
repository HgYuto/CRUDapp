package com.crud.app.dao;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

import com.crud.app.model.Customer;

public interface CustomerDAO {

	int findCount(Customer customer)throws SQLSyntaxErrorException,SQLException;

	void insertCustomer(Customer customer)throws SQLSyntaxErrorException,SQLException;

	void updateCustomer(Customer customer)throws SQLSyntaxErrorException,SQLException;

	void deleteCustomer(Customer customer)throws SQLSyntaxErrorException,SQLException;

	List<Customer> getAllCustomers()throws SQLSyntaxErrorException,SQLException;

	String decisionWhere(String sql);

	List<Customer> searchCustomers(Customer customer);

	Customer getCustomerByCode(String cust_code)throws SQLSyntaxErrorException,SQLException ;

}