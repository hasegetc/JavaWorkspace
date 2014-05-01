package com.sinaapp.sanrenxing.util;

import com.sinaapp.sanrenxing.consts.ISanrenxingConsts;

public class CustomMsgUtil {

	/**
	 * http请求方式: POST
	 */
	private final static String POST_CUSTOM_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

	public static boolean postCustomMsg(String response) {
		String token = AccessTokenUtil.getAccessToken(ISanrenxingConsts.APPID,
				ISanrenxingConsts.APPSECRET);

		String url = POST_CUSTOM_MESSAGE_URL.replace("ACCESS_TOKEN", token);
		HttpsUtil.post(url, response);
		return false;
	}
}
