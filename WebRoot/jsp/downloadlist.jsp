<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Queue"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>下载多个文件</h3>

<%
	String rootPath = "D:\\root";
	// 向队列存放File
	File root = new File(rootPath);
	// 创建队列
	Queue<File> queue = new LinkedList<File>();
	// 把根节点入队
	queue.offer(root);
	// 循环的条件，如果队列不为空，一直循环
	while(!queue.isEmpty()){
		// 先获取根节点
		File file = queue.poll();
		// 获取file文件下的所有子节点
		File [] files = file.listFiles();
		// 循环遍历
		for(File f : files){
			// 拿到每一个File对象，判断当前File是文件还是文件夹
			if(f.isFile()){
				// 如果是一个文件，提供下载。显示到页面上。
%>
		<h4><a href="${ pageContext.request.contextPath }/downloadlist?path=<%=f.getCanonicalPath()%>"><%=f.getName() %></a></h4>
<%				
				
			}else{
				// 如果是一个文件夹
				queue.offer(f);
			}
		}
	}
 	
%>

</body>
</html>











