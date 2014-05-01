package com.sinaapp.sanrenxing.util;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sinaapp.sanrenxing.bean.JokeBean;
import com.sinaapp.sanrenxing.service.impl.JokeServiceImpl;

public class JokeUtil {

	private static Logger logger = Logger.getLogger(JokeUtil.class);;
	private static JokeServiceImpl jokeService;
	private static List<Integer> jokeIds;
	private static List<Integer> useJokeId;

	public static String randomSelectOneJoke(String useId) {

		init();
		checkUseJokeId(useId);
		JokeBean joke = null;
		for (int i : jokeIds) {
			if (!useJokeId.contains(i)) {
				joke = jokeService.getJoke(i);
				break;
			}
		}

		insertUseJoke(useId, joke.getId());
		return joke.toString();
	}

	private static void init() {

		getBean();
		if (jokeIds == null) {
			// 此处初始化bean
			jokeIds = jokeService.getJokeIds();
			Collections.shuffle(jokeIds);
			logger.error("bluemoon init jokeIds");
		}
	}

	private static void insertUseJoke(String useId, int jokeId) {
		Map map = new HashMap();
		map.put("useId", useId);
		map.put("jokeId", jokeId);

		jokeService.insertUseJoke(map);
	}

	/**
	 * 如果用户看完全部笑话，就清空记录，从头开始
	 * 
	 * @param useId
	 */
	private static void checkUseJokeId(String useId) {

		useJokeId = jokeService.getUseJokeId(useId);

		if (useJokeId.size() >= jokeIds.size()) {
			jokeService.clearUseJoke(useId);
		}
	}

	public static String getJoke(int id) {

		Document doc = getDocumentByUrl("http://joke.supfree.net/sohu.asp?id="
				+ id);
		if (doc == null) {
			return null;
		}
		// Elements element = doc.getElementsByClass("botitle18");
		Elements element = doc.getElementsByClass("cdiv");
		Element story = element.first();
		if (story == null) {
			return null;
		}
		// Elements allElements = story.getAllElements();
		Elements p = story.getElementsByTag("p");

		if (p == null) {
			return null;
		}
		if (p.size() != 2) {
			return null;
		} else {
			String title = p.get(0).text();
			String joke = p.get(1).text();

			if (joke.length() < 2048) {
				save2DB(id, title, joke);
			}
			return title + ": " + joke;
		}

	}

	private static void save2DB(int id, String title, String joke) {
		System.out.println("开始执行插入数据。。。");
		Map map = new HashMap();
		map.put("id", id);
		map.put("title", title);
		map.put("joke", joke);

		jokeService.insert(map);
		System.out.println("插入数据成功：" + map);
	}

	private static void getBean() {
		if (jokeService == null) {
			ServletContext application = ServletActionContext.getRequest()
					.getSession().getServletContext();
			ApplicationContext context = WebApplicationContextUtils
					.getWebApplicationContext(application);

			jokeService = (JokeServiceImpl) context.getBean("jokeService");
			logger.error("bluemoon init bean");
		}
	}

	public static Document getDocumentByUrl(String url) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).data("jquery", "java")
					.userAgent("Mozilla").cookie("auth", "token")
					.timeout(600000).get();
		} catch (IOException e) {
			logger.error("while getDocumentByUrl error, the url is: " + url, e);
		}
		return doc;
	}

}
