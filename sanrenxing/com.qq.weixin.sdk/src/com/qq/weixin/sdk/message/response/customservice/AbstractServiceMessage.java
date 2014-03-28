package com.qq.weixin.sdk.message.response.customservice;

import org.codehaus.jackson.map.ObjectMapper;

public abstract class AbstractServiceMessage {

	private String touser;
	private String msgtype;
	protected ObjectMapper objectMapper = new ObjectMapper();;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public abstract String generatorJson();
}
