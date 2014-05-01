package com.sinaapp.sanrenxing.util;

import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.sinaapp.sanrenxing.bean.RegisterLesson;
import com.sinaapp.sanrenxing.bean.Relation;
import com.sinaapp.sanrenxing.job.SanrenxingJob;
import com.sinaapp.sanrenxing.service.impl.SanrenxingServiceImpl;

public class SanrenxingUtil {

	private static Logger logger = Logger.getLogger(SanrenxingUtil.class);
	private static SanrenxingServiceImpl service = SanrenxingServiceImpl
			.getInstance();

	public static String getToUserName(String fromUserName) {
		return service.getToUserName(fromUserName);
	}

	public static RegisterLesson getLesson(String userName) {
		return service.getLesson(userName);
	}

	// 正在接受服务的老师不能转发
	synchronized public static List<String> getTeachers(String classValue, String lessonValue) {
		return service.getTeachers(classValue, lessonValue);
	}

	/**
	 * 得到所有正在转发中的老师
	 */
	public static List<String> getForwardTeachers() {
		return service.getForwardTeachers();
	}

	public static void stopService(String userName) {
		service.stopService(userName);
	}

	public static void usePrevLesson(String userName) {
		service.usePrevService(userName);
	}

	/**
	 * 考虑多线程的问题
	 * 多线程调用此函数时要阻塞
	 * 多线程修改teachermap时要阻塞，会不会死锁？
	 * 用户在startService时，不能将学生消息转发
	 * @param tearchName
	 */
	synchronized public static boolean startService(String tearchName) {

		String studentName = SanrenxingJob.teacherMap.get(tearchName);
		if(studentName == null)
		{
			return false;
		}
		Relation relation = new Relation();
		relation.setTeacherId(tearchName);
		relation.setStudentId(studentName);
		relation.setStatus(1);
		relation.setTimestamp(Long.toString(DateUtil.getTime()));
		relation.setDate(DateUtil.getStringDate());
		service.startService(relation);
		for (Entry<String, String> v : SanrenxingJob.teacherMap.entrySet()) {
			if (v.getValue().equalsIgnoreCase(studentName)) {
				SanrenxingJob.teacherMap.remove(v.getKey());
			}
		}
		return true;
	}
}
