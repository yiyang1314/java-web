<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String basepath=request.getScheme()+"://"+request.getServerName()+":"+request.getLocalPort()+request.getContextPath()+"/";
%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@taglib  uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<html>
	<head>
		<base href="<%=basepath%>" />
		<style>
			body{
				margin:0px;
				padding:0px;
				background-color:blue;
			}
			fieldset{
				width:350px;
				margin-top:120px;
				margin-left:150px;
				margin-bottom:50px;
				background-color:green;
				padding-right: 180px;
				padding-left: 80px;
				padding-bottom: 80px;
			}
		</style>
	</head>
	<body>
		<fieldset>
			<legend>顺风车搬家预约系统</legend>
			<table>
			<tr>
				<th>起始地区</th>
				<th>所用车型</th>
				<th>搬家日期</th>
				<th>联系人</th>
				<th>电话</th>
				<th>操作</th>
			</tr>
			<c:forEach var="move" items="${move_list}">
				<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				</tr>
			</c:forEach>
			</table>
		</fieldset>

	
	</body>
</html>