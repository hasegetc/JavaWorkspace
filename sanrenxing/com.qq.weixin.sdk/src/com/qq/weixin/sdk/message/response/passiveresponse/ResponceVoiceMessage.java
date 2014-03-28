package com.qq.weixin.sdk.message.response.passiveresponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.qq.weixin.sdk.consts.IMessageConsts;

public class ResponceVoiceMessage extends ResponceMultimediaMessage {

	@Override
	public String generatorXml() {

		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("UTF-8");
		Element root = document.addElement("xml");

		super.addElements(root);

		Element voiceElement = root.addElement(IMessageConsts.TAG_VOICE);
		Element mediaIdElement = voiceElement
				.addElement(IMessageConsts.TAG_MEDIAID);
		mediaIdElement.setText(getMediaId());

		String result = document.getRootElement().asXML();
		return result;
	}

}
