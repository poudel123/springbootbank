<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Profile</title>
</head>
<body>
<%@ include file="header.jsp" %>
	<form action="editProfile.do" method="post">
	
		<label>Email Id:</label>
		<input type="text" name="emailId" value="${sessionScope.customer.email}"/>
		<br>
			
		<label>Address:</label>
		<input type="text" name="address" value="${sessionScope.customer.address}"/>
		<br>
			
		<input type="submit" value="submit">
		
	</form>
</body>
</html>