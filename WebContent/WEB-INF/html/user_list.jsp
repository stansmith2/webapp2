<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/mayo.css">

<title>ユーザ管理画面</title>
</head>
<body>
	<div class="head" style="background-color: #c0c0c0">
		<h1>Mayonos</h1>
	</div>
	<div class="loginUser">
		ようこそ、
		<c:out value="${sessionScope.loginUser.name}" />
		さん
	</div>

	ユーザ一覧
	<br>
	<form action="UserSearch" method="POST">
		<table>
			<tr>
				<td>氏名</td>
				<td><input type="text" name="search"></td>
				<td><input type="submit" value="検索"></td>
			</tr>
		</table>
	</form>
	<c:out value="${requestScope.errorMsg}" />

	<table border=1 class="list">
		<tr style="background-color: #dcdcdc">
			<th>ユーザID</th>
			<th>氏名</th>
			<th>登録日</th>
			<th>詳細</th>
		</tr>
		<c:forEach var="lists" items="${requestScope.list}">
			<tr>
				<th><c:out value="${lists.userId}" /></th>
				<th><c:out value="${lists.name}" /></th>
				<th><c:out value="${lists.addDate}" /></th>
				<th><a href="FromUserListToUserDetail?id=${lists.userId}">詳細</a></th>
			</tr>

		</c:forEach>
	</table>
	<div class="futt">
		<form action="FromUserListToUserDetail" method="POST">
			<input type="hidden" name="which" value="add"> <input
				class="user_button" type="submit" value="追加">
		</form>
		<form action="BackMenu" method="POST">
			<input id="user_back" type="submit" value="戻る">
		</form>
	</div>
</body>
</html>