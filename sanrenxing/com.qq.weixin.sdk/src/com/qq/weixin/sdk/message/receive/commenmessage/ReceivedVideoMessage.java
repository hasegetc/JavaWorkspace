package com.qq.weixin.sdk.message.receive.commenmessage;

import org.dom4j.Element;

public class ReceivedVideoMessage extends ReceivedMultimediaMessage {

	public ReceivedVideoMessage(Element root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	private String thumbMediaId;

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

}
