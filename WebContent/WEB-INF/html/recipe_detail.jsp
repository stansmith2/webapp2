<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/mayo.css">
<script type="text/javascript" src="js/mayo.js">
</script>

<title>レシピ詳細</title>
</head>
<body>
	<div class="head" style="background-color:#c0c0c0">
		<h1>Mayonos</h1>
	</div>
	<div class="loginUser">
		ようこそ、
		<c:out value="${sessionScope.loginUser.name}" />
		さん
	</div>

レシピ詳細<br>
<c:out value="${requestScope.checkError}" />
<form action="FileUpload" method="POST"  enctype="multipart/form-data">
	<table class="list">
		<tr>
			<td rowspan="6">
				<c:if test="${requestScope.detailFlg != null}">
					<img src="ImageGet?id=${requestScope.recipeDto.recipeId}" width="250" height="250" alt="写真" title="No_IMG">
				</c:if>
				<c:if test="${requestScope.detailFlg == null}">
					<img src="img/NO_img.jpeg" alt="写真" title="No_IMG">
				</c:if>
			</td>
			<td>
				レシピNO
			</td>
			<td colspan="2">
				<input type="text" name="recipeNo" value="${requestScope.recipeDto.recipeId}" disabled="disabled">
				<input type="hidden" name="recipeNo2"  value="${requestScope.recipeDto.recipeId}">
			</td>
		</tr>
		<tr>
			<td>
				タイトル
			</td>
			<td colspan="2">
				<input type="text" name="title" value="${requestScope.recipeDto.title}">
			</td>
		</tr>
		<tr>
			<td>
				材料
			</td>
			<td colspan="2">
				<textarea name="material"> ${requestScope.recipeDto.material}</textarea>
			</td>
		</tr>
		<tr>
			<td>
				手順
			</td>
			<td colspan="2">
				<textarea name="process">${requestScope.recipeDto.procedureiCooking}</textarea>
			</td>
		</tr>
		<tr>
			<td>
				調理時間
			</td>
			<td colspan="2">
				<input type="text" name="cookingTime" value="${requestScope.recipeDto.cookingMin}">
			</td>
		</tr>
		<tr>
			<td>
				レシピ画像
			</td>
			<td>
				<input type="file" name="file" accept="image/*">
			</td>
		</tr>
	</table>

	<input id="recipeDetail_back" type="button" onclick="location.href='BackRecipeList'" value="戻る">
	<input id="recipeDetail_submit" type="submit" value="保存">
	<c:if test="${requestScope.detailFlg != null}">
		<input id="recipeDetail_delete" type="button" onclick="location.href='RecipeDelete?id=${requestScope.recipeDto.recipeId}'" value="削除" onClick="return kakunin()">
		<input type="hidden" name="flg" value="1">
	</c:if>
</form>

</body>
</html>