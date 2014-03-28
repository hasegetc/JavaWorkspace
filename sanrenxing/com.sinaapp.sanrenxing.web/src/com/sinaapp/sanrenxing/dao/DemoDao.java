package com.sinaapp.sanrenxing.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DemoDao {

	public void insertStudent(Map map);

}
