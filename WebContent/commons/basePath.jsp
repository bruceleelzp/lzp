<%
	//获取当前请求的上下文路径 如：//jsp2
	String path=request.getContextPath();
	
	//获取部署当前应用在服务器根目录的url
					//请求的是http://
	String basePath=request.getScheme()+"://"
	//请求的是服务器地址localhost:			//请求的是服务服务器端口
	+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- base标签，用于定义当前页HTML标签url的基准路径 -->
<base href="<%=basePath%>"/>

