package com.qq.weixin.sdk.message.response.customservice;

import java.util.HashMap;
import java.util.Map;

public class ServicePictureMessage extends ServiceMultimediaMessage {

	private Map<String, String> image = new HashMap<String, String>();

	public Map<String, String> getImage() {
		return image;
	}

	public void setImage(Map<String, String> image) {
		this.image = image;
	}

	@Override
	public void setMediaId(String mediaId) {
		image.put("media_id", mediaId);
	}

}
