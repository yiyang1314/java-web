<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@taglib  uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分页</title>
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
<script type="text/javascript">
	function nextPage(cp){
		var pageCount = userform.pageCount.value;//拿到总页码数
		if(parseInt(cp)<=0){
			alert("已经是首页了");
		}else if(parseInt(cp)>parseInt(pageCount)){
			alert("已经是最后一页了");
		}else{
			userform.currentPage.value = cp;
			userform.submit();
			
		}
		
	}
</script>
</head>
<body>
	<form name="userform" id="userform" action="${param.action}" >
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
		
		<span class="showpagecls">共<span class="page_num" >${param.count}</span>行/合<span class="page_num" >${pageCount }</span>页,当前<span class="page_num" >${currentPage }</span>页，每页记录:
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
	</form>
</body>
</html>