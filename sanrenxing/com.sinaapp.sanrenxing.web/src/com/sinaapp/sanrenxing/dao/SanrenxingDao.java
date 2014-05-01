package com.sinaapp.sanrenxing.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinaapp.sanrenxing.bean.RegisterLesson;
import com.sinaapp.sanrenxing.bean.Relation;
import com.sinaapp.sanrenxing.bean.RequestLesson;

@Repository
@Transactional
public interface SanrenxingDao {

	void registerLesson(List<RegisterLesson> lessonList);

	List<RegisterLesson> getRegistedLesson(String useId);

	void unregisterLesson(RegisterLesson unregisterLesson);

	void unregisterAllLesson(String useid);

	void requestLesson(RequestLesson requestLesson);

	void deleteRequestLesson(String useid);

	List<Relation> getToUserName(String fromUserName);

	void stopRelation(String userName);

	List<RegisterLesson> getLesson(String userName);

	List<String> getForwardTeachers();

	void usePrevService(String userName);

	void startService(Relation relation);

	List<String> getTeachers(Map<String, String> v);
}
