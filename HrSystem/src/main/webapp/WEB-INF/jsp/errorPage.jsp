<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>System Error</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/dashboard.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/imageClasses.css"/>" />
</head>
<body>
	<div class="centerContent">
		<h1 style="color: #fff; font-style: italic;">Error!</h1>
		<h2>Something went wrong... Please go back to the main page.</h2>
		<br /> <a href="index">Go Back</a>
	</div>

</body>
</html>