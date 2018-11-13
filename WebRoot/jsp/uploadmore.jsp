<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	// 项div中间添加文本框
	function run(){
		var div = document.getElementById("divId");
		div.innerHTML += "<div><input type='file' name='myfile'><input type='button' value='删除' onclick='del(this)'/></div>";
	}
	
	// 删除某一行
	function del(who){
		// 获取删除的按钮的父节点
		var divv = who.parentNode;
		divv.parentNode.removeChild(divv);	// 最外围的div
	}
	
</script>

</head>
<body>

<h3>多文件上传</h3>
<form action="${ pageContext.request.contextPath }/upload" method="post" enctype="multipart/form-data">
	<input type="button" value="添加" onclick="run()" >&nbsp;<input type="submit" value="上传" />
	
	<div id="divId">
		
	</div>
	
</form>

</body>
</html>