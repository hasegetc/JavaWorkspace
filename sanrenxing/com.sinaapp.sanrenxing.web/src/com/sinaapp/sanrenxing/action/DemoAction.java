package com.sinaapp.sanrenxing.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.sinaapp.sanrenxing.service.DemoService;

@Component
@Scope("prototype")
public class DemoAction extends ActionSupport {
	private static final long serialVersionUID = -3617424650852024180L;
	@Autowired
	private DemoService demoService;
	private String name;
	private String gender;
	private int age;
	private Logger logger = Logger.getLogger(DemoAction.class);
	
	public void insert() {
		Map map = new HashMap();
		map.put("name", name);
		map.put("gender", gender);
		map.put("age", age);
		this.demoService.insert(map);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
