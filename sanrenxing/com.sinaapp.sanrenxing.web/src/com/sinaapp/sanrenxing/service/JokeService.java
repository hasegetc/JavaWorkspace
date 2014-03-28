package com.sinaapp.sanrenxing.service;

import java.util.List;
import java.util.Map;

import com.sinaapp.sanrenxing.bean.JokeBean;

public interface JokeService {

	void insert(Map map);

	JokeBean randomSelectOneJoke(String useId);

	int getJokeCount();

	int getUseJokeCount(String useId);

	List<Integer> getUseJokeId(String useId);
}
