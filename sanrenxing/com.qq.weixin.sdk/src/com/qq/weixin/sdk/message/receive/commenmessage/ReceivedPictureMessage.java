package com.qq.weixin.sdk.message.receive.commenmessage;

import org.dom4j.Element;

import com.qq.weixin.sdk.consts.IMessageConsts;

public class ReceivedPictureMessage extends ReceivedMultimediaMessage {

	private String picUrl;

	public ReceivedPictureMessage(Element root) {
		super(root);
		this.picUrl = root.element(IMessageConsts.TAG_PICURL).getText();
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

}
