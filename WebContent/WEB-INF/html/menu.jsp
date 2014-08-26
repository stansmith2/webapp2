<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/mayo.css">
<title>管理者メニュー</title>
</head>
<body>
	<div class="head" style="background-color:#c0c0c0"><h1>Mayonos</h1></div>
	<div class="loginUser">ようこそ、<c:out value="${sessionScope.loginUser.name}" />さん</div>
		<table>
			<tr>
				<td><a name="resipi" href="FromMenuToRecipeList">・レシピ管理</a></td>
			</tr>
			<tr>
				<td><a name="user" href="FromMenuToUserList">・ユーザ管理</a></td>
			</tr>
		</table>
</body>
</html>