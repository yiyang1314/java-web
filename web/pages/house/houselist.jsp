<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String basepath=request.getScheme()+"://"+request.getServerName()+":"+request.getLocalPort()+request.getContextPath()+"/";
%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@taglib  uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<base href="<%=basepath%>" />
		<TITLE>自如租房 - 首页</TITLE>
		<META content="text/html; charset=utf-8" http-equiv=Content-Type>
		<LINK rel=stylesheet type=text/css href="file/css/style.css">
		<META name=GENERATOR content="MSHTML 8.00.7601.17514">
		<script type="text/javascript" src="file/scripts/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="file/scripts/houselist2.js"></script>
		<script type="text/javascript">
		function onchangse(){
			var upper1=sform.street_id.value;
			var upper2=sform.address.value;
			console.log(sform.code.value);
			if(upper2==""){
				sform.code.value=upper1;
			}else{
				sform.code.value=upper2;
			}
			sform.submit();
		}
		function nextPage(cp){
			var pageCount = sform.pageCount.value;//拿到总页码数
			if(parseInt(cp)<=0){
				alert("已经是首页了");
			}else if(parseInt(cp)>parseInt(pageCount)){
				alert("已经是最后一页了");
			}else{
				sform.currentPage.value = cp;
				sform.submit();
				
			}
			
		}
		</script>
		<style type="text/css">
			.showpagecls{
		font-size: 14px;
		color: gray;
		font-style: italic;
	
	}
	p{
			padding-left:50px;
			margin-top:20px;
			text-align:left;
	}
	.page_num{
		font-size: 16px;
		color: black;
		font-weight: bold;
	}
	
	.nowpage_on{
		font-size: 18px;
		text-decoration: none;
		color: red;
		font-weight: bold;
		cursor: text;
	
	}
	
	.nowpage_off{
		font-size: 16px;
		text-decoration: none;
		color: green;
		font-weight: bold;
	}
		</style>
	</HEAD>
<BODY >
<DIV id=header class=wrap>
<DIV id=logo><IMG src="file/images/logo.jpg"></DIV></DIV>
<FORM id="sform"  method="post" action="house_page">
<DIV id=navbar class=wrap>
<DL class="search clearfix">
  	<input type="hidden" name="code" id="code" value="${hosemap['street_id']}"/>
	<DT>
		<UL>
			<LI class=bold>房屋信息</LI>
			<LI>标题：<INPUT class=text type=text name="title" value="${hosemap['title'] }"> 
			<LABEL
				class=ui-blue> <INPUT type="submit" value=搜索房屋 name=search>
			</LABEL>
			</LI>
		</UL>
	</DT>
	<DD>
		<UL>
			<LI class=first>价格</LI>
			<LI><SELECT name="price" onchange="onchangse()">
				<OPTION ${hosemap['price']==''?'selected':''}  value="">不限</OPTION>
				<OPTION ${hosemap['price']=='*-500'?'selected':''} value=*-500>500元以下</OPTION>
				<OPTION ${hosemap['price']=='500-1000'?'selected':''} value=500-1000>500元—1000元</OPTION>
				<OPTION ${hosemap['price']=='1000-*'?'selected':''} value=1000-*>1000元以上</OPTION>
			</SELECT></LI>
		</UL>
	</DD>
	<DD>
		<UL>
			<LI class=first>区域</LI>
			<LI>
				<SELECT id="street" name="street_id" >
				</SELECT>
			</LI>
		</UL>
	</DD>
	<DD>
		<UL>
			<LI class=first>街道</LI>
			<LI>
				<SELECT id="address"  name="address">
					<option value="110101" selected>不限</option>
				</SELECT>
			</LI>
		</UL>
	</DD>
	<DD>
		<UL>
			<LI class=first>房型</LI>
			<LI>
			<SELECT name="type_id" id="type_id" onchange="onchangse()">
				<OPTION ${hosemap['styleid']==''?'selected':'' } value="">不限</OPTION>
				<c:forEach var="style" items="${stylelist }">
					<OPTION ${hosemap['styleid']==style.styleid?'selected':'' }  value="${style.styleid }">
						${style.styleName }
					</OPTION>
				</c:forEach>
			</SELECT>
			</LI>
		</UL>
	</DD>
	<DD>
		<UL>
			<LI class=first>面积</LI>
			<LI>
				<SELECT name="floorage" onchange="onchangse()">
					<OPTION selected value="">不限</OPTION>
					<OPTION ${hosemap['areas']=='*-40'?'selected':''} value=*-40>40以下</OPTION>
					<OPTION ${hosemap['areas']=='40-100'?'selected':''} value=40-100>40-100</OPTION>
					<OPTION ${hosemap['areas']=='100-200'?'selected':''} value=100-200>100-200</OPTION>
					<OPTION ${hosemap['areas']=='200-500'?'selected':''} value=200-500>200-500</OPTION>
					<OPTION ${hosemap['areas']=='500-*'?'selected':''} value=500-*>500以上</OPTION>
				</SELECT>
			</LI>
		</UL>
	</DD>
</DL>
</DIV>

<DIV class="main wrap">
		<TABLE class=house-list>
			<TBODY>
				<c:forEach items="${houselist }" var="house" varStatus="pionter">
					<TR class=add>
						<TD class=house-thumb><span>
							<A href="house_findById?houseid=${house.houseid }" target="_blank"><img
									src="file/images/thumb_house.gif" width="100" height="75" alt=""></a></span></TD>
						<TD>
							<DL>
								<DT>
									<A href="house_findById?houseid=${house.houseid }" target="_blank">${house.title}</A>
								</DT>
								<DD style="text-index:20px;">
									${house.street} ${house.region['regionNameC']} ${house.houseArea} 平米<BR>联系方式：${house.phone}
								</DD>
							</DL>
						</TD>
						<TD class=house-type>${house.houseStyle['styleName']}</TD>
						<TD class=house-price><SPAN>${house.housePrice}</SPAN>元/月</TD>
					</TR>
				</c:forEach>
			</TBODY>
		</TABLE>
		<DIV class=pager>
			<c:if test="${count>0}">
				<p>
				<input type="hidden" name="pageCount" id="pageCount" value="${pageCount }" />
				<input type="hidden" name="currentPage" id="currentPage" value="${currentPage }" />
				<input type="hidden" name="count" id="count" value="${count }" />
				<input type="button" value="首页" ${currentPage==1?"disabled":"" } onclick="nextPage('1');" />
				<input type="button" value="上一页" ${currentPage==1?"disabled":"" } onclick="nextPage('${currentPage-1 }');" />
				<c:if test="${currentPage>3 }">
				<a href="javascript:void(0);"  onclick="nextPage('${currentPage-3 }');" class="nowpage_off" >...</a>
				</c:if>
				<c:if test="${currentPage>2 }">
				<a href="javascript:void(0);"  onclick="nextPage('${currentPage-2 }');"  class="nowpage_off" >${currentPage-2 }</a>
				</c:if>
				<c:if test="${currentPage>1 }">
				<a href="javascript:void(0);" onclick="nextPage('${currentPage-1 }');"  class="nowpage_off" >${currentPage-1 }</a>
				</c:if>
				<a href="javascript:void(0);" class="nowpage_on" >${currentPage }</a>
				<c:if test="${currentPage<pageCount }">
				<a href="javascript:void(0);"  onclick="nextPage('${currentPage+1 }');"  class="nowpage_off" >${currentPage+1 }</a>
				</c:if>
				<c:if test="${currentPage+1<pageCount }">
				<a href="javascript:void(0);"  onclick="nextPage('${currentPage+2 }');" class="nowpage_off" >${currentPage+2 }</a>
				</c:if>
				<c:if test="${currentPage+2<pageCount }">
				<a href="javascript:void(0);"  onclick="nextPage('${currentPage+3 }');" class="nowpage_off" >...</a>
				</c:if>
				
				<input type="button" value="下一页" ${currentPage==pageCount?"disabled":"" } onclick="nextPage('${currentPage+1 }');" />
				<input type="button" value="尾页" ${currentPage==pageCount?"disabled":"" } onclick="nextPage('${pageCount }');" />
				
				<span class="showpagecls">共<span class="page_num" >${count}</span>行/合<span class="page_num" >${pageCount }</span>页,当前<span class="page_num" >${currentPage }</span>页，每页记录:
				<select class="page_num" name="pageSize" onchange="nextPage('1')">
					<option value="1" ${pageSize==1?'selected':''}>1</option>
					<option value="2" ${pageSize==2?'selected':''}>2</option>
					<option value="3" ${pageSize==3?'selected':''}>3</option>
					<option value="5" ${pageSize==5?'selected':''}>5</option>
					<option value="10"${pageSize==10?'selected':''}>10</option>
					<option value="15"${pageSize==15?'selected':''}>15</option>
					<option value="30"${pageSize==30?'selected':''}>30</option>
				</select>行</span>
				</p>
			</c:if> 
		</DIV>
</DIV>
</FORM>
	<DIV id=footer class=wrap>
		<DL>
		  <DT>自如租房 © 2010 北京自如 京ICP证1000001号</DT>
		  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD>
		</DL>
	</DIV>
 </BODY>
</HTML>
