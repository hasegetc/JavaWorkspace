package com.sinaapp.sanrenxing.service;

import java.util.List;

import com.sinaapp.sanrenxing.bean.RegisterLesson;
import com.sinaapp.sanrenxing.bean.RequestLesson;

public interface RegisterService {

	void registerLesson(List<RegisterLesson> lessonList);

	List<RegisterLesson> getRegistedLesson(String useId);

	List<RegisterLesson> getRegistedLesson(String string, String classValue);

	void unregisterLesson(RegisterLesson unregisterLesson);

	void unregisterAllLesson(String useid);

	void requestLesson(RequestLesson requestLesson);

	void deleteRequestLesson(String useid);

}
