package com.qq.weixin.sdk.message.response.passiveresponse;

import org.dom4j.Element;

import com.qq.weixin.sdk.consts.IMessageConsts;

public abstract class AbstractResponseMessage {

	private String msgType;
	private String fromUserName;
	private String toUserName;

	private int createTime;// 32bits the time is Unix timestamp

	public abstract String generatorXml();

	protected void addElements(Element root) {
		Element e = root.addElement(IMessageConsts.TAG_TOUSERNAME);
		e.setText(getToUserName());

		e = root.addElement(IMessageConsts.TAG_FROMUSERNAME);
		e.setText(getFromUserName());

		e = root.addElement(IMessageConsts.TAG_CREATETIME);
		e.setText(Integer.toString(getCreateTime()));

		e = root.addElement(IMessageConsts.TAG_MSGTYPE);
		e.setText(getMsgType());

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

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

}
