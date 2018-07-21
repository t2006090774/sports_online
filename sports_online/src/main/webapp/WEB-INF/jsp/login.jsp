<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<form action="login/check" method="post">
<label>账号：</label>
<input type="text" id="txtUsername" name="username" /><br/>
<label>密码：</label>
<input type="password" id="txtPassword" name="password" /><br/>
<input type="submit" value="提交" />
<input type="reset" value="重置" />
<input id = "register_id" type="button" value="注册" />
</form>
</body>

<script type="text/javascript" src="<%=basePath %>common/js/jquery-3.3.1.min.js"></script>

<script>
	$("#register_id").click(function(){
		window.open("http://localhost:8080/sport/login/register");
	})
</script>
</html>
