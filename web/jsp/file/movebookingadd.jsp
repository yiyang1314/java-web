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
			<h1>顺风车搬家预约登记</h1>
			<p style="text-align:right;">----顺丰搬家，----一路顺风</p>
				<form action="move_save" method="post" style="width:550px;" name="myform" id="myform">
					<p >起始地区:
						<select name="area">
							<option value="-1" >---请选择---</option>
							<option value="海淀区">海淀区</option>
							<option value="西城区">西城区</option>
							<option value="东城区">东城区</option>
							<option value="丰台区">丰台区</option>
							<option value="大兴区">大兴区</option>
							<option value="石景山">石景山</option>
						</select>
					 </p>
					<p >所用车型：
					<input type="radio" name="cartype" 	value="金杯"/>金杯
					<input type="radio" name="cartype" 	value="皮卡"/>皮卡
					<input type="radio" name="cartype" checked value="厢式小货"/>厢式小货
					<input type="radio" name="cartype" value="1041货车"/>1041货车
					</p>
					<p >搬家日期：<input type="text" name="movedate" /> 日期格式如：2019-01-08 </p>
					<p >联系人：<input type="text" name="contact"  /></p>
					<p >电话：<input type="text"  name="phone" /> </p>		
					<p  style="text-align:center;">
						<input type="submit" value="预约登记" />
						<input type="reset" value="&nbsp;重&nbsp;置&nbsp;" />
					</p>
				</form>	
				<a href="move_page">返回</a>
				<a href="jsp/file/movebookingtip.jsp">返回</a>
		</fieldset>

	
	</body>
</html>