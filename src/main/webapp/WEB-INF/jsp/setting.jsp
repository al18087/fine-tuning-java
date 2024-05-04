<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Optional" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	<form action="<%= request.getContextPath() %>/Setting?action=fine-tuning" enctype="multipart/form-data" method="post">
		<br>
		<h6>ファインチューニングを行うための情報を入力してください</h6>
		<br>
		<table class="table">
			<tr>
				<th>jsonlファイル</th>
				<td><input type="file" name="upload" class="btn btn-light"></td>
			</tr>
			<tr>
				<th>エポック数</th>
				<td><input type="number" name="epoch"></td>
			</tr>
			<tr>
				<th>モデル</th>
				<td>
					<div>
						<input type="radio" id="gpt-3.5-turbo" name="traning-model" value="gpt-3.5-turbo-0613" checked>
						<label class="h6 m-2">gpt-3.5-turbo-0613</label>
						<input type="radio" id="baggage-002" name="traning-model" value="baggage-002">
						<label class="h6 m-2">baggage-002</label>
						<input type="radio" id="davinci-002" name="traning-model" value="davinci-002">
						<label class="h6 m-2">davinci-002</label>
					</div>
				</td>
			</tr>
		</table>
		<input type="submit" value="ファインチューニング開始" class="btn btn-dark">
	</form>
	<br><br>
	<a href="<%= request.getContextPath() %>/Main" class="text-dark">トップページに戻る</a>
	<br><br>
</body>
</html>