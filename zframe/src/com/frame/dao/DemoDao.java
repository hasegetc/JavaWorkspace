package com.frame.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DemoDao {

	public void insert(Map map);

}
