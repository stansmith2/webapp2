<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/.css">
<title>ログイン</title>
</head>
<body>
	<div class="head" style="background-color:#c0c0c0"><h1>Mayonos</h1></div>
	
	<c:out value="${requestScope.erroMg}" />
	<br>
	<form method="POST" action="FromLoginToMenu">
	<table>
		<tr>
			<td>ユーザID</td><td><input type="text" name="id"/></td>
		</tr>
		<tr>
			<td>パスワード</td><td><input type="password" name="password"/></td>
		</tr>
	</table>
	<input type="submit" value="ログイン"/>
	</form>
</body>
</html>