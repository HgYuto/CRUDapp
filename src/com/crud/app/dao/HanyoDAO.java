package com.crud.app.dao;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

import com.crud.app.model.Hanyo;

public interface HanyoDAO {

	int findCount(Hanyo hanyo);

	void insertHanyo(Hanyo hanyo)throws SQLSyntaxErrorException,SQLException;

	void updateHanyo(Hanyo hanyo)throws SQLSyntaxErrorException,SQLException;

	void deleteHanyo(Hanyo hanyo)throws SQLSyntaxErrorException,SQLException;

	List<Hanyo> getAllHanyos()throws SQLSyntaxErrorException,SQLException;

	List<Hanyo> searchHanyos(Hanyo hanyo);

	Hanyo getHanyoByCode(String hanyo_code,String value_code);

	String decisionWhere(String sql);

	 String decisionSet(String sql);

	String hanyoArraySet(Hanyo hanyo, int i, int j);

	String hanyoWhere(String sql, Hanyo hanyo, int i, int j);

}