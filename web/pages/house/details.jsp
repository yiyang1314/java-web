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
</HEAD>
<BODY>

	<DIV id=header class=wrap>
		<DIV id=logo>
			<IMG src="file/images/logo.jpg">
		</DIV>
		<DIV class=search>
			<FORM method=get>
				<INPUT class=text type=text name=keywords> <LABEL
					class="ui-green searchs"><a href="house_page?method=post" target="_blank">搜索房屋</a></LABEL>
			</FORM>
		</DIV>
	</DIV>
	<DIV id=navbar class=wrap>
		<DL class="search clearfix">
			<FORM id=sform method=post action="pages/house/houselist.jsp">
				<DT>
					<UL>
						<LI class=bold>房屋信息</LI>
						<LI>标题：<INPUT class=text type=text name=title> <LABEL
							class=ui-blue><INPUT onclick="location.href='pages/house/houselist.jsp'" value=搜索房屋
								type=button name=search></LABEL>
						</LI>
					</UL>
				</DT>
				<DD>
					<UL>
						<LI class=first>价格</LI>
						<LI><SELECT id=price name=price>
								<OPTION selected value="">不限</OPTION>
								<OPTION value=0-100>100元以下</OPTION>
								<OPTION value=100-200>100元—200元</OPTION>
								<OPTION value=200-1000000>200元以上</OPTION>
						</SELECT></LI>
					</UL>
				</DD>
				<DD>
					<UL>
						<LI class=first>房屋位置</LI>
						<LI><SELECT id=street name=street_id>
								<OPTION selected value="">不限</OPTION>
								<OPTION value=1000>知春路</OPTION>
								<OPTION value=1001>中关村大街</OPTION>
								<OPTION value=1002>学院路</OPTION>
								<OPTION value=1003>朝阳路</OPTION>
						</SELECT></LI>
					</UL>
				</DD>
				<DD>
					<UL>
						<LI class=first>房型</LI>
						<LI><SELECT id=housetype name=type_id>
								<OPTION selected value="">不限</OPTION>
								<OPTION value=1000>一室一厅</OPTION>
								<OPTION value=1001>一室两厅</OPTION>
								<OPTION value=1002>两室一厅</OPTION>
								<OPTION value=1003>两室两厅</OPTION>
						</SELECT></LI>
					</UL>
				</DD>
				<DD>
					<UL>
						<LI class=first>面积</LI>
						<LI><SELECT id=floorage name=floorage>
								<OPTION selected value="">不限</OPTION>
								<OPTION value=0-40>40以下</OPTION>
								<OPTION value=40-500>40-500</OPTION>
								<OPTION value=500-1000000>500以上</OPTION>
						</SELECT></LI>
					</UL>
				</DD>
			</FORM>
		</DL>
	</DIV>
	<DIV id=position class=wrap>当前位置：<a href="house_admin">自如租房网</a> /房源</DIV>
	<DIV id=view class="main wrap">
		<DIV class=intro>
			<DIV class=lefter>
				<H1>${house.title }</H1>
				<DIV class=subinfo> 
					<fmt:formatDate value="${house.houseDate }" pattern="yyyy-MM-dd hh:mm:ss"/>
				</DIV>
				<DIV class=houseinfo>
					<P>
						户 型：<SPAN>${house.houseStyle['styleName'] }</SPAN>
					</P>
					<P>
						面 积：<SPAN> ${house.houseArea } m<SUP>2</SUP></SPAN>
					</P>
					<P>
						位 置：<SPAN>${house.street }${house.region['regionNameC'] }</SPAN>
					</P>
					<P>
						联系方式：<SPAN> ${house.phone } </SPAN>
					</P>
				</DIV>
			</DIV>
			<DIV class=side>
				<P>
					<A class=bold href="http://localhost:8080/House-2/#">北京自如房地产经纪公司</A>
				</P>
				<P>资质证书：有</P>
				<P>内部编号: ${house.user['userid'] } </P>
				<P>联 系 人： ${house.user['realname']} </P>
				<P>
					联系电话：<SPAN> ${house.user['phone'] }</SPAN>
				</P>
				<P>
					手机号码：<SPAN> ${house.phone }</SPAN>
				</P>
			</DIV>
			<DIV class=clear></DIV>
			<DIV class=introduction>
				<H2>
					<SPAN><STRONG>房源详细信息</STRONG></SPAN>
				</H2>
				<DIV class=content>
					<P>${house.summary }</P>
				</DIV>
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
