package com.sinaapp.sanrenxing.job;

import org.apache.log4j.Logger;

import com.qq.weixin.sdk.consts.IMessageConsts;
import com.qq.weixin.sdk.message.receive.ReceivedMessage;
import com.qq.weixin.sdk.message.receive.commenmessage.ReceivedMultimediaMessage;
import com.qq.weixin.sdk.message.receive.commenmessage.ReceivedVoiceMessage;
import com.qq.weixin.sdk.message.response.customservice.ServiceTextMessage;
import com.qq.weixin.sdk.message.response.passiveresponse.ResponcePictureMessage;
import com.qq.weixin.sdk.message.response.passiveresponse.ResponceVoiceMessage;
import com.sinaapp.sanrenxing.util.WeixinUtil;

//https://mp.weixin.qq.com/cgi-bin/getimgdata?token=1330201807&msgid=200043156&mode=large&source=&fileId=0&ow=1537187974
public class SanrenxingJob {

	Logger logger = Logger.getLogger(SanrenxingJob.class);

	public void handleJob(ReceivedMessage receivedMessage) {

		String response = null;
		if (receivedMessage.getMsgType().equalsIgnoreCase(
				IMessageConsts.MESSAGE_IMAGE)) {
			response = handlePicMessage(receivedMessage);
		} else if (receivedMessage.getMsgType().equalsIgnoreCase(
				IMessageConsts.MESSAGE_VOICE)) {
			response = handVoiceMessage(receivedMessage);
		} else {
			response = handTextMessage(receivedMessage);
		}
		post(response);
	}

	private void post(String response) {
		WeixinUtil.post(response);
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
		logger.error("to:" + receivedMessage.getToUserName());
		logger.error("MediaID:" + mediaId);
		responseMessage.setMsgType(IMessageConsts.MESSAGE_IMAGE);
		responseMessage
				.setCreateTime((int) (System.currentTimeMillis() / 1000));
		return responseMessage.generatorXml();

	}

	private String handTextMessage(ReceivedMessage receivedMessage) {
		ServiceTextMessage message = new ServiceTextMessage();

		message.setMsgtype(IMessageConsts.MESSAGE_TEXT);
		message.setTouser(receivedMessage.getFromUserName());
		message.getText().setContent("I am LiuYuchao,Good Morning");

		return message.generatorJson();
	}
	//
	// private String handTextMessage(ReceivedMessage receivedMessage) {
	// ResponseTextMessage responseMessage = new ResponseTextMessage();
	// responseMessage.setFromUserName(receivedMessage.getToUserName());
	// responseMessage.setToUserName(receivedMessage.getFromUserName());
	// responseMessage.setContent("I am LiuYuchao,Good Morning");
	// responseMessage.setMsgType(IMessageConsts.MESSAGE_TEXT);
	// responseMessage
	// .setCreateTime((int) (System.currentTimeMillis() / 1000));
	// return responseMessage.generatorXml();
	// }

}
