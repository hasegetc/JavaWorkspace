package com.sinaapp.sanrenxing.job;

import org.apache.log4j.Logger;

import com.qq.weixin.sdk.consts.IMessageConsts;
import com.qq.weixin.sdk.message.receive.ReceivedMessage;
import com.qq.weixin.sdk.message.receive.commenmessage.ReceivedMultimediaMessage;
import com.qq.weixin.sdk.message.receive.commenmessage.ReceivedTextMessage;
import com.qq.weixin.sdk.message.receive.commenmessage.ReceivedVoiceMessage;
import com.qq.weixin.sdk.message.response.passiveresponse.ResponcePictureMessage;
import com.qq.weixin.sdk.message.response.passiveresponse.ResponceVoiceMessage;
import com.qq.weixin.sdk.message.response.passiveresponse.ResponseTextMessage;
import com.sinaapp.sanrenxing.util.JokeUtil;
import com.sinaapp.sanrenxing.util.SimsimiUtil;

//https://mp.weixin.qq.com/cgi-bin/getimgdata?token=1330201807&msgid=200043156&mode=large&source=&fileId=0&ow=1537187974
public class BlueMoonJob {

	Logger logger = Logger.getLogger(BlueMoonJob.class);

	public String handleJob(ReceivedMessage receivedMessage) {
		if (receivedMessage.getMsgType().equalsIgnoreCase(
				IMessageConsts.MESSAGE_TEXT)) {
			return handTextMessage(receivedMessage);
		} else if (receivedMessage.getMsgType().equalsIgnoreCase(
				IMessageConsts.MESSAGE_IMAGE)) {
			return handlePicMessage(receivedMessage);
		} else if (receivedMessage.getMsgType().equalsIgnoreCase(
				IMessageConsts.MESSAGE_VOICE)) {
			return handVoiceMessage(receivedMessage);
		} else {
			return handSorryMessage(receivedMessage);
		}
	}

	private String handSorryMessage(ReceivedMessage receivedMessage) {
		ResponseTextMessage responseMessage = new ResponseTextMessage();
		responseMessage.setFromUserName(receivedMessage.getToUserName());
		responseMessage.setToUserName(receivedMessage.getFromUserName());
		responseMessage.setContent(Messages.getString("BlueMoonJob.Wrong")); //$NON-NLS-1$
		responseMessage.setMsgType(IMessageConsts.MESSAGE_TEXT);
		responseMessage
				.setCreateTime((int) (System.currentTimeMillis() / 1000));
		return responseMessage.generatorXml();
	}

	private String handVoiceMessage(ReceivedMessage receivedMessage) {

		ResponceVoiceMessage responseMessage = new ResponceVoiceMessage();
		responseMessage.setFromUserName(receivedMessage.getToUserName());
		responseMessage.setToUserName(receivedMessage.getFromUserName());
		responseMessage.setMediaId(((ReceivedVoiceMessage) receivedMessage)
				.getMediaId());
		responseMessage.setMsgType(IMessageConsts.MESSAGE_VOICE);
		responseMessage
				.setCreateTime((int) (System.currentTimeMillis() / 1000));
		return responseMessage.generatorXml();

	}

	private String handlePicMessage(ReceivedMessage receivedMessage) {
		//
		// ResponseTextMessage responseMessage = new ResponseTextMessage();
		// responseMessage.setFromUserName(receivedMessage.getToUserName());
		// responseMessage.setToUserName(receivedMessage.getFromUserName());
		// responseMessage.setContent(((ReceivedPictureMessage) receivedMessage)
		// .getPicUrl());
		// responseMessage.setMsgType(IMessageConsts.MESSAGE_TEXT);
		// responseMessage
		// .setCreateTime((int) (System.currentTimeMillis() / 1000));
		// return responseMessage.generatorXml();

		ResponcePictureMessage responseMessage = new ResponcePictureMessage();
		responseMessage.setFromUserName(receivedMessage.getToUserName());
		responseMessage.setToUserName(receivedMessage.getFromUserName());
		String mediaId = ((ReceivedMultimediaMessage) receivedMessage)
				.getMediaId();
		responseMessage.setMediaId(mediaId);
		responseMessage.setMsgType(IMessageConsts.MESSAGE_IMAGE);
		responseMessage
				.setCreateTime((int) (System.currentTimeMillis() / 1000));
		return responseMessage.generatorXml();

	}

	private String handTextMessage(ReceivedMessage receivedMessage) {

		String content = ((ReceivedTextMessage) receivedMessage).getContent();
		ResponseTextMessage responseMessage = new ResponseTextMessage();
		responseMessage.setFromUserName(receivedMessage.getToUserName());
		String fromUserName = receivedMessage.getFromUserName();
		responseMessage.setToUserName(fromUserName);
		String response = null;
		if (needJoke(content)) {
			response = JokeUtil.randomSelectOneJoke(fromUserName);
		} else {
			response = SimsimiUtil.getResponse(content);
		}

		responseMessage.setContent(response);

		responseMessage.setMsgType(IMessageConsts.MESSAGE_TEXT);
		responseMessage
				.setCreateTime((int) (System.currentTimeMillis() / 1000));
		return responseMessage.generatorXml();
	}

	private boolean needJoke(String content) {

		if (content.contains(Messages.getString("BlueMoonJob.NotGlad")) || content.contains(Messages.getString("BlueMoonJob.NotHappy")) //$NON-NLS-1$ //$NON-NLS-2$
				|| content.equalsIgnoreCase("jgxh") || content.equals(Messages.getString("BlueMoonJob.MyLove")) //$NON-NLS-1$ //$NON-NLS-2$
				|| content.equalsIgnoreCase("zz")) { //$NON-NLS-1$
			return true;
		}
		return false;
	}
}
