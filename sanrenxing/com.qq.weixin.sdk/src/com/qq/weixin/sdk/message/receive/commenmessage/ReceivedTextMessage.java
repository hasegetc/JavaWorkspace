package com.qq.weixin.sdk.message.receive.commenmessage;

import org.dom4j.Element;

import com.qq.weixin.sdk.consts.IMessageConsts;
import com.qq.weixin.sdk.message.receive.ReceivedMessage;

public class ReceivedTextMessage extends ReceivedMessage {

	protected String content;

	public ReceivedTextMessage(Element root) {
		super(root);
		this.content = root.element(IMessageConsts.TAG_CONTENT).getText();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
