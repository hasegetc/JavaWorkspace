package com.sinaapp.sanrenxing.util;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpUtil {

	private static Logger log = Logger.getLogger(MessageUtil.class);

	/**
	 * 成功返回true
	 * 失败返回false
	 */
	public static boolean httpPost(String url, List<NameValuePair> params) {

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);

		String body = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse response = client.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				log.error("Method failed:" + response.getStatusLine());
				return false;
			}
			// Read the response body
			body = EntityUtils.toString(response.getEntity());
			log.error("Method failed:" + body);

		} catch (Exception e) {
			log.error(e);
		}
		return true;
	}

	/**
	 * 失敗返回null
	 * @param url
	 * @return
	 */
	public static JSONObject httpGet(String url) {
		HttpClient client = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);

		String body = null;
		try {
			HttpResponse response = client.execute(httpget);

			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				log.error("Method failed:" + response.getStatusLine());
				return null;
			}
			// Read the response body
			body = EntityUtils.toString(response.getEntity());

		} catch (Exception e) {
			log.error(e);
		} finally {
			httpget.abort();
		}
		return JSONObject.fromObject(body);

	}
}
