package com.sinaapp.sanrenxing.consts;

public interface IMenuConsts {

	String REGISTER_LESSON = "http://sanrenxing.sinaapp.com/jsp/registerLesson.jsp";
	String UNREGISTER_LESSON = "http://sanrenxing.sinaapp.com/jsp/unregisterLesson.jsp";
	// 申请辅导
	String REQUEST_LESSON = "http://sanrenxing.sinaapp.com/jsp/requestLesson.jsp";

	// key click类型必须 菜单KEY值，用于消息接口推送，不超过128字节
	// 结束辅导
	String STOP_SERVICE = "stop_service";
	// 使用前一课程
	String USE_PREV_LESSON = "use_prev_lesson";
	// 解答问题
	String START_SERVICE = "start_service";

}
