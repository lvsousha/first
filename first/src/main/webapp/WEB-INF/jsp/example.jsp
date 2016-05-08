<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<link rel="stylesheet" href="/first/jquery/jquery-ui.css" />
	<link rel="stylesheet" href="/first/jquery/example/example.css" />
	<script src="/first/jquery/jquery.js"></script>
	<script src="/first/jquery/jquery-ui.js"></script>
	<script src="/first/jquery/example/example.js"></script>
</head>
<title>用户登录-个人多功能系统</title>
<body>

<div id="header">
	<div class="header_title">
		<span class="title_con">个人多功能系统</span>
	</div>
</div>

<div id="content">
	<center>
		<div class="con">
		<div class="con_title">
			<span class="con_title_sp">欢迎登录个人多功能系统</span>
		</div>
		<div class="con_panel">
			<div class="con_input">
				<span>用户名：</span><input type="text" placeholder="邮箱/昵称/电话号码"/>
			</div>
			<div class="con_input">
				<span>密&nbsp;&nbsp;&nbsp;&nbsp;码：</span><input type="text" placeholder="密码"/>
			</div>
			<div class="con_select">
				<input type="radio" name="t1" value="个人" />个人
				<input type="radio" name="t1" value="管理员" />管理员
			</div>
			<button type="button" id="submit" class="submit-btn">登    录</button>
			<button type="button" id="register" class="submit-btn">注    册</button>
		</div>
	</div>
	</center>
</div>

<div style="text-align:center;">
<p>来源:<a href="http://www.mycodes.net/" target="_blank">源码之家</a></p>
</div>

</body>
</html>