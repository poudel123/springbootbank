<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>
</head>
<body>
<body>
<%@ include file="header.jsp" %>

	<form action="changePassword.do" method="post">
	
	<label>Old Password</label>
	<input type="password" size=20 name="oldPassword">
	
	
	<label>New Password</label>
	<input type="password" size=20 name="newPassword" >
	
	
	<label>Confirm New Password</label>
	<input type="password" size=20 name="confirmNewPassword">
	
	
	<input type="submit" value="GO">
	</form>
</body>
</html>