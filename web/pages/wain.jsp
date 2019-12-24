<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String basepath=request.getScheme()+"://"+request.getServerName()+":"+request.getLocalPort()+request.getContextPath()+"/";
%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<html>
	<head>
		<base href="<%=basepath%>" />
		<link href="url" type="text/css">
		<meta http-equiv="refresh" content="3;pages/login.jsp">
		<style>
			body{
				margin:0px;
				padding:0px;
				background-color:green;
			}
			fieldset{
				width:650px;
				margin-top:120px;
				margin-left:150px;
				margin-bottom:50px;
				background-color:white;
				padding-right: 180px;
				padding-left: 80px;
				padding-bottom: 80px;
			}
			.green{
				background-color:white;
			}
			.red{
				background-color:white;
			}
			.red{
				background-color:white;
			}
		</style>
	</head>
	<body onload="alert('${result.msg}')">
		<fieldset>
			<legend>用户登录</legend>
			
			<c:if test="${result.status==200}">
				<h1>当前用户，${u.loginname } 进入本系统！</h1>
				<a href="pages/house/fabu.jsp">添加</a>
				<a href="house_page">查看房源列表</a>
				<a href="house_admin">查看用户列表</a>
				<a href="users_logout">退出</a>
			</c:if>
			<c:if test="${result.status!=200}">
				<h1>${result.msg}</h1>

			</c:if>
		</fieldset>
	
	</body>
</html>