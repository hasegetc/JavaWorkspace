package com.qq.weixin.sdk.message.receive.event;

import org.dom4j.Element;

public class QRCodeEvent extends AbstractEvent {

	private String latitude;
	private String longitude;
	private String precision;

	public QRCodeEvent(Element root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

}
