/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.52
 * Generated at: 2014-04-06 01:55:52 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class registerLesson_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\" />\r\n");
      out.write("<title>注册服务</title>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"./jQuery/jquery.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"./jQueryPlugin/jquery.form.js\"></script>\r\n");
      out.write("<link href=\"sanrenxingCSS/sanrenxing.css\" rel=\"stylesheet\"\r\n");
      out.write("\ttype=\"text/css\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tfunction success() {\r\n");
      out.write("\t\talert(\"注册成功，请继续！\");\r\n");
      out.write("\t\t$('#js_nextBtn').removeAttr(\"disabled\");\r\n");
      out.write("\t\t$('#js_nextBtn').addClass(\"btn_primary\");\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t$(document).ready(function() {\r\n");
      out.write("\t\t$('#js_addLessonBtn').click(function() {\r\n");
      out.write("\r\n");
      out.write("\t\t\tvar lesson = $('.lesson').last().clone();\r\n");
      out.write("\t\t\t$('.lesson').last().after(lesson);\r\n");
      out.write("\r\n");
      out.write("\t\t\t$('#js_delLessonBtn').attr('disabled', false);\r\n");
      out.write("\t\t\t$('#js_delLessonBtn').addClass(\"btn_primary\");\r\n");
      out.write("\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t$('#js_delLessonBtn').click(function() {\r\n");
      out.write("\r\n");
      out.write("\t\t\t$('.lesson').last().remove(); // remove the last element\r\n");
      out.write("\t\t\tvar num = $('.lesson').size();\r\n");
      out.write("\t\t\t// if only one element remains, disable the \"remove\" button\r\n");
      out.write("\t\t\tif (num == 1) {\r\n");
      out.write("\t\t\t\t$('#js_delLessonBtn').attr('disabled', true);\r\n");
      out.write("\t\t\t\t$('#js_delLessonBtn').removeClass(\"btn_primary\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t$(\"#js_nextBtn\").click(function() {\r\n");
      out.write("\t\t\t$(\"#js_nextBtn\").attr(\"disabled\", true);\r\n");
      out.write("\t\t\t$('#js_nextBtn').removeClass(\"btn_primary\");\r\n");
      out.write("\t\t\t//alert(\"注册成功，请继续！\");\r\n");
      out.write("\t\t\t$('#registerForm').ajaxForm({\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tsuccess: function() {\r\n");
      out.write("\t\t\t\t\tsuccess();\r\n");
      out.write("\t\t\t    }}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t).submit();\r\n");
      out.write("\t\t\t//return  false;不需要加  \r\n");
      out.write("\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"head\" id=\"header\">\r\n");
      out.write("\t\t<div class=\"head_box\">\r\n");
      out.write("\t\t\t<div class=\"inner wrp\">\r\n");
      out.write("\t\t\t\t<h1 class=\"logo\">\r\n");
      out.write("\t\t\t\t\t<a href=\"/\" title=\"微信公众平台\"></a>\r\n");
      out.write("\t\t\t\t</h1>\r\n");
      out.write("\t\t\t\t<div class=\"account\">\r\n");
      out.write("\t\t\t\t\t<div class=\"account_meta account_faq\" style=\"display: none;\">\r\n");
      out.write("\t\t\t\t\t\t<a class=\"account_primary_link\"\r\n");
      out.write("\t\t\t\t\t\t\thref=\"http://crm2.qq.com/page/portalpage/wpa.php?uin=40012345&amp;f=1&amp;ty=1&amp;ap=000011:400792:|m:12|f:400792:m:12\"\r\n");
      out.write("\t\t\t\t\t\t\ttarget=\"_blank\">在线客服</a>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t<div id=\"body\" class=\"body\">\r\n");
      out.write("\t\t<form id=\"registerForm\" action=\"register!registerLesson.action\">\r\n");
      out.write("\t\t\t<div id=\"class\" class=\"frm_ctl_group\">\r\n");
      out.write("\t\t\t\t<span class=\"frm_label\">年级：</span> <select name=\"class\"\r\n");
      out.write("\t\t\t\t\tclass=\"frm_input_box\">\r\n");
      out.write("\t\t\t\t\t<option value=\"senior\">高中</option>\r\n");
      out.write("\t\t\t\t\t<option value=\"junior\">初中</option>\r\n");
      out.write("\t\t\t\t\t<option value=\"primary\">小学</option>\r\n");
      out.write("\t\t\t\t</select>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"frm_ctl_group lesson\">\r\n");
      out.write("\t\t\t\t<span class=\"frm_label\">科目：</span> <select name=\"lesson\"\r\n");
      out.write("\t\t\t\t\tclass=\"frm_input_box\">\r\n");
      out.write("\t\t\t\t\t<option value=\"math\">数学</option>\r\n");
      out.write("\t\t\t\t\t<option value=\"physical\">物理</option>\r\n");
      out.write("\t\t\t\t\t<option value=\"chemistry\">化学</option>\r\n");
      out.write("\t\t\t\t\t<option value=\"english\">英语</option>\r\n");
      out.write("\t\t\t\t\t<option value=\"biological\">生物</option>\r\n");
      out.write("\t\t\t\t</select>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div class=\"clear\"></div>\r\n");
      out.write("\t\t\t<div class=\"tool_bar border with_form\">\r\n");
      out.write("\t\t\t\t<button type=\"button\" id=\"js_addLessonBtn\"\r\n");
      out.write("\t\t\t\t\tclass=\"btn_disabled btn_primary btn add_lesson\">增加科目</button>\r\n");
      out.write("\t\t\t\t<button type=\"button\" id=\"js_delLessonBtn\"\r\n");
      out.write("\t\t\t\t\tclass=\"btn_disabled  btn add_lesson\" disabled=\"disabled\">删除科目</button>\r\n");
      out.write("\t\t\t\t<button type=\"submit\" id=\"js_nextBtn\" class=\"btn_primary btn\">注册</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<!-- 年龄：<input name=\"age\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'');\"><br> -->\r\n");
      out.write("\t\t\t<!--input type=\"submit\" value=\"确定\"-->\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"foot\" id=\"footer\">\r\n");
      out.write("\t\t<ul class=\"links ft\">\r\n");
      out.write("\t\t\t<li class=\"links_item no_extra\"><a\r\n");
      out.write("\t\t\t\thref=\"http://www.tencent.com/zh-cn/index.shtml\" target=\"_blank\">关于腾讯</a>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t\t<li class=\"links_item\"><a\r\n");
      out.write("\t\t\t\thref=\"/cgi-bin/readtemplate?t=home/agreement_tmpl&amp;type=info&amp;lang=zh_CN&amp;token=\"\r\n");
      out.write("\t\t\t\ttarget=\"_blank\">服务协议</a></li>\r\n");
      out.write("\t\t\t<li class=\"links_item\"><a\r\n");
      out.write("\t\t\t\thref=\"http://kf.qq.com/product/weixinmp.html\" target=\"_blank\">客服中心</a>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t\t<li class=\"links_item\"><a\r\n");
      out.write("\t\t\t\thref=\"http://crm2.qq.com/page/portalpage/wpa.php?uin=40012345&amp;f=1&amp;ty=1&amp;ap=000011:400792:|m:12|f:400792:m:12\"\r\n");
      out.write("\t\t\t\ttarget=\"_blank\">在线客服</a></li>\r\n");
      out.write("\t\t\t<li class=\"links_item\"><a href=\"mailto:weixinmp@qq.com\"\r\n");
      out.write("\t\t\t\ttarget=\"_blank\">联系邮箱</a></li>\r\n");
      out.write("\t\t\t<li class=\"links_item\"><a\r\n");
      out.write("\t\t\t\thref=\"/cgi-bin/readtemplate?t=home/infringement_tmpl&amp;lang=zh_CN\"\r\n");
      out.write("\t\t\t\ttarget=\"_blank\">侵权投诉</a></li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<p class=\"copyright\">Copyright © 2012-2014 Tencent. All Rights\r\n");
      out.write("\t\t\tReserved.</p>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
