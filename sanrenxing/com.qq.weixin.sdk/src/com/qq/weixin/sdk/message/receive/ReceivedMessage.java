package com.qq.weixin.sdk.message.receive;

import org.dom4j.Element;

import com.qq.weixin.sdk.consts.IMessageConsts;

public class ReceivedMessage {

	protected String msgType;
	protected String fromUserName;
	protected String toUserName;
	// attention! class MessageHandlerHelper its method parseMessage
	// I do not parse msgId and createtime,because it seems to be meaningless
	protected long msgId;// 64bits for message result and event message, this
							// field is useless
	protected int createTime;// 32bits the time is Unix timestamp

	public ReceivedMessage(Element root) {

		this.msgType = root.element(IMessageConsts.TAG_MSGTYPE).getText();
		this.fromUserName = root.element(IMessageConsts.TAG_FROMUSERNAME)
				.getText();
		this.toUserName = root.element(IMessageConsts.TAG_TOUSERNAME).getText();
	}

	public ReceivedMessage() {
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

}
