package com.frame.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.dao.DemoDao;
import com.frame.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {
	@Autowired
	private DemoDao demoDao;
	
	@Override
	public void insert(Map map) {
		this.demoDao.insert(map);
	}
}
