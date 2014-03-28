package com.sinaapp.sanrenxing.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinaapp.sanrenxing.dao.DemoDao;
import com.sinaapp.sanrenxing.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {
	@Autowired
	private DemoDao demoDao;
	
	@Override
	public void insert(Map map) {
		this.demoDao.insertStudent(map);
	}
}
