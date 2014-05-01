package WebQQ;

import java.util.Date;

import WebQQ.util.FindValueAccordingKey;
import WebQQ.util.HttpClient;

public class UserInfo {
	private static String userInfoURL = "http://s.web2.qq.com/api/get_friend_info2?";
	
	public static String getUserInfo(String QQ, String loginAgainResult){
	long time = new Date().getTime();
	String url = userInfoURL + "tuin="+ QQ + "&verifysession=&code=&vfwebqq="+ FindValueAccordingKey.findValue("vfwebqq", loginAgainResult)+"&t=" + time + "\"";
		
		String result = HttpClient.get(url);
		return result ;
	}

}
