package com.qq.weixin.sdk.message.response.passiveresponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.qq.weixin.sdk.consts.IMessageConsts;


public class ResponseTextMessage extends AbstractResponseMessage {

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String generatorXml() {



		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("UTF-8");
		Element root = document.addElement("xml");

		super.addElements(root);

		Element element = root.addElement(IMessageConsts.TAG_CONTENT);
		
		element.setText(content);

		String result = document.getRootElement().asXML();
		return result;
	
	
	}

}
