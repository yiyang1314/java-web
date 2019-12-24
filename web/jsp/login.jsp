<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>马上登录！</title>
	</head>
	<style>
	body{
		margin:0px;
		padding:0px;
		background-color:blue;
	}
	fieldset{
		width:350px;
		
		margin-top:220px;
		margin-left:150px;
		background-color:green;
	}
	</style>
	<script>
		function checkName(name){
			console.log(name);
			if(name!=''){
				alert(name+"登陆成功！");
				location.href="jsp/file/movebookingtip.jsp";
			}
		}
	</script>
	<body onload="checkName('${u.name}')">
		<fieldset>
			<legend>用户登录</legend>
			<form action="users_login" method="post">
				<p>姓名：<input type="text" name="username"></p>
				<p>密码：<input type="password" name="password"></p>
				<p><input type="checkbox" name="remeber">记住</p>
				<p><input type="submit" value="登录">
				<input type="reset" value="重置"></p>
			</form>
		</fieldset>
	</body>
</html>