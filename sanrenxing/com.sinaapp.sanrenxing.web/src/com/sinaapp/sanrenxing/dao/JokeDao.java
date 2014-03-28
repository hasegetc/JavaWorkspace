package com.sinaapp.sanrenxing.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinaapp.sanrenxing.bean.JokeBean;

@Repository
@Transactional
public interface JokeDao {

	void insertJoke(Map map);

	void insertUseJoke(Map map);

	JokeBean randomSelectOneJoke(String useId);

	int getUseJokeCount(String useId);

	int getJokeCount();

	void clearUseJoke(String useId);

	List<Integer> getJokeIds();

	List<Integer> getUseJokeId(String useId);

	JokeBean getJoke(int id);

}
