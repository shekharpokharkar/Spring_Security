<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h1>Login</h1>
		<hr>
		<form action="/custom-login-processing" method="post" >
			<label for="userName">Username:</label> <input type="text"
				id="userName" name="username" required /><br />
			<br /> <label for="password">Password:</label> <input type="password"
				id="password" name="password" required />
			<br />
			<br /> 
			<input type="submit" value="Login" />
		</form>
	</div>
</body>
</html>