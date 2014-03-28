package com.sinaapp.sanrenxing.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinaapp.sanrenxing.bean.JokeBean;
import com.sinaapp.sanrenxing.dao.JokeDao;
import com.sinaapp.sanrenxing.service.JokeService;

@Service
public class JokeServiceImpl implements JokeService {
	@Autowired
	private JokeDao jokeDao;

	@Override
	public void insert(Map map) {
		this.jokeDao.insertJoke(map);
	}

	@Override
	public JokeBean randomSelectOneJoke(String useId) {
		JokeBean joke = jokeDao.randomSelectOneJoke(useId);

		return joke;
	}

	public int getUseJokeCount(String useId) {
		return jokeDao.getUseJokeCount(useId);
	}

	public List<Integer> getUseJokeId(String useId) {
		return jokeDao.getUseJokeId(useId);
	}

	public int getJokeCount() {
		return jokeDao.getJokeCount();
	}

	public void clearUseJoke(String useId) {
		jokeDao.clearUseJoke(useId);
	}

	public void insertUseJoke(Map map) {
		this.jokeDao.insertUseJoke(map);
	}

	public List<Integer> getJokeIds() {
		return this.jokeDao.getJokeIds();
	}

	public JokeBean getJoke(int id) {
		JokeBean joke = jokeDao.getJoke(id);
		return joke;
	}
}
