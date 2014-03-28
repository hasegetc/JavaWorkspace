package com.sinaapp.sanrenxing.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.sinaapp.sanrenxing.dao.WeiXinDao;
import com.sinaapp.sanrenxing.exception.DBException;

/**
 * 微信自定义回复业务逻辑类
 * 
 * @author 程辉
 * @version V1.0 创建时间：2012-12-18 下午1:52:15
 */
public class WeiXinService {

	private static Logger log = Logger.getLogger(WeiXinService.class
			.getSimpleName());

	public String execute(String xmlData) {
		Map<String, String> msg = null;
		try {
			// 解析xml
			msg = parseXml(xmlData);
		} catch (DocumentException e) {
			log.log(Level.INFO, "解析xml异常", e);
		}
		try {
			// 保存保存数据库
			WeiXinDao dao = new WeiXinDao();
			dao.saveWeiXinTable(msg);
		}  catch (Exception e) {
			log.log(Level.INFO, "保存到数据库异常", e);
		}
		
		// 处理消息
		 String handleMsg = null;
		try {
			handleMsg = handleMsg(msg);
		} catch (DBException e) {
			log.log(Level.INFO, "数据库异常", e);
		}
		 
		 return handleMsg;
	}

	

	/**
	 * 处理消息
	 * 
	 * @param msg
	 * @return
	 * @throws DBException 
	 */
	private String handleMsg(Map<String, String> msg) throws DBException {
		String msgType = msg.get("MsgType");

		if ("location".equals(msgType)) {
			return handleLocationMsg(msg);
		} else if ("text".equals(msgType)) {
			return handleTextMsg(msg);
		} else if ("image".equals(msgType)) {
			return handleImageMsg(msg);
		} else if ("link".equals(msgType)) {
			return handleLinkMsg(msg);
		} else if ("event".equals(msgType)) {
			return handleEventMsg(msg);
		} else {
			return handleUnknowMsg(msg);
		}
	}

	/**
	 * 解析微信发送过来xml,把解析的数据放到map中
	 * 
	 * @param xmlData
	 * @return
	 * @throws DocumentException
	 */
	private static Map<String, String> parseXml(String xmlData)
			throws DocumentException {
		log.log(Level.INFO, xmlData);

		Document doc = DocumentHelper.parseText(xmlData);
		Element rootElt = doc.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> list = rootElt.elements();

		HashMap<String, String> map = new HashMap<String, String>();
		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}

		return map;
	}

	private String handleUnknowMsg(Map<String, String> map) {
		return null;
	}

	private String handleLinkMsg(Map<String, String> map) {
		return null;
	}

	private String handleEventMsg(Map<String, String> map) throws DBException {
		String event = map.get("Event");
		if ("subscribe".equals(event)) {// 对于刚刚订阅用户的回复
			WeiXinDao dao = new WeiXinDao();
			String content= dao.queryAutoReply("subscribe");
			
			return getTextReply(content, map);
		} else {
			return null;
		}
	}
	
	

	private String getTextReply(String content, Map<String, String> map) {
		Map<String, String> reply = new HashMap<String, String>();
		reply.put("Content", content);
		reply.put("ToUserName", map.get("FromUserName"));
		reply.put("FromUserName", map.get("ToUserName"));
		reply.put("CreateTime", new Date().getTime() + "");
		reply.put("MsgType", "text");
		reply.put("FuncFlag", "0");
		return generatorXml(reply);
	}



	private String generatorXml(Map<String, String> reply) {
		if (null == reply) {
			return null;
		}
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("UTF-8");
		Element root = document.addElement("xml");

		for (Entry<String, String> entry : reply.entrySet()) {
			Element e = root.addElement(entry.getKey());
			e.setText(entry.getValue());
		}

		String xml = document.getRootElement().asXML();
		log.log(Level.INFO, "生成回复xml:" + xml);
		return xml;
	}



	/**
	 * 图文信息 
	 * @return
	 */
	private String handleImageMsg(Map<String, String> map) {
		return null;
	}

	/**
	 * 回复地理信息消息
	 * 
	 * @param map
	 * @return
	 */
	private String handleLocationMsg(Map<String, String> map) {

		return null;
	}

	
	
	private String handleTextMsg(Map<String, String> map) throws DBException {
		String key=map.get("Content");
		WeiXinDao dao = new WeiXinDao();
		String content = dao.queryAutoReply(key);
		return getTextReply(content, map);
	}

}
