package com.sinaapp.sanrenxing.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinaapp.sanrenxing.bean.RegisterLesson;
import com.sinaapp.sanrenxing.bean.RequestLesson;
import com.sinaapp.sanrenxing.dao.SanrenxingDao;
import com.sinaapp.sanrenxing.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {
	@Autowired
	private SanrenxingDao dao;

	@Override
	public void registerLesson(List<RegisterLesson> lessonList) {
		dao.registerLesson(lessonList);
	}

	@Override
	public void unregisterLesson(RegisterLesson unregisterLesson) {
		dao.unregisterLesson(unregisterLesson);
	}

	@Override
	public List<RegisterLesson> getRegistedLesson(String useId) {
		return dao.getRegistedLesson(useId);
	}

	@Override
	public void unregisterAllLesson(String useid) {
		dao.unregisterAllLesson(useid);
	}

	@Override
	public List<RegisterLesson> getRegistedLesson(String useId,
			String classValue) {
		List<RegisterLesson> registedLesson = getRegistedLesson(useId);
		List<RegisterLesson> result = new ArrayList<RegisterLesson>();

		for (RegisterLesson v : registedLesson) {
			if (v.getClassValue().equalsIgnoreCase(classValue)) {
				result.add(v);
			}
		}

		return result;
	}

	@Override
	public void requestLesson(RequestLesson requestLesson) {
		dao.requestLesson(requestLesson);
	}

	@Override
	public void deleteRequestLesson(String useid) {
		dao.deleteRequestLesson(useid);
	}

}
