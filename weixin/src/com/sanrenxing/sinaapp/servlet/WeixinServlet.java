package com.sanrenxing.sinaapp.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanrenxing.sinaapp.service.WeiXinService;
import com.sanrenxing.sinaapp.util.WeixinUtils;

/**
 * 类说明
 * 
 * @author 程辉
 * @version V1.0 创建时间：2012-12-18 上午8:44:53
 */
public class WeixinServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String TOKEN = "sanrenxing";
	private static Logger log = Logger.getLogger(WeixinServlet.class
			.getSimpleName());

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.log(Level.INFO,request.getRemoteAddr());
		
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		
		PrintWriter out = response.getWriter();
		String echostr = request.getParameter("echostr");
		System.err.println(echostr);
		out.write(echostr);
		log.info(echostr);
		log.info(signature);
		out.flush();
//		
//		if (WeixinUtils.checkSignature(TOKEN,signature, timestamp, nonce)) {
//			out.print(request.getParameter("echostr"));
//		} else {
//			out.print("error");
//		}
		out.close();
		out = null;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.获取到请求的消息xml
		InputStream is = request.getInputStream();
		int size = request.getContentLength();
		byte[] buffer = new byte[size];
		byte[] xmldataByte = new byte[size];
		int count = 0;
		int rbyte = 0;
		while (count < size) {
			rbyte = is.read(buffer);
			for (int i = 0; i < rbyte; i++) {
				xmldataByte[count + i] = buffer[i];
			}
			count += rbyte;
		}
		is.close();
		String xmlData = new String(xmldataByte, "UTF-8");

		// 2.得到自动回复的消息xml
		WeiXinService service = new WeiXinService();
		String responseXml = service.execute(xmlData);

		// 3.把回复xml写给微信服务器
		if (null != responseXml) {
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(responseXml);
			writer.flush();
			writer.close();
		}
	}

	

}
