package com.sinaapp.sanrenxing.menu;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.sinaapp.sanrenxing.bean.ClickMenu;
import com.sinaapp.sanrenxing.bean.CommonMenu;
import com.sinaapp.sanrenxing.bean.ComplexMenu;
import com.sinaapp.sanrenxing.bean.Menu;
import com.sinaapp.sanrenxing.bean.ViewMenu;
import com.sinaapp.sanrenxing.consts.IMenuConsts;
import com.sinaapp.sanrenxing.consts.ISanrenxingConsts;

/**
 * { "button":[ { "type":"click", "name":"今日歌曲", "key":"V1001_TODAY_MUSIC" }, {
 * "type":"click", "name":"歌手简介", "key":"V1001_TODAY_SINGER" }, { "name":"菜单",
 * "sub_button":[ { "type":"view", "name":"搜索", "url":"http://www.soso.com/" },
 * { "type":"view", "name":"视频", "url":"http://v.qq.com/" }, { "type":"click",
 * "name":"赞一下我们", "key":"V1001_GOOD" }] }] }
 * 
 * @author LiuYuchao
 * 
 */
public class CustomMenu {

	private Map<String, List<Menu>> map = new HashMap<String, List<Menu>>();
	private List<Menu> button = new ArrayList<Menu>(3);

	protected ObjectMapper objectMapper = null;

	public CustomMenu() {
		objectMapper = new ObjectMapper();
		initCustomMenu();
	}

	private void initCustomMenu() {

		/**
		 * 第一个菜单
		 */
		Menu menu = new ClickMenu();
		menu.setName("前一辅导");
		((ClickMenu) menu).setType(CommonMenu.CLICK);
		((ClickMenu) menu).setKey(IMenuConsts.USE_PREV_LESSON);
		button.add(menu);

		/**
		 * 第二个菜单
		 */
		menu = new ClickMenu();
		menu.setName("结束辅导");
		((ClickMenu) menu).setType(CommonMenu.CLICK);
		((ClickMenu) menu).setKey(IMenuConsts.STOP_SERVICE);
		button.add(menu);

		// 第三個菜单
		ComplexMenu complexmenu = new ComplexMenu();
		complexmenu.setName("更多");

		menu = new ViewMenu();
		menu.setName("申请辅导");
		((ViewMenu) menu).setType(CommonMenu.VIEW);
		((ViewMenu) menu).setUrl(getUrl(IMenuConsts.REQUEST_LESSON));
		complexmenu.getSub_button().add(menu);

		menu = new ClickMenu();
		menu.setName("解答问题");
		((ClickMenu) menu).setType(CommonMenu.CLICK);
		((ClickMenu) menu).setKey(IMenuConsts.START_SERVICE);
		complexmenu.getSub_button().add(menu);

		menu = new ViewMenu();
		menu.setName("注册服务");
		((ViewMenu) menu).setType(CommonMenu.VIEW);
		((ViewMenu) menu).setUrl(getUrl(IMenuConsts.REGISTER_LESSON));
		complexmenu.getSub_button().add(menu);

		menu = new ViewMenu();
		((ViewMenu) menu).setType(CommonMenu.VIEW);
		((ViewMenu) menu).setUrl(getUrl(IMenuConsts.UNREGISTER_LESSON));
		menu.setName("注消服务");
		complexmenu.getSub_button().add(menu);

		button.add(complexmenu);

		map.put("button", button);
	}

	public String getMenuJson() {

		return getMenuJson(map);

	}

	private String getUrl(String name) {
		try {
			return IMenuConsts.REDIRECT_TEMPLATE.replace("APPID",
					ISanrenxingConsts.APPID).replace("REDIRECT_URI",
					URLEncoder.encode(name, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String getMenuJson(Object v) {
		String result = null;
		try {
			result = objectMapper.writeValueAsString(v);
			// 默认只有一个菜单即为一级菜单

			System.err.println(result);

		} catch (Exception e) {
			Logger logger = Logger.getLogger(CustomMenu.class);
			logger.error(e);
		}
		return result;
	}

	public static void main(String[] args) {

		CustomMenu v = new CustomMenu();
		v.getMenuJson();
	}
}
