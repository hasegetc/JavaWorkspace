package com.qq.weixin.sdk.message.receive.commenmessage;

import org.dom4j.Element;

import com.qq.weixin.sdk.message.receive.ReceivedMessage;

public class ReceivedLocationMessage extends ReceivedMessage {

	protected String locationX;
	protected String locationY;
	protected String scale;
	protected String label;

	public ReceivedLocationMessage(Element root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	public String getLocationX() {
		return locationX;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	public String getLocationY() {
		return locationY;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
