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
	<body>
		<fieldset>
			<legend>顺风车搬家预约系统</legend>
			<h1>顺风车搬家预约系统</h1>
			<form action="move_save" method="post">
			<p style="text-align:right;">----顺丰搬家，----一路顺风</p>
			<input readonly type="hidden" name="id" value="${move.id}"/>
			<table border="2">
			<tr>
				<th>起始地区</th>
				<td><input readonly type="text" name="area" value="${move.area}"/></td>
			</tr>
			<tr>
				<th>所用车型</th>
				<td><input  readonly type="text" name="cartype" value="${move.cartype}"/></td>
			</tr>
			<tr>
				<th>搬家日期</th>
				<td><input readonly type="text" name="movedate" value="${move.movedate}"/></td>
			</tr>
			<tr>
				<th>联系人</th>
				<td><input readonly type="text" name="contact" value="${move.contact}" /></td>
			</tr>
			<tr>
				<th>电话</th>
				<td><input readonly type="text" value="${move.phone}" name="phone" /></td>
			</tr>
			<tr>
				<th>状态</th>		
				<td>
					<c:if test="${move.status!='2'}">
					<select name="status">
						<option ${move.status=='0'?'selected':''} value="0">未处理</option>
						<option ${move.status=='1'?'selected':''} value="1">已派车</option>
						<option ${move.status=='2'?'selected':''} value="2">已结束</option>
					</select>
					</c:if>
					<c:if test="${move.status=='2'}">已结束</c:if>
				</td>
			</tr>
			<c:if test="${move.status!='2'}">
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="处理登记信息" />
				</td>
			</tr>
			</c:if>
			</table>
			</form>
		</fieldset>
		<a href="move_page">返回</a>
	</body>
</html>