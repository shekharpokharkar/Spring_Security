<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form action="/process-register" method="post"
		modelAttribute="REG">

		<label for="user">Username</label>
		<form:input path="username" id="user" />
		<br>
		<br>
		<label for="pass">Password</label>
		<form:input path="password" id="pass" />
		<br>
		<br>
		<input type="submit" value="Register">
	</form:form>
</body>
</html>