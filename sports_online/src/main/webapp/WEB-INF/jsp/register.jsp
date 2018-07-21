<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath %>" />
<title>注册</title>
</head>
<body>
<form action="login/addUser" method="post">
<label>账号：</label>
<input type="text" id="txtUsername" name="username" /><br/>
<label>密码：</label>
<input type="password" id="txtPassword" name="password" /><br/>
<input type="reset" value="重置" />
<input type="submit" value="提交" />
<input type="button" name="close" value="关闭" onclick="window.close();" />
</form>
</body>

<script type="text/javascript" src="common/js/jquery-3.3.1.min.js"></script>

</html>
