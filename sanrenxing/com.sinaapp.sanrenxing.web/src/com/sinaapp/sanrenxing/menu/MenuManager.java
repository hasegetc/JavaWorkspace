package com.sinaapp.sanrenxing.menu;

import com.sinaapp.sanrenxing.consts.ISanrenxingConsts;
import com.sinaapp.sanrenxing.util.AccessTokenUtil;
import com.sinaapp.sanrenxing.util.HttpsUtil;

public class MenuManager {

	private static String CUSTOM_MENU_URL = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	public static void main(String[] args) {

		String token = AccessTokenUtil.getAccessToken(ISanrenxingConsts.APPID,
				ISanrenxingConsts.APPSECRET);

		CustomMenu menu = new CustomMenu();
		
		String url = CUSTOM_MENU_URL.replace("ACCESS_TOKEN", token);
		HttpsUtil.post(url, menu.getMenuJson());
	
	}
}