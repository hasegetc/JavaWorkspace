package com.qq.weixin.sdk.message.response.passiveresponse;


public abstract class ResponceMultimediaMessage extends AbstractResponseMessage {

	private String mediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}
