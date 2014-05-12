package com.sinaapp.sanrenxing.consts;

public interface IMenuConsts {

	String REDIRECT_TEMPLATE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=xx#wechat_redirect";

	String REGISTER_LESSON = "http://sanrenxing.sinaapp.com/jsp/registerLesson.jsp";
	String UNREGISTER_LESSON = "http://sanrenxing.sinaapp.com/jsp/unregister!getUseRegisterLesson.action";
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
