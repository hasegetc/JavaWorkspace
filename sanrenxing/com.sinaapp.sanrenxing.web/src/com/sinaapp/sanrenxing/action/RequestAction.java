package com.sinaapp.sanrenxing.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sinaapp.sanrenxing.bean.RequestLesson;
import com.sinaapp.sanrenxing.service.RegisterService;
import com.sinaapp.sanrenxing.util.DateUtil;

@Component
@Scope("prototype")
public class RequestAction extends ActionSupport {
	private static final long serialVersionUID = -3617424650852024180L;
	@Autowired
	private RegisterService service;

	private Logger logger = Logger.getLogger(RequestAction.class);

	private String useid;

	public String requestLesson() {

		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);

		String classValue = request.getParameter("class");
		String lesson = request.getParameter("lesson");

		RequestLesson requestLesson = new RequestLesson();

		requestLesson.setLesson(lesson);
		requestLesson.setClassValue(classValue);
		requestLesson.setUseid(useid);
		requestLesson.setTimestamp(Long.toString(DateUtil.getTime()));
		requestLesson.setDate(DateUtil.getStringDate());
		// 1表示有效，0表示失效
		requestLesson.setStatus(1);
		
		this.service.deleteRequestLesson(useid);
		this.service.requestLesson(requestLesson);

		return NONE;
	}

	public String getUseid() {
		return useid;
	}

	public void setUseid(String useid) {
		this.useid = useid;
	}

}
