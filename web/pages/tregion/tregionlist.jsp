<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String basepath=request.getScheme()+"://"+request.getServerName()+":"+request.getLocalPort()+request.getContextPath()+"/";
%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@taglib  uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<base href="<%=basepath%>" />
		<style>
			body{
				margin:0px;
				padding:0px;
				background-color:green;
			}
			fieldset{
				width:850px;
				margin-top:120px;
				margin-left:150px;
				margin-bottom:50px;
				background-color:white;
				padding-right: 180px;
				padding-left: 80px;
				padding-bottom: 80px;
			}
			table{
				border-collapse: collapse;
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
		<script type="text/javascript">
			
			function find(id){
				if(confirm("您确定要修改"+id+"吗？")){
					location.href="move_findById?id="+id;
				}
				   
			}
			
			function update(id){
				if(confirm("您确定要修改"+id+"吗？")){
					location.href="move_updateById?id="+id;
				}
				   
			}
			
			function del(id){
				if(confirm("您确定要删除"+id+"吗？")){
					location.href="move_delete?id="+id;
				}
				   
			}
			var flags=false;
			function ckeckMove(){
				if(!flags){
					flags=!flags;
					selectAll();
				}else{
					unselectAll();
				}
			}

		</script>
</head>
<body>
<fieldset>
			<legend>顺风车搬家预约系统</legend>
			<h1>顺风车搬家预约系统</h1>
			<p style="text-align:right;">----顺丰搬家，----一路顺风</p>
			<form name="fom" id="fom" method="post">
			<table border="2" >
			<tr>
				<th><input type="checkbox" onclick="ckeckMove()"> 选择</th>
				<th>起始地区</th>
				<th>所用车型</th>
				<th>搬家日期</th>
				<th>联系人</th>
				<th>电话</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
			<c:forEach var="move" items="move_list}">
				<tr>
				<td bgcolor="#FFFFFF">
					<input type="checkbox" name="delid" value="move.id}"/>
				</td>
				<td>move.area}</td>
				<td>move.cartype}</td>
				<td>move.movedate}</td>
				<td>move.contact}</td>
				<td>move.phone}</td>
				<td style="color:move.status=='0'?'red':(move.status=='1'?'green':'gray') };">
				 statusarr[move.status]}
				</td>
				<td>
					<input type="button" value="详情" onclick="find(move.id})"/>
					<input type="button" value="修改" onclick="update(move.id})"/>
					<input type="button" value="删除" onclick="del(move.id})"/>
				</td>
				</tr>
			</c:forEach>
			</table>
			</form>
			<c:if test="count>0}">
			<jsp:include page="/pages/house/details.jsp">
				<jsp:param value="move_page" name="action"/>
				<jsp:param value="enablePage}" name="enablePage"/>
				<jsp:param value="column}" name="column"/>
				<jsp:param value="enableSearch }" name="enableSearch"/>
				<jsp:param value="count}" name="count"/>
			</jsp:include>
			</c:if> 
		<a href="jsp/file/movebookingadd.jsp">添加</a>
		<a href="move_page">返回</a>
		<input type="button" value="批量删除" title="已启用"  onclick="delids('move',this)" />
		<c:if test="enablePage==false }">
			<a href="move_page">分页</a>
		</c:if>
		<c:if test="enablePage==true }">
			<a href="move_findAll">查看所有</a>
		</c:if>
		
		</fieldset>

	
	</body>
</html>