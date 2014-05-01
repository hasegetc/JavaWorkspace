package com.sinaapp.sanrenxing.messagehandle;

import com.qq.weixin.sdk.consts.IMessageConsts;
import com.qq.weixin.sdk.message.receive.event.MeunEvent;
import com.sinaapp.sanrenxing.consts.IMenuConsts;

public class MenuEventHandle {

	public static void handle(MeunEvent receivedMessage) {

		if (!receivedMessage.getEvent().equalsIgnoreCase(
				IMessageConsts.EVENT_VIEW)) {

			/**
			 * // key click类型必须 菜单KEY值，用于消息接口推送，不超过128字节 // 结束辅导 String
			 * STOP_SERVICE = "stop_service"; // 申请辅导 String APPLY_SERVICE =
			 * "apply_service"; // 使用前一辅导 String USE_PREV_SERVICE =
			 * "use_prev_service";
			 */
			String eventKey = receivedMessage.getEventKey();

			if (eventKey.equalsIgnoreCase(IMenuConsts.STOP_SERVICE)) {

			} else if (eventKey.equalsIgnoreCase(IMenuConsts.USE_PREV_LESSON)) {

			}

		}

	}

}
