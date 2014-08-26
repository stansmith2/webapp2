<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/mayo.css">

<title>ユーザ詳細画面</title>
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
	
	ユーザ詳細
	<br>
	<c:out value="${requestScope.checkError}" />
	<form action="UserUpdate" method="POST">
		<table id="user_detail">
			<tr>
				<td>ユーザID</td>
				<td><input type="text" name="userId"
					value="${requestScope.userDto.userId}"> <input
					type="hidden" name="beforeUserId"
					value="${requestScope.userDto.userId}"></td>
			</tr>
			<tr>
				<td>氏名</td>
				<td><input type="text" name="name"
					value="${requestScope.userDto.name}"></td>
			</tr>
			<tr>
				<td>パスワード</td>
				<td><input type="password" name="password1"
					value="${requestScope.userDto.password}"></td>
			</tr>
			<tr>
				<td>パスワード(確認用)</td>
				<td><input type="password" name="password2"></td>
			</tr>
		</table>

		<input id="back_user_list" type="button" onclick="location.href='BackUserList'" value="戻る">
		<input id="user_submit" type="submit" value="保存">
		<c:if test="${requestScope.detailFlg != null}">
			<input id="delete_user" type="button" onclick="location.href='UserDelete?id=${requestScope.userDto.userId}'" value="削除">
			<input type="hidden" name="flg" value="1">
		</c:if>
	</form>

</body>
</html>