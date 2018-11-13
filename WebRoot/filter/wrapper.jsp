<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>GET方式</h3>
<form action="${ pageContext.request.contextPath }/wrapper" method="get">
	文本：<input type="text" name="username" /><br/>
	<input type="submit" value="提交" />
</form>

<h3>POST方式</h3>
<form action="${ pageContext.request.contextPath }/wrapper" method="post">
	文本：<input type="text" name="username" /><br/>
	<input type="submit" value="提交" />
</form>

</body>
</html>