package com.crud.app.dao;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

import com.crud.app.model.Syain;

public interface SyainDAO {

	int findCount(Syain syain);

	void insertSyain(Syain syain)throws SQLSyntaxErrorException,SQLException;

	void updateSyain(Syain syain)throws SQLSyntaxErrorException,SQLException;

	void deleteSyain(Syain syain)throws SQLSyntaxErrorException,SQLException;

	List<Syain> getAllSyains()throws SQLSyntaxErrorException,SQLException;

	String decisionWhere(String sql);

	List<Syain> searchSyains(Syain syain);

	Syain getSyainByCode(String syain_code);
}