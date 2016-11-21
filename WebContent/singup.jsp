<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">		
		<title>Sing Up</title>
		<link type="image/png" rel="shortcut icon" href="Resources/images/favicon.gif"/>
		<link rel="stylesheet" type="text/css" href="Resources/styles/style.css">
<!-- 		<script src="Resources/js/ajax.js" type="text/javascript"> </script> -->
		<script src="Resources/js/validator.js" type="text/javascript"> </script>
	</head>
	<body>		
		<form class="box register" id="userForm" method="POST" action="UserController" enctype="multipart/form-data">
			<fieldset class="boxBody">
				<a href="?lang=ru">
					<img src="Resources/images/rus.png" title="Russian" width="16" height="13"/>
				</a>
				<a href="?lang=en">
					<img src="Resources/images/usa.png" title="English" width="16" height="13"/>
				</a>
				<input type="hidden" form="userForm" id="action" name="action" value="ADD_EDIT_USER"> 
				<input type="hidden" form="userForm" id="idUser" name="idUser" value="${user.id}">
				<div id="profilePicture">
					<c:if test="${user.id != null}">
				        <img src="Picture?id=${user.id}" id="image" width="150px"> 					        
				    </c:if>    
				    <c:if test="${user.id == null}">
				        <img src="Resources/images/noImg.png" id="image" height="150px">					        
				    </c:if>
					<input type="file" accept="image/*" onchange="document.getElementById('image').setAttribute('src', window.URL.createObjectURL(this.files[0]))" name="picture" id="picture">
				</div>
				<label class="error">${textStatus}</label>		
				<label id="firstNameLabel">First Name</label>
				<input type="text" form="userForm" id="firstName" value="${user.firstName}" autocomplete="off" onkeyup="checkFirstName()" name="firstName">
				<label id="firstNameError" class="error"></label>
				<label>Last Name</label>
				<input type="text" name="lastName" autocomplete="off"  value="${user.lastName}" id="lastName" onkeyup="checkLastName()">
				<label id="lastNameError" class="error"></label>
				<label>Email</label>
				<input type="text" name="email" id="email"  value="${user.email}" autocomplete="off" onkeyup="checkEmail()">
				<label id="emailError" class="error"></label>
				<label>Phone</label>
				<input type="text" name="phone" id="phone" autocomplete="off"  value="${user.phone}" onkeyup="checkPhone()">
				<label id="phoneError" class="error"></label>
				<label>Date of Birth</label>
				<input type="text" name="dob" id="dob" autocomplete="off"  value="${user.dob}" onkeyup="checkDob()">
				<label id="dobError" class="error"></label>
				<label>Gender</label>
				<input type="radio" name="gender" id="male" value="M"> Male 
				<input type="radio" id="female" name="gender" value="F"> Female
				<label id="genderError" class="error"></label>
				<label>Password</label>
				<input type="password" name="password" id="password" onkeyup="checkPass()">
				<label id="passError" class="error"></label>
				<label>Confirm Password</label>
				<input type="password" name="passwordConfirmation" id="passwordConfirmation" onkeyup="checkPass()">
				<label id="passConfError" class="error"></label>
			</fieldset>
			<footer>	
				<input type="submit" form="userForm" value="Sing Up" class="btnLogin" id="btnLogin">  
<!-- 			<a href="home.jsp" onClick="createUser()" id="btnLogin" class="btnLogin">Sing Up</a> -->
			</footer>	 
		</form>
	</body>
</html>