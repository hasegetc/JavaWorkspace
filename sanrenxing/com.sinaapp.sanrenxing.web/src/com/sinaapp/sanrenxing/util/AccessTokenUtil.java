package com.sinaapp.sanrenxing.util;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.qq.weixin.sdk.accesstoken.AccessToken;
import com.sinaapp.sanrenxing.consts.ISanrenxingConsts;

public class AccessTokenUtil {

	static Logger logger = Logger.getLogger(AccessTokenUtil.class);

	// 获取access_token的接口地址（GET） 限2000（次/天）
	private final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	private static AccessToken accessToken;

	public static void main(String[] args) {
		System.err.println(getAccessToken(ISanrenxingConsts.APPID,
				ISanrenxingConsts.APPSECRET));
	}

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static String getAccessToken(String appid, String appsecret) {

		if (accessToken == null) {
			accessToken = getAccessTokenFromWeixin(appid, appsecret);
		} else {
			boolean isExpires = (int) (System.currentTimeMillis() / 1000)
					- accessToken.getGetTime() > accessToken.getExpiresIn() - 100;
			if (isExpires) {
				accessToken = getAccessTokenFromWeixin(appid, appsecret);
			}
		}
		return accessToken.getAccessToken();
	}

	private static AccessToken getAccessTokenFromWeixin(String appid,
			String appsecret) {
		String url = ACCESS_TOKEN_URL.replace("APPID", appid).replace(
				"APPSECRET", appsecret);

		JSONObject jsonObject = HttpUtil.httpGet(url);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken
						.setAccessToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
				accessToken
						.setGetTime((int) (System.currentTimeMillis() / 1000));
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				logger.error("获取token失败 errcode:{} errmsg:{}"
						+ jsonObject.getInt("errcode") + " "
						+ jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}
}
