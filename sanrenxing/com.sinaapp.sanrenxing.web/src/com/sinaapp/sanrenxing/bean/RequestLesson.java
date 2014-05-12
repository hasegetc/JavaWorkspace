package com.sinaapp.sanrenxing.bean;

import java.util.HashMap;
import java.util.Map;

public class RequestLesson {
	private String useid;
	private String classValue;
	private String lesson;

	private String timestamp;
	private String date;

	// 1表示有效，0表示失效
	private int status;

	private static Map<String, String> lessonMap = new HashMap<String, String>();
	static {
		lessonMap.put("math", "数学");
		lessonMap.put("physical", "物理");
		lessonMap.put("chemistry", "化学");
		lessonMap.put("english", "英语");
		lessonMap.put("biological", "生物");
	}

	private static Map<String, String> classMap = new HashMap<String, String>();
	static {
		classMap.put("primary", "小学");
		classMap.put("junior", "初中");
		classMap.put("senior", "高中");
	}

	public String getUseid() {
		return useid;
	}

	public void setUseid(String useid) {
		this.useid = useid;
	}

	public String getClassValue() {
		return classValue;
	}

	public void setClassValue(String classValue) {
		this.classValue = classValue;
	}


	public String getLessonText() {
		return lessonMap.get(lesson);
	}
	
	public String getClassText() {
		return classMap.get(classValue);
	}
	
	public String getLesson() {
		return lesson;
	}

	public void setLesson(String lesson) {
		this.lesson = lesson;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
