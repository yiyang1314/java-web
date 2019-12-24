<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getLocalPort()
			+ request.getContextPath() + "/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<base href="<%=basepath%>" />
<TITLE>自如租房 -发布房屋信息</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="file/css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
<script type="text/javascript" src="file/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript">
$(function(){
	function areaload(){
		//加載房屋類型
		$("#type_id").load("style_findAll",function(data,status){
			  if(status=='success'){
				  var options=eval('('+data+')');//JSON.parse(data);
				  for(var i=0;i<options.length;i++){
					  var op=new Option(options[i].styleName,options[i].styleid);
					  $("#type_id").append(op);
				  }
			  }
			  
		   });
		//加載位置區域
	   $("#area").load("region_findAll",function(data,status){
		$("#area").html("");
		$("#area").append("<OPTION selected value='1101'>不限</OPTION>");
		  if(status=='success'){
			  var options=eval('('+data+')');//JSON.parse(data);
			  for(var i=0;i<options.length;i++){
				  var op=new Option(options[i].regionNameC,options[i].code);
				  $("#area").append(op);
			  }
			
		  }
	   });

	  
	}

	areaload();

	$("#area").change(function(){
		 $("#address").html("");
		 $("#address").append("<OPTION selected value="+$("#area").val()+">不限</OPTION>");
		changeStreet();
	});
	
	function changeStreet(){
	   $("#address").load("region_findBySupperId","area="+$("#area").val(),function(data,status){
			  if(status=='success'){
				  var options=eval('('+data+')');//JSON.parse(data);
				  for(var i=0;i<options.length;i++){
					  var op=new Option(options[i].regionNameC,options[i].code);
					  $("#address").append(op);
				  }
			  }
		});
	}
	
	$(":submit").click(function(){
		
		var area=$("#area").children(":selected").val();
		var subarea=$("#address").children(":selected").val();
		var areatitle=$("#area").children(":selected").text();
		var subareatitle=$("#address").children(":selected").text();
		$("house_address").val(areatitle+" "+subareatitle);
		$("area_id").val(area+" "+subarea);
	});
	
});

</script>
</HEAD>
<BODY>
	<DIV id=header class=wrap>
		<DIV id=logo>
			<IMG src="file/images/logo.jpg">
		</DIV>
	</DIV>
	<DIV id=regLogin class=wrap>
		<DIV class=dialog>
			<DL class=clearfix>
				<DT>${house==null?'发布':'修改' }新房屋信息</DT>
				<DD class=past>${house==null?'填写':'修改' }房屋信息</DD>
			</DL>
			<DIV class=box>
				
				<FORM id="add_action" method="post" name="myform" action="house_save">
				<INPUT id=add_action_title class=text type="hidden" name="houseid" value="${house.houseid }">
					<DIV class=infos>
						<TABLE class=field>
							<TBODY>
								<TR>
									<TD class=field>标 题：</TD>
									<TD><INPUT id=add_action_title class=text type=text
										name="title" value="${house.title }"></TD>
								</TR>
								<TR>
									<TD class=field>户 型：</TD>
									<TD>
									<SELECT class=text name="type_id" id="type_id"  value="${house.houseStyle['styleid']}">
									
									</SELECT>
									</TD>
								</TR>
								<INPUT id="area_id"  type="hidden" name="area_id" />
								<INPUT id="house_address"  type="hidden" name="house_address" />
								<TR>
									<TD class=field>面 积：</TD>
									<TD><INPUT id=add_action_floorage class=text type=text
										name="houseArea"  value="${house.houseArea }"></TD>
								</TR>
								<TR>
									<TD class=field>图片：</TD>
									<TD><INPUT class=text type="file" name="picPath"  value="${house.picPath }"></TD>
								</TR>
								<TR>
									<TD class=field>价 格：</TD>
									<TD><INPUT id=add_action_price class=text type=text
										name="price"  value="${house.housePrice }"></TD>
								</TR>
								<TR>
									<TD class=field>房产证日期：</TD>
									<TD>
									<INPUT class=text type="date" name="houseDate"  value="${house.houseDate }" />
									</TD>
								</TR>
								<TR>
									<TD class=field>位 置：</TD>
									<TD>区：<SELECT class="text" name="district_id" id="area" >
											<option value="1101">不限</option>
										</SELECT> 
										街：<SELECT class=text name="street_id" id="address"  >
											<option value="110101">不限</option>
										</SELECT>
									</TD>
								</TR>
								<TR>
									<TD class=field>联系方式：</TD>
									<TD><INPUT id=add_action_contact class=text type=text
										name="contact" value="${house.phone }"></TD>
								</TR>
								<TR>
									<TD class=field>详细信息：</TD>
									<TD><TEXTAREA name="description">
										${house.summary }
										</TEXTAREA>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
						<DIV class=buttons>
							<INPUT  value="${house==null?'立即发布':'保存' }" type="submit" />
						</DIV>
					</DIV>
				</FORM>
			</DIV>
		</DIV>
	</DIV>
	<DIV id=footer class=wrap>
		<DL>
			<DT>自如租房 © 2010 北京自如 京ICP证1000001号</DT>
			<DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD>
		</DL>
	</DIV>
</BODY>
</HTML>
