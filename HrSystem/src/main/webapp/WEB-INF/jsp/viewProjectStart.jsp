<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Project Start Date</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css"/>"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/imageClasses.css"/>"/>
</head>
<body>
	<%@include file = "navlinks.jsp" %>
	<div align="center">
	<!-- center -->
		<h1>Project Start List</h1>
		<div class="floaterButQuery"><a href="viewAllocationList"><img alt="View Allocation Table" width="150" height="37.5" src="<c:url value="/resources/images/viewAlloc.png"/>" class="viewAlloclink"/></a></div>
		<table>
			<tr>
				<th>Project Name</th>
				<th>Employee Name</th>
				<th>Start Date</th>
			</tr>
			
			<c:forEach var="projectStart" items="${projectStartList}">
				<tr>
					<td>${projectStart.employee_name}</td>
					<td>${projectStart.project}</td>
					<td>${projectStart.start_date}</td>
				</tr>
			</c:forEach>
			<tr><td colspan="11"><div class="center">Pages: </div></td></tr>
		</table>

	<!-- center -->
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>