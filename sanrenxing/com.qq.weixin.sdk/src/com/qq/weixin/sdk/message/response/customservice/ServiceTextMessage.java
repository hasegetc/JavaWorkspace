package com.qq.weixin.sdk.message.response.customservice;

import org.apache.log4j.Logger;

public class ServiceTextMessage extends AbstractServiceMessage {

	private Logger logger = Logger.getLogger(ServiceTextMessage.class);
	private Content text = new Content();

	public Content getText() {
		return text;
	}

	public class Content {
		String content;

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}

	@Override
	public String generatorJson() {
		try {
			return objectMapper.writeValueAsString(this);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

}
