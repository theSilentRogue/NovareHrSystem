<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<title>Home - Resource Management System</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js" ></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"></script>
<link rel="stylesheet"
	href="<c:url value="/resources/css/dashboard.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/imageClasses.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/jPaginate-default.css"/>" />
</head>
<body>
<div id="title">Home - Resource Management System</div>
	<%@include file="/WEB-INF/jsp/navlinks.jsp"%>
	<br />
	<div class="center">
	<h1>Welcome!</h1>
		<h2>
			You are currently on the Resource Management System Home page. 
			<br />If you wish to get started, click on one of the tabs above.
		</h2>
	</div>
	<br />
	<%@include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>
 <%--response.sendRedirect("viewEmployeeList");--%>