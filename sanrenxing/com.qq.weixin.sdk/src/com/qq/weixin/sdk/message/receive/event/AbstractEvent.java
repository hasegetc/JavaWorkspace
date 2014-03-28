package com.qq.weixin.sdk.message.receive.event;

import org.dom4j.Element;

import com.qq.weixin.sdk.message.receive.ReceivedMessage;

public abstract class AbstractEvent extends ReceivedMessage {

	protected String event;

	public AbstractEvent(Element root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

}
