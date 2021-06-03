package com.crud.app.dao;

import java.util.List;

import com.crud.app.model.Hanyo;

public interface HanyoDAO {

	void insertHanyo(Hanyo hanyo);

	void updateHanyo(Hanyo hanyo);

	void deleteHanyo(Hanyo hanyo);

	List<Hanyo> getAllHanyos();

	List<Hanyo> searchHanyos(Hanyo hanyo);

	Hanyo getHanyoByCode(String hanyo_code,String value_code);

	String decisionWhere(String sql);

	 String decisionSet(String sql);

	String hanyoArraySet(Hanyo hanyo,int i ,int k);

	String hanyoWhere(String sql,Hanyo hanyo,int i,int k);

}