package com.sinaapp.sanrenxing.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

public class SimsimiUtil {

	/**
	 * 
	 * App Name : 一壶明月 Category : Trial Key :
	 * 8a75aa7f-dbe0-42b3-b968-ae417e61ddcd Created : Wed Mar 05 16:35:08 UTC
	 * 2014
	 */

	static Logger logger = Logger.getLogger(SimsimiUtil.class);

	public static void main(String[] args) throws IOException {

		System.err.println(getResponse("你是谁")); //$NON-NLS-1$
		System.err.println(getResponse("今天几号")); //$NON-NLS-1$
		System.err.println(getResponse("天气怎么样")); //$NON-NLS-1$
		System.err.println(getResponse("我喜欢你")); //$NON-NLS-1$
		System.err.println(getResponse("hello")); //$NON-NLS-1$
		System.err.println(getResponse("I Love You")); //$NON-NLS-1$
		System.err.println(getResponse("一壶明月")); //$NON-NLS-1$
	}

	public static String getResponse(String msg) {
		HttpGet httpGet = initHttpGet(msg);

		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = ""; //$NON-NLS-1$
		HttpClient client = new DefaultHttpClient();
		try {
			responseBody = client.execute(httpGet, responseHandler);
		} catch (Exception e) {
			logger.error("client.execute error", e); //$NON-NLS-1$
			responseBody = null;
		} finally {
			httpGet.abort();
		}
		Map reply = JSONObject.fromObject(responseBody);
		int result = (Integer) reply.get("result"); //$NON-NLS-1$
		if (result == 100)
			return (String) reply.get("response"); //$NON-NLS-1$
		else
			return Messages.getString("SimsimiUtil.ChickenCooked"); //$NON-NLS-1$
	}

	private static HttpGet initHttpGet(String msg) {
		HttpGet httpGet = null;
		try {
			httpGet = new HttpGet("http://www.simsimi.com/func/req?msg=" //$NON-NLS-1$
					+ URLEncoder.encode(msg, "UTF-8") + "&lc=ch"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (UnsupportedEncodingException e) {
			logger.error("new HttpGet error ", e); //$NON-NLS-1$
		}
		httpGet.setHeader("Accept", //$NON-NLS-1$
				"application/json, text/javascript, */*; q=0.01"); //$NON-NLS-1$
		httpGet.setHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3"); //$NON-NLS-1$ //$NON-NLS-2$
		httpGet.setHeader("Accept-Encoding", "gzip,deflate,sdch"); //$NON-NLS-1$ //$NON-NLS-2$
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8"); //$NON-NLS-1$ //$NON-NLS-2$
		httpGet.setHeader("Connection", "keep-alive"); //$NON-NLS-1$ //$NON-NLS-2$
		httpGet.setHeader("Content-Type", "application/json; charset=utf-8"); //$NON-NLS-1$ //$NON-NLS-2$
		// httpget.setHeader(
		// "Cookie",
		// "AWSELB=4B7FB5FB1E2B99E00C748D6720647E1D18B7ECA6CF77DF13F5AF77ED586F7C1615048ABCFFEB12886800082B6589E54DC7DAA2778C37B0D04F39086F5CEE516E493359E813;PATH=/;MAX-AGE=86400");
		httpGet.setHeader("Host", "www.simsimi.com"); //$NON-NLS-1$ //$NON-NLS-2$
		httpGet.setHeader("Referer", "http://www.simsimi.com/talk.htm?lc=ch"); //$NON-NLS-1$ //$NON-NLS-2$
		httpGet.setHeader(
				"User-Agent", //$NON-NLS-1$
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.71 Safari/537.1 LBBROWSER"); //$NON-NLS-1$
		httpGet.setHeader("X-Requested-With", "XMLHttpRequest"); //$NON-NLS-1$ //$NON-NLS-2$
		return httpGet;
	}

}
