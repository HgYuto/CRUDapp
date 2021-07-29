package com.crud.app.dao;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

import com.crud.app.model.User;

public interface UserDAO {

	int findCount(User user);

	void insertUser(User user)throws SQLSyntaxErrorException,SQLException;

	void updateUser(User user)throws SQLSyntaxErrorException,SQLException;

	void deleteUser(User user)throws SQLSyntaxErrorException,SQLException;

	List<User> getAllUsers()throws SQLSyntaxErrorException,SQLException;

	String decisionWhere(String sql);

	List<User> searchUsers(User user);

	User getUserByCode(String syain_code, String user_id)throws SQLSyntaxErrorException,SQLException;
}