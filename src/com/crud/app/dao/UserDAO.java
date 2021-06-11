package com.crud.app.dao;

import java.util.List;

import com.crud.app.model.User;

public interface UserDAO {

	int findCount(User user);

	void insertUser(User user);

	void updateUser(User user);

	void deleteUser(User user);

	List<User> getAllUsers();

	String decisionWhere(String sql);

	List<User> searchUsers(User user);

	User getUserByCode(String syain_code, String user_id);
}