package com.qq.weixin.sdk.message.response.customservice;

import java.util.HashMap;
import java.util.Map;

public class ServiceTextMessage extends AbstractServiceMessage {

	private Map<String, String> text = new HashMap<String, String>();

	public Map<String, String> getText() {
		return text;
	}

	public void setText(String text) {
		
		this.text.put("content", text);
	}

}
