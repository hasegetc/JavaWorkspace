package com.qq.weixin.sdk.message.receive.commenmessage;

import org.dom4j.Element;

import com.qq.weixin.sdk.consts.IMessageConsts;
import com.qq.weixin.sdk.message.receive.ReceivedMessage;

public abstract class ReceivedMultimediaMessage extends ReceivedMessage {

	private String mediaId;

	public ReceivedMultimediaMessage(Element root) {
		super(root);
		this.mediaId = root.element(IMessageConsts.TAG_MEDIAID).getText();
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}
