<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Allocation List</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css"/>"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/imageClasses.css"/>"/>
	<script src="<c:url value="/resources/scripts/sorttable.js"/>"></script>
</head>
<body>
	<%@include file = "navlinks.jsp" %>
	<div align="center">
	<!-- center -->
		<h1>Allocation List</h1>
		<div class="floaterButAlloc"><a href="addAllocation"><img alt="Add New Allocation" width="150" height="37.5" src="<c:url value="/resources/images/addnewAlloc.png"/>" class="addAlloclink"/></a></div>
		<table class="sortable">
			<tr>
				<th>Allocation Id</th>
				<th>Employee Name</th>
				<th>Project Name</th>
				<th>Percent</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th class="sorttable_nosort">Controls</th>
				<th class="sorttable_nosort"></th>
			</tr>
			
			<c:forEach var="allocation" items="${allocationList}">
				<tr>
					<td>${allocation.id}</td>
					<td>${allocation.employee_name}</td>
					<td>${allocation.project}</td>
					<td>${allocation.percent}</td>
					<td>${allocation.start_date}</td>
					<td>${allocation.end_date}</td>
					<td><a href="editAllocation?id=${allocation.id}"><img alt="Edit" width="60" height="30" src="<c:url value="/resources/images/editbut.png"/>" class="editlink"/></a></td>
					<td><a href="deleteAllocation?id=${allocation.id}"><img alt="Delete" width="60" height="30" src="<c:url value="/resources/images/delbut.png"/>" class="dellink"/></a></td>
				</tr>
			</c:forEach>
			<tr><th class="sorttable_nosort" colspan="11"><div class="center">Pages: </div></th></tr>
		</table>

	<!-- center -->
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>