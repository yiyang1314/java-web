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
		<script type="text/javascript">
			function update(id){
				if(confirm("您确定要修改"+id+"吗？")){
					location.href="move_updateById?id="+id;
				}
				   
			}
		</script>
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
			<h1>顺风车搬家预约系统----movebookinginfo.jsp</h1>
			<p style="text-align:right;">----顺丰搬家，----一路顺风</p>
			
			<table border="2">
			<tr>
				<th>起始地区</th>
				<td>${move.area}</td>
			</tr>
			<tr>
				<th>所用车型</th>
				<td>${move.cartype}</td>
			</tr>
			<tr>
				<th>搬家日期</th>
				<td>${move.movedate}</td>
			</tr>
			<tr>
				<th>联系人</th>
				<td>${move.contact}</td>
			</tr>
			<tr>
				<th>电话</th>
				<td>${move.phone}</td>
			</tr>
			<tr>
				<th>状态</th>
				<td style="color:${move.status=='0'?'red':(move.status=='1'?'green':'gray') };">${statusarr[move.status]}</td>
			</tr>
			</table>
			<a href="move_page">返回</a>
		</fieldset>
		
	</body>
</html>