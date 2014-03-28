package com.qq.weixin.sdk.message.receive.commenmessage;

import org.dom4j.Element;

import com.qq.weixin.sdk.consts.IMessageConsts;

public class ReceivedVoiceMessage extends ReceivedMultimediaMessage {

	public ReceivedVoiceMessage(Element root) {
		super(root);
		this.format = root.element(IMessageConsts.TAG_FORMAT).getText();
	}

	private String format;

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
