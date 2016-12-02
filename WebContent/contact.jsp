<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">		
		<title>Contact Us</title>
		<link type="image/png" rel="shortcut icon" href="Resources/images/favicon.gif"/>
		<link rel="stylesheet" type="text/css" href="Resources/styles/style.css">
<!-- 		<script src="Resources/js/ajax.js" type="text/javascript"> </script> -->
		<script src="Resources/js/validator.js" type="text/javascript"> </script>
	</head>
	<body>		
		<form class="box register" id="userForm" method="POST" action="SendEmail">
			<fieldset class="boxBody">
				<label class="error">${textStatus}</label>		
				<label id="firstNameLabel">First Name</label>
				<input type="text" form="userForm" id="firstName" value="${user.firstName} ${user.lastName}" autocomplete="off" onkeyup="checkFirstName()" name="firstName">
				<label id="firstNameError" class="error"></label>
				<label>Email</label>
				<input type="text" name="email" id="email"  value="${user.email}" autocomplete="off" onkeyup="checkEmail()">
				<label id="emailError" class="error"></label>
				<label>Phone</label>
				<input type="text" name="phone" id="phone" autocomplete="off"  value="${user.phone}" onkeyup="checkPhone()">
				<label id="phoneError" class="error"></label>
				<textarea id="message" name="message" rows="7" cols="30">
				</textarea>
			</fieldset>
			<footer>	
				<input type="submit" form="userForm" value="Sing Up" class="btnLogin" id="btnLogin">  
			</footer>	 
		</form>
	</body>
</html>