package com.qq.weixin.sdk.message.receive.event;

import org.dom4j.Element;

import com.qq.weixin.sdk.consts.IMessageConsts;
import com.qq.weixin.sdk.message.receive.ReceivedMessage;

public abstract class AbstractEvent extends ReceivedMessage {

	protected String event;

	public AbstractEvent(Element root) {
		super(root);
		this.event = root.element(IMessageConsts.TAG_EVENT).getText();
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

}
