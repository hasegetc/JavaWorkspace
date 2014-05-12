package com.qq.weixin.sdk.message.response.passiveresponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.qq.weixin.sdk.consts.IMessageConsts;

public class ResponcePictureMessage extends ResponceMultimediaMessage {

	// <xml>
	// <ToUserName><![CDATA[toUser]]></ToUserName>
	// <FromUserName><![CDATA[fromUser]]></FromUserName>
	// <CreateTime>12345678</CreateTime>
	// <MsgType><![CDATA[image]]></MsgType>
	// <Image>
	// <MediaId><![CDATA[media_id]]></MediaId>
	// </Image>
	// </xml>

	@Override
	public String generatorXml() {

		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("UTF-8");
		Element root = document.addElement("xml");

		super.addElements(root);

		Element ImageElement = root.addElement(IMessageConsts.TAG_IMAGE);
		Element mediaIdElement = ImageElement
				.addElement(IMessageConsts.TAG_MEDIAID);
		mediaIdElement.setText(getMediaId());

		String result = document.getRootElement().asXML();
		return result;
	}

}
