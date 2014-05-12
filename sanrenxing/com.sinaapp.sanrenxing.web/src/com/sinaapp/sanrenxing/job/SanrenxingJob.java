package com.sinaapp.sanrenxing.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.qq.weixin.sdk.consts.IMessageConsts;
import com.qq.weixin.sdk.message.receive.ReceivedMessage;
import com.qq.weixin.sdk.message.receive.event.MeunEvent;
import com.sinaapp.sanrenxing.bean.RegisterLesson;
import com.sinaapp.sanrenxing.consts.IMenuConsts;
import com.sinaapp.sanrenxing.util.ForwardUtil;
import com.sinaapp.sanrenxing.util.SanrenxingUtil;

//https://mp.weixin.qq.com/cgi-bin/getimgdata?token=1330201807&msgid=200043156&mode=large&source=&fileId=0&ow=1537187974
public class SanrenxingJob {

	Logger logger = Logger.getLogger(SanrenxingJob.class);

	static public Map<String, String> teacherMap = new HashMap<String, String>();

	public void handleJob(ReceivedMessage receivedMessage) {

		logger.error(receivedMessage.getMsgType());
		// 处理event事件
		if (receivedMessage.getMsgType().equalsIgnoreCase(
				IMessageConsts.MESSAGE_EVENT)) {

			// 点击菜单跳转链接时的事件推送,不处理
			if (((MeunEvent) receivedMessage).getEvent().equalsIgnoreCase(
					IMessageConsts.EVENT_VIEW)) {
				return;
			}
			if (((MeunEvent) receivedMessage).getEventKey().equalsIgnoreCase(
					IMenuConsts.STOP_SERVICE)) {
				/**
				 * 将课程状态设为0 将relation状态设为0
				 */
				SanrenxingUtil.stopService(receivedMessage);
			} else if (((MeunEvent) receivedMessage).getEventKey()
					.equalsIgnoreCase(IMenuConsts.USE_PREV_LESSON)) {
				SanrenxingUtil.usePrevLesson(receivedMessage);

			} else if (((MeunEvent) receivedMessage).getEventKey()
					.equalsIgnoreCase(IMenuConsts.START_SERVICE)) {
				// 要分辨老師和學生id,老師ID可能下發好多次，這尼瑪怎么搞
				SanrenxingUtil.startService(receivedMessage);
			}

			return;
		}

		/**
		 * 先建立转发关系，再转发消息
		 */
		String fromUserName = receivedMessage.getFromUserName();
		String toUserName = getToUserName(fromUserName);
		String response = null;
		/**
		 * 为null时默认为学生 建立轉發關係
		 */
		if (toUserName == null) {
			// 如果是学生
			// 先检查是否已经申请课程
			RegisterLesson lesson = getLesson(fromUserName);

			if (lesson == null) {
				// 未注册课程，提示用户注册，并且再重新发送一次信息
				response = "您还未申请辅导课程，请你先申请辅导课程再重新发送问题";
				boolean result = ForwardUtil.forwardNotice(response,
						receivedMessage);
				if (!result) {
					logger.error("forward message: " + response + "error!");
				}
			} else {
				/*
				 * 已经注册课程，由于toUserName为null，说明没有老师，转发所有老师
				 */
				forwardTeachers(receivedMessage, lesson);
			}

		} else {
			/**
			 * 转发所有消息
			 */
			ForwardUtil.forwardTo(toUserName, receivedMessage);
		}

	}

	private void forwardTeachers(ReceivedMessage receivedMessage,
			RegisterLesson lesson) {
		String fromUserName = receivedMessage.getFromUserName();

		List<String> teachers = getTeachers(lesson, fromUserName);
		String notice = "有同学提如下问题，请点“解答问题”菜单抢答[微笑]";
		for (String v : teachers) {
			ForwardUtil.forwardNotice(notice, v);
			ForwardUtil.forwardTo(v, receivedMessage);
			addTeacherMap(v, fromUserName);
		}

	}

	private void addTeacherMap(String toUserName, String fromUserName) {
		teacherMap.put(toUserName, fromUserName);
	}

	/**
	 * 不能转给自己，不能转给已经有课程的老师，不能转给申请课程的人
	 * 
	 * @param fromUserName
	 */
	private List<String> getTeachers(RegisterLesson lesson, String fromUserName) {
		List<String> result = new ArrayList<String>();

		String classValue = lesson.getClassValue();
		String lessonValue = lesson.getLesson();
		List<String> teachers = SanrenxingUtil.getTeachers(classValue,
				lessonValue);

		/**
		 * 得到所有正在转发中的老师
		 */
		List<String> forwardTeachers = SanrenxingUtil.getForwardTeachers();
		
		/**
		 * 所有申请辅导的人
		 */
		List<String> requestUsers = SanrenxingUtil.getRequestUser();

		int i = 0;
		for (String v : teachers) {

			if (!v.equalsIgnoreCase(fromUserName)
					&& !forwardTeachers.contains(v)&& !requestUsers.contains(v)) {
				result.add(v);
				i++;
			}
			if (i > 100) {
				return result;
			}
		}
		return result;
	}

	private RegisterLesson getLesson(String userName) {

		return SanrenxingUtil.getLesson(userName);
	}

	private String getToUserName(String fromUserName) {
		// 不管是老师还是学生，转发规则应该是唯一的
		// 不存在返回null
		return SanrenxingUtil.getToUserName(fromUserName);
	}

}
