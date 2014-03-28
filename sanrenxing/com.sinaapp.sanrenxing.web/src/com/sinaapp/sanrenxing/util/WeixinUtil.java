package com.sinaapp.sanrenxing.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dom4j.Element;

import com.qq.weixin.sdk.accesstoken.AccessToken;
import com.qq.weixin.sdk.consts.IMessageConsts;
import com.qq.weixin.sdk.message.receive.ReceivedMessage;
import com.qq.weixin.sdk.message.receive.commenmessage.ReceivedPictureMessage;
import com.qq.weixin.sdk.message.receive.commenmessage.ReceivedTextMessage;
import com.qq.weixin.sdk.message.receive.commenmessage.ReceivedVoiceMessage;
import com.sinaapp.sanrenxing.consts.ISanrenxingConsts;

public class WeixinUtil {

	static Logger logger = Logger.getLogger(WeixinUtil.class);

	// 获取access_token的接口地址（GET） 限2000（次/天）
	private final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	/**
	 * http请求方式: POST
	 */
	private final static String POST_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

	private static AccessToken accessToken;

	public static ReceivedMessage getMessage(Element root) {

		String type = root.element(IMessageConsts.TAG_MSGTYPE).getText();

		ReceivedMessage receivedMessage = null;
		if (type.equalsIgnoreCase(IMessageConsts.MESSAGE_TEXT)) {

			receivedMessage = new ReceivedTextMessage(root);

		} else if (type.equalsIgnoreCase(IMessageConsts.MESSAGE_EVENT)) {

		} else if (type.equalsIgnoreCase(IMessageConsts.MESSAGE_IMAGE)) {

			receivedMessage = new ReceivedPictureMessage(root);

		} else if (type.equalsIgnoreCase(IMessageConsts.MESSAGE_LINK)) {

		} else if (type.equalsIgnoreCase(IMessageConsts.MESSAGE_LOCATION)) {

		} else if (type.equalsIgnoreCase(IMessageConsts.MESSAGE_VOICE)) {

			receivedMessage = new ReceivedVoiceMessage(root);
		}

		return receivedMessage;
	}

	public static boolean checkSignature(String signature, String timestamp,
			String nonce, String token) {

		// dictionary sort
		String[] dataStrings = new String[] { token, timestamp, nonce };
		Arrays.sort(dataStrings);
		// construct the three string
		String data = dataStrings[0] + dataStrings[1] + dataStrings[2];
		// sha1
		String flag = DigestUtils.shaHex(data);
		// check
		if (flag.equalsIgnoreCase(signature)) {
			return true;
		} else {
			return false;
		}
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
		String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appid).replace(
				"APPSECRET", appsecret);

		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
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

	private static JSONObject httpRequest(String requestUrl, String string,
			Object object) {
		HttpClient client = new DefaultHttpClient();
		HttpGet httpget = null;
		httpget = new HttpGet(requestUrl);

		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = "";
		try {
			responseBody = client.execute(httpget, responseHandler);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			httpget.abort();
		}
		return JSONObject.fromObject(responseBody);

	}

	public static void main(String[] args) {
		System.err.println(getAccessToken(ISanrenxingConsts.APPID,
				ISanrenxingConsts.APPSECRET));
	}

	public static void post(String response) {
		String token = getAccessToken(ISanrenxingConsts.APPID,
				ISanrenxingConsts.APPSECRET);

		String url = POST_URL.replace("ACCESS_TOKEN", token);
		httpPost(url, response);
	}

	private static void httpPost(String url, String value) {
		String body = null;

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		// 设置编码
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// 建立一个NameValuePair数组，用于存储欲传送的参数
		params.add(new BasicNameValuePair("data", value));
		// 添加参数

		try {
			post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse response = client.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("Method failed:" + response.getStatusLine());
			}
			// Read the response body
			body = EntityUtils.toString(response.getEntity());
			logger.error("Method failed:" + body);

		} catch (Exception e) {
			logger.error(e);
		}
	}
}
