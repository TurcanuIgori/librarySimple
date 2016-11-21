<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
		<title>Login</title>
		<link type="image/png" rel="shortcut icon" href="Resources/images/favicon.gif"/>			
		<link rel="stylesheet" type="text/css" href="Resources/styles/style.css">
	</head>
	<body>
		<form class="box login" id="loginForm" method="POST" action="UserController?action=LOGIN">
			<fieldset class="boxBody">
				<a href="?lang=ru">
					<img src="Resources/images/rus.png" title="Russian" width="16" height="13"/>
				</a>
				<a href="?lang=en">
					<img src="Resources/images/usa.png" title="English" width="16" height="13"/>
				</a>
				<a href="singup.jsp" value="Register" class="btnRegister">Sing Up</a>
			 	<label>Username</label>
			 	<input type="text" value="${login}" name="login" form="loginForm" id="login" tabindex="1" required>
			 	<label><a href="#" class="rLink" tabindex="5">Forget your password?</a>Password</label>
				<input type="password" value="${password}" id="password" name="password"  form="loginForm" tabindex="2" required>
				<span class="error">${textStatus}</span>
			</fieldset>
			<footer>
				<label><input type="checkbox"  form="loginForm">Remember me</label>
			  	<input type="submit" class="btnLogin"  form="loginForm" value="Login">
<!-- 			  <a href="home.jsp" class="btnLogin" value="Login">Login</a> -->
			</footer>	 
		</form>
	</body>
</html>