package com.qq.weixin.sdk.message.response.customservice;

public abstract class ServiceMultimediaMessage extends AbstractServiceMessage {

	private String mediaID;

	public String getMediaID() {
		return mediaID;
	}

	public void setMediaID(String mediaID) {
		this.mediaID = mediaID;
	}

}
