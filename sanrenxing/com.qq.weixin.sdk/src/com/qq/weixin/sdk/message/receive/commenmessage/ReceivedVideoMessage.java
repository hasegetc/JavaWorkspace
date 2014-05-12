package com.qq.weixin.sdk.message.receive.commenmessage;

import org.dom4j.Element;

import com.qq.weixin.sdk.consts.IMessageConsts;

public class ReceivedVideoMessage extends ReceivedMultimediaMessage {

	public ReceivedVideoMessage(Element root) {
		super(root);
		this.thumbMediaId = root.element(IMessageConsts.TAG_THUMBMEDIAID).getText();
	}

	private String thumbMediaId;

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

}
