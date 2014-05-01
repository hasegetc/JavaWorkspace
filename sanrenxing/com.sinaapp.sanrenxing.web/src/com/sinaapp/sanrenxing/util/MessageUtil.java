package com.sinaapp.sanrenxing.util;

import org.apache.log4j.Logger;
import org.dom4j.Element;

import com.qq.weixin.sdk.consts.IMessageConsts;
import com.qq.weixin.sdk.message.receive.ReceivedMessage;
import com.qq.weixin.sdk.message.receive.commenmessage.ReceivedPictureMessage;
import com.qq.weixin.sdk.message.receive.commenmessage.ReceivedTextMessage;
import com.qq.weixin.sdk.message.receive.commenmessage.ReceivedVoiceMessage;
import com.qq.weixin.sdk.message.receive.event.MeunEvent;

public class MessageUtil {

	static Logger logger = Logger.getLogger(MessageUtil.class);

	public static ReceivedMessage getMessage(Element root) {

		String type = root.element(IMessageConsts.TAG_MSGTYPE).getText();

		ReceivedMessage receivedMessage = null;
		if (type.equalsIgnoreCase(IMessageConsts.MESSAGE_TEXT)) {

			receivedMessage = new ReceivedTextMessage(root);

		} else if (type.equalsIgnoreCase(IMessageConsts.MESSAGE_EVENT)) {
			
			receivedMessage = new MeunEvent(root);

		} else if (type.equalsIgnoreCase(IMessageConsts.MESSAGE_IMAGE)) {

			receivedMessage = new ReceivedPictureMessage(root);

		} else if (type.equalsIgnoreCase(IMessageConsts.MESSAGE_LINK)) {

		} else if (type.equalsIgnoreCase(IMessageConsts.MESSAGE_LOCATION)) {

		} else if (type.equalsIgnoreCase(IMessageConsts.MESSAGE_VOICE)) {

			receivedMessage = new ReceivedVoiceMessage(root);
			
		}

		return receivedMessage;
	}

}
