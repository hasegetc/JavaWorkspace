package com.sinaapp.sanrenxing.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

public class HelloAction extends ActionSupport implements ServletResponseAware{
	private static final long serialVersionUID = -6493858961444458651L;
	private HttpServletResponse response;
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	@Override
	public String execute() throws Exception {
		System.out.println("HelloAction.execute is executing...");
		return SUCCESS;
	}
	public void say() throws IOException{
		System.out.println("HelloAction.say is executing....");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<span style='color:red;'>啊哈，We are here!</span>");
		out.close();
	}
	
}
