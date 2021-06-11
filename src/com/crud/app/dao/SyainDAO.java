package com.crud.app.dao;

import java.util.List;

import com.crud.app.model.Syain;

public interface SyainDAO {

	int findCount(Syain syain);

	void insertSyain(Syain syain);

	void updateSyain(Syain syain);

	void deleteSyain(Syain syain);

	List<Syain> getAllSyains();

	String decisionWhere(String sql);

	List<Syain> searchSyains(Syain syain);

	Syain getSyainByCode(String syain_code);
}