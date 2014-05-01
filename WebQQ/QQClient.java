package WebQQ;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;


public class QQClient {

	private int qq = -1;
	private String pwd = null;
	
	private int clientid = 66933334;//����������д
	private String psessionid = "";
	
	private String ptwebqq;
	private String vfwebqq;
	
	private String skey;
	
	private String refer = "http://web2-b.qq.com/proxy.html";
	
	private String cookie = "";
	//��ȡ��Ϣ�߳�
	private boolean isrun = false;
	private Thread poolThread =new PollThread();
	public Thread getPoolThread() {
		return poolThread;
	}
	/**
	 * ��¼��־
	 */
	private void log(String msg){
		System.out.println(new Date().toLocaleString()+":"+msg);
	}
	
	
	public QQClient(int qq, String pwd) { 
		this.qq = qq;
		this.pwd = pwd;
		try {
			boolean rs = checkAndLogin();
			if(rs){ 
				isrun = true;
				poolThread.start();//��ʼѭ������
				log("�����ɹ�");
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	/*****************�����ķֽ���*****************/
	//����
	public static void main(String[] args) throws Exception{
		QQClient q = new QQClient(1506214893, "yww123321"); 
		q.getPoolThread().join();
	}
	
	/*****************�����ķֽ���*****************/
	/**
	 * ��toQQ����һ��msg��Ϣ��ǰ����toQQ����ĺ��ѣ�Ҫ��Ȼ���ղ���
	 */
	public boolean sendMsg(int toQQ, String message){
		try {
			JSONObject json = new JSONObject();
			json.put("to", toQQ);//Ҫ���͵���
			json.put("face", 0);
			
			JSONArray msg = new JSONArray();
			msg.add(message);
			JSONArray font = new JSONArray();
			font.add("font");
			
			JSONObject font1 = new JSONObject().put("name", "����").put("size", "10");
			
			JSONArray style = new JSONArray();
			style.add(0);
			style.add(0);
			style.add(0);		
			font1.put("style", style);
			font1.put("color", "000000");
			
			font.add(font1);	 
			msg.add(font);
			
			json.put("content", msg.toString());
			json.put("msg_id", new Random().nextInt(10000000));
			json.put("clientid", clientid);
			json.put("psessionid", psessionid);//��Ҫ������ܷ���
			String sendMsgUrl = "http://web2-b.qq.com/channel/send_msg";
			String content = json.toString();
			 
			content = URLEncoder.encode(content);//��Ҫ��Ҫ����
			content ="r="+content;
			//����
			String res = postUrl(sendMsgUrl, content);
			//�������⣬���Ƿ��ؽ����{"retcode":0,"result":"ok"}
			JSONObject rh = new JSONObject(res);
			if("ok".equals(rh.getString("result"))){
				return true;
			} 
		} catch (JSONException e) { 
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * ��鲢�ҵ�½
	 */
	private boolean checkAndLogin() throws Exception{
		if(qq == -1 || pwd == null)
			throw new IllegalArgumentException("qq�����벻��Ϊ��");
		String checkIdUrl = "http://ptlogin2.qq.com/check?appid=1003903&uin="+qq;
		String res = getUrl(checkIdUrl);
		//ptui_checkVC('0','!ZLE');��������Ͳ���Ҫ��ȡ��֤���ˡ���֤�����!ZLE 
		//ptui_checkVC('1','95ab7db15e5ab17f50f25d33598259e83ccc098c4af2f8a4');������ַ�������Ҫʹ����
		Pattern p = Pattern. compile("\\,\\'([!\\w]+)\\'");
		Matcher m = p. matcher(res);
		String checkType = "";
		if(m.find()){
			checkType = m.group(1); 
		}
		String check = ""; 
		if(!checkType.startsWith("!")){
			//��Ҫ������֤��
			String getCheckImageUrl = "http://captcha.qq.com/getimage?aid=1003903&uin="+qq+"&vc_type="+checkType;
			String file = readCheckImage(getCheckImageUrl);
			log("���"+file+"�������������������е��ַ�����Ȼ��س���");
			InputStreamReader ins = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(ins);
			check = br.readLine();	
		}else{
			//����Ҫ������֤��
			check = checkType;
		}
		
		//��ʼ��½
		String loginUrl = "http://ptlogin2.qq.com/login?u="+qq+"&" +
				"p=" +mdP(pwd, check)+
				"&verifycode="+check+"&remember_uin=1&aid=1003903" +
				"&u1=http%3A%2F%2Fweb2.qq.com%2Floginproxy.html%3Fstrong%3Dtrue" +
				"&h=1&ptredirect=0&ptlang=2052&from_ui=1&pttype=1&dumy=&fp=loginerroralert";
		res = getUrl(loginUrl);
//		ptuiCB('0','0','http://web2.qq.com/loginproxy.html?strong=true','0','��¼�ɹ���');
//		ptuiCB('4','0','','0','���������֤�����������ԡ�');
	 
		p = Pattern.compile("��¼�ɹ���");//��ȡ���һ���ַ����������ǲ��� ��¼�ɹ���
		m = p. matcher(res);
		if(m.find()){
			log("��½�ɹ�"); 
		}else{
			//��½ʧ��
			log(checkType);
			return false;
		}
		//��cookie����ȡptwebqq,skey
		p = Pattern.compile("ptwebqq=(\\w+);");
		m = p.matcher(cookie);
		if(m.find()){
			ptwebqq = m.group(1);
		}
		p = Pattern.compile("skey=(@\\w+);");
		m = p.matcher(cookie);
		if(m.find()){
			skey = m.group(1);
		}
		log("ptwebqq="+ptwebqq+",skey="+skey);
		
		//�ٴε�½��ֻ����ε�½������������½qq�����ʱ�������qq�Ѿ���½��������qq�����ߣ����Ҵ˴ε�½�������ߡ�
		String channelLoginUrl = "http://web2-b.qq.com/channel/login";
		String content = "{\"status\":\"\",\"ptwebqq\":\""+ptwebqq+"\",\"passwd_sig\":\"\",\"clientid\":\""+clientid+"\"}";
		content = URLEncoder.encode(content);//urlencode 
		content = "r="+content;//post������
		res = postUrl(channelLoginUrl, content);//post
		//��ε�½�����ϲ��ᷢ��ʲô����
		//������ȡ����Ҫ��2������psessionid ,vwebqq��ͨ�ò���������ʽ,��Ȼ����Ǹ�json
		p = Pattern.compile("\"vfwebqq\":\"(\\w+)\"");
		m = p.matcher(res);
		if(m.find()){
			vfwebqq = m.group(1);
		}
		p = Pattern.compile("\"psessionid\":\"(\\w+)\"");
		m = p.matcher(res);
		if(m.find()){
			psessionid = m.group(1);
		}
		log("vwebqq="+vfwebqq+","+"psessionid="+psessionid);
		//���ˣ���½��������ˣ�������Ե��÷���qq��Ϣ�Ƚӿ���		
		 return true;
	}
	

	
	
	/**
	 * ����tx��js��������Կ
	 */
	public  String mdP(String p, String code){
		try {
			ScriptEngineManager m = new ScriptEngineManager();
			ScriptEngine se = m.getEngineByName("javascript");
			se.eval(new FileReader(new File("comm.js")));
			Object t = se.eval("md5(md5_3(\""+p+"\")+\""+code.toUpperCase()+"\");");
			return t.toString();
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	/**
	 * POSTһ��url,contents�����������
	 */
	private  String postUrl(String url, String contents){
		try{ 
			System.out.println("post>>>"+url);
			 
			URL serverUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection(); 
	        conn.setRequestMethod("POST");//"POST" ,"GET" 
	       
	        if(refer != null){
	        	conn.addRequestProperty("Referer", refer);
	        }
	        conn.addRequestProperty("Cookie", cookie);
	        conn.addRequestProperty("Accept-Charset", "UTF-8;");//GB2312,
	        conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
	        conn.setDoOutput(true); 
	        conn.connect();
	        
	        conn.getOutputStream().write(contents.getBytes());
	        
	        if(conn.getHeaderFields().get("Set-Cookie") != null){
		        for(String s:conn.getHeaderFields().get("Set-Cookie")){
		        	cookie += s;
		        }
	        }
	        
	        InputStream ins =  conn.getInputStream();
	        
	        String charset = "UTF-8"; 
	        InputStreamReader inr = new InputStreamReader(ins, charset);
	        BufferedReader bfr = new BufferedReader(inr);
	       
	        String line = "";
	        StringBuffer res = new StringBuffer(); 
	        do{
	    	    res.append(line);
	    	    line = bfr.readLine();
	    	   //System.out.println(line);
	        }while(line != null);
	      
	        System.out.println(">>>==="+res);
	        
	        return res.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * GET һ��url
	 */
	private  String getUrl(String url){
		try{ 
			System.out.println("get>>>"+url);
			 
			URL serverUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection(); 
	        conn.setRequestMethod("GET");//"POST" ,"GET"
	       // conn.setDoOutput(true); 
	        if(refer != null){
	        	conn.addRequestProperty("Referer", refer);
	        }
	        conn.addRequestProperty("Cookie", cookie);
	        conn.addRequestProperty("Accept-Charset", "UTF-8;");//GB2312,
	        conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
	        conn.connect();
	       
	        if(conn.getHeaderFields().get("Set-Cookie") != null){
		        for(String s:conn.getHeaderFields().get("Set-Cookie")){
		        	cookie += s;
		        }
	        }
	        InputStream ins =  conn.getInputStream();
	        
	        String charset = "UTF-8"; 
	        InputStreamReader inr = new InputStreamReader(ins, charset);
	        BufferedReader bfr = new BufferedReader(inr);
	       
	        String line = "";
	        StringBuffer res = new StringBuffer(); 
	        do{
	    	    res.append(line);
	    	    line = bfr.readLine();
	    	   //System.out.println(line);
	        }while(line != null);
	      
	        System.out.println(">>>==="+res);
	        
	        return res.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ��ȡ��֤�롣������֤���ļ������·��
	 */
	private String readCheckImage(String url){
		try{ 
			System.out.println("get>>>"+url);
		 
			URL serverUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection(); 
	        conn.setRequestMethod("GET");//"POST" ,"GET" 
	        
	        conn.addRequestProperty("Accept-Charset", "UTF-8;");//GB2312,
	        conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
	        conn.connect();
	        //���ص�cookie
	        if(conn.getHeaderFields().get("Set-Cookie") != null)
		        for(String s:conn.getHeaderFields().get("Set-Cookie")){
		        	cookie += s;
		        }
	        
	        InputStream ins =  conn.getInputStream();
	        
	        BufferedImage bi = ImageIO.read(ins);
	        File f =new File("qqimg.jpg");
	        ImageIO.write(bi, "jpg", f);
	        
	        return f.getAbsolutePath();
		}catch(Exception e){
			e.printStackTrace(); 
		} 
		return null;
	}
	
	/**
	 * ����qq��Ϣ�ǻص��˺���
	 */
	public void receiveMsg(String message, int fromQQ){
		log("qq:"+fromQQ+"˵:"+message);
		//test
		sendMsg(fromQQ, "Ȼ���أ�");
	}
	
	/**
	 * ͨ��pollһֱ�ȴ���������Ӧ�������յ���Ϣ�����������߰���������Ѱ�֮��ĸ�����Ϣ����ͨ���˽ӿڷ��أ��ڻ�����ݺ�Ӧ�ü���poll��ȡ�¸�����
	 * http://web2-b.qq.com/channel/poll
	 */
	class PollThread extends Thread{
		
		private String pollUrl = "http://web2-b.qq.com/channel/poll";
		@Override
		public void run() { 
			String url = pollUrl+ "?clientid="+clientid+"&psessionid="+psessionid; 
			
			try {
				while(isrun){
					//�߳�һֱ�ȴ�֪���������з�������
					String res = getUrl(url);
					
					JSONObject retJ = new JSONObject(res);
					if(retJ.getInt("retcode") == 0){
						JSONArray result = retJ.getJSONArray("result");
						String poll_type = result.getJSONObject(0).getString("poll_type");
						if("message".equals(poll_type)){
							//˵�����˷�qq��Ϣ���ң�							
							String raw_content = result.getJSONObject(0).getJSONObject("value").get("raw_content").toString(); 
							int from_uin = result.getJSONObject(0).getJSONObject("value").getInt("from_uin");
							log("�յ����ԣ�"+from_uin+":"+raw_content);
							//֪ͨ�ͻ����յ���
							receiveMsg(raw_content, from_uin);
						}
						//system_message ��ϵͳ��Ϣ
					}
				}
			} catch (JSONException e) { 
				e.printStackTrace();
			}
		}
		
	}

	
}