package com.qq.weixin.sdk.message.response.customservice;

import java.util.HashMap;
import java.util.Map;

public class ServiceVoiceMessage extends ServiceMultimediaMessage {

	private Map<String, String> voice = new HashMap<String, String>();

	public Map<String, String> getVoice() {
		return voice;
	}

	public void setVoice(Map<String, String> voice) {
		this.voice = voice;
	}

	@Override
	public void setMediaId(String mediaId) {
		voice.put("media_id", mediaId);
	}


	public static void main(String[] args) {
		ServiceVoiceMessage t = new ServiceVoiceMessage();
		t.getVoice().put("media_id", "Hello World");
		System.err.println(t.generatorJson());
	}

}
