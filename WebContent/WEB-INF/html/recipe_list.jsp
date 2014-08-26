<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/mayo.css">

<title>レシピ一覧</title>
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
	
	レシピ一覧<br>
	<form action="RecipeSearch" method="POST">
		<table>
			<tr>
				<td>タイトル</td>
				<td><input type="text" name="search"></td>
				<td><input type="submit" value="検索"></td>
			</tr>
		</table>
	</form>
	
	<c:out value="${requestScope.errorMsg}" />
	
	<table border=1 class="list">
		<tr>
			<th>レシピNO</th>
			<th>タイトル</th>
			<th>登録日</th>
			<th>詳細</th>
		</tr>
	<c:forEach var="lists" items="${requestScope.list}">
		<tr>
			<th><c:out value="${lists.recipeId}" /></th>
			<th><c:out value="${lists.title}" /></th>
			<th><c:out value="${lists.addDate}" /></th>
			<th><a href="FromRecipeListToRecipeDetail?id=${lists.recipeId}">詳細</a></th>
		</tr>
		
	</c:forEach>
	
	</table>
	
	<div class="futt">

	<form action="FromRecipeListToRecipeDetail" method="POST">
		<input id="recipe_add" type="submit" value="追加">
	</form>
	<form action="BackMenu" method="POST">
		<input id="recipe_back" type="submit" value="戻る">
	</form>
	</div>
	
</body>
</html>