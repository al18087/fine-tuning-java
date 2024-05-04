<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	crossorigin="anonymous">
<link rel="stylesheet" href="/WEB-INF/css/base.css">
<title>source code Copilot</title>
</head>
<body class="container">
	<header>
	<br><br>
		<a href="<%= request.getContextPath() %>/Setting" class="h5 text-dark">設定</a>
	</header>
	<form action="<%= request.getContextPath() %>/Main?action=inference" method="post">
		<br>
		<br>
		<table class="table">
			<tr>
				<th>文章を入力してください</th>
				<td><textarea name="user-content" placeholder="お手伝いをします" cols="100" rows="10"></textarea></td>
			</tr>
		</table>
		<input type="submit" value="コードを生成" class="btn btn-dark">
	</form>
	<br><br>
	<table class="table">
		<tr>
			<th>システムからの提案</th>
			<td>
				<textarea name="text-content" cols="100" rows="10" readonly><%= (String) request.getAttribute("suggestion") %>
				</textarea>
			</td>
		</tr>
	</table>
</body>
</html>
