package com.qq.weixin.sdk.message.receive.event;

import org.dom4j.Element;

public class LocationEvent extends AbstractEvent {

	private String eventKey;
	private String ticket;

	public LocationEvent(Element root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

}
