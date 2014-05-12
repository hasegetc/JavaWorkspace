<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>申请服务</title>

<script type="text/javascript" src="./jQuery/jquery.min.js"></script>
<script type="text/javascript" src="./jQueryPlugin/jquery.form.js"></script>
<link href="sanrenxingCSS/sanrenxing.css" rel="stylesheet"
	type="text/css">


<script type="text/javascript">
	function success() {
		alert("注册成功，请继续！");
		$('#submitBtn').removeAttr("disabled");
		$('#submitBtn').addClass("btn_primary");
	}

	$(document).ready(function() {
	       var code =  '<%=request.getParameter("code")%>';

	        $.ajax({
	            type : "get",
	            dataType: 'json',
	            url : "userId!getUserId.action?code=" + code,
	            async:false,
	            success : function(result) {
	            	var openid = result.openid;
	            	$('#useid').val(openid);
	            },
	            error:function(error){ 
	            	alert("error");
	        	},
	        });
	        
	});

	
	$(document).ready(function() {

		$("#submitBtn").click(function() {
			$("#submitBtn").attr("disabled", true);
			$('#submitBtn').removeClass("btn_primary");
			//alert("注册成功，请继续！");
			$('#registerForm').ajaxForm({

				success : function() {
					success();
				}
			}

			).submit();
			//return  false;不需要加  

		});

	});
</script>


</head>
<body>

	<div class="head" id="header">
		<div class="head_box">
			<div class="inner wrp">
				<h1 class="logo">
					<a href="/" title="微信公众平台"></a>
				</h1>
				<div class="account">
					<div class="account_meta account_faq" style="display: none;">
						<a class="account_primary_link"
							href="http://crm2.qq.com/page/portalpage/wpa.php?uin=40012345&amp;f=1&amp;ty=1&amp;ap=000011:400792:|m:12|f:400792:m:12"
							target="_blank">在线客服</a>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div id="body" class="body">
		<form id="registerForm" action="request!requestLesson.action">
			<div id="class" class="frm_ctl_group">
				<span class="frm_label">年级：</span> <select name="class"
					class="frm_input_box">
					<option value="senior">高中</option>
					<option value="junior">初中</option>
					<option value="primary">小学</option>
				</select>

			</div>
			<div class="frm_ctl_group lesson">
				<span class="frm_label">科目：</span> <select name="lesson"
					class="frm_input_box">
					<option value="math">数学</option>
					<option value="physical">物理</option>
					<option value="chemistry">化学</option>
					<option value="english">英语</option>
					<option value="biological">生物</option>
				</select>

			</div>
            <input id = "useid" type="hidden" name="useid" value="">
			<div class="clear"></div>
			<div class="tool_bar border with_form">
				<button type="submit" id="submitBtn"
					class="btn_disabled btn_primary btn">确定</button>

			</div>
			<!-- 年龄：<input name="age" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');"><br> -->
			<!--input type="submit" value="确定"-->
		</form>

	</div>
	<div class="foot" id="footer">
		<ul class="links ft">
			<li class="links_item no_extra"><a
				href="http://www.tencent.com/zh-cn/index.shtml" target="_blank">关于腾讯</a>
			</li>
			<li class="links_item"><a
				href="/cgi-bin/readtemplate?t=home/agreement_tmpl&amp;type=info&amp;lang=zh_CN&amp;token="
				target="_blank">服务协议</a></li>
			<li class="links_item"><a
				href="http://kf.qq.com/product/weixinmp.html" target="_blank">客服中心</a>
			</li>
			<li class="links_item"><a
				href="http://crm2.qq.com/page/portalpage/wpa.php?uin=40012345&amp;f=1&amp;ty=1&amp;ap=000011:400792:|m:12|f:400792:m:12"
				target="_blank">在线客服</a></li>
			<li class="links_item"><a href="mailto:weixinmp@qq.com"
				target="_blank">联系邮箱</a></li>
			<li class="links_item"><a
				href="/cgi-bin/readtemplate?t=home/infringement_tmpl&amp;lang=zh_CN"
				target="_blank">侵权投诉</a></li>
		</ul>
		<p class="copyright">Copyright © 2012-2014 Tencent. All Rights
			Reserved.</p>
	</div>


</body>

</html>
