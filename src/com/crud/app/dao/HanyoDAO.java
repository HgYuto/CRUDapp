package com.crud.app.dao;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

import com.crud.app.model.Hanyo;

public interface HanyoDAO {

	int findCount(Hanyo hanyo)throws SQLSyntaxErrorException,SQLException;

	void insertHanyo(Hanyo hanyo)throws SQLSyntaxErrorException,SQLException;

	void updateHanyo(Hanyo hanyo)throws SQLSyntaxErrorException,SQLException;

	void deleteHanyo(Hanyo hanyo)throws SQLSyntaxErrorException,SQLException;

	List<Hanyo> getAllHanyos()throws SQLSyntaxErrorException,SQLException;

	List<Hanyo> searchHanyos(Hanyo hanyo);

	Hanyo getHanyoByCode(String hanyo_code,String value_code)throws SQLSyntaxErrorException,SQLException;

	String decisionWhere(String sql);

}