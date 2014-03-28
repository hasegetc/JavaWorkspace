package com.qq.weixin.sdk.message.response.passiveresponse;

public class ResponceVideoMessage extends ResponceMultimediaMessage {

	private String title;
	private String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String generatorXml() {
		// TODO Auto-generated method stub
		return null;
	}

}
