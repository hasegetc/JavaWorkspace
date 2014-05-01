package com.qq.weixin.sdk.message.receive.event;

import org.dom4j.Element;

import com.qq.weixin.sdk.consts.IMessageConsts;

public class MeunEvent extends AbstractEvent {

	private String eventKey;

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public MeunEvent(Element root) {
		super(root);
		this.eventKey = root.element(IMessageConsts.TAG_EVENTKEY).getText();
	}

}
