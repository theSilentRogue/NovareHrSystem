<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	<title>Employee List</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css"/>"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/imageClasses.css"/>"/>
	<script src="<c:url value="/resources/scripts/sorttable.js"/>"></script>
	<script src="<c:url value="/resources/scripts/confirmDel.js"/>"></script>
</head>
<body>
	<%@include file = "navlinks.jsp" %>
	<div align="center">
	<!-- center -->
		<h1>Employee List </h1>
		<div class="floaterButEmp"><a href="addEmployee"><img alt="Add New Employee" width="150" height="37.5" src="<c:url value="/resources/images/addnewEmp.png"/>" class="addEmplink"/></a></div>	
		<table class="sortable">
			<tr>
				<th>Employee Id</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Department</th>
				<th>Status</th>
				<th>Start Date</th>
				<th>Date Resigned</th>
				<th>Position</th>
				<th>Cost</th>
				<th class="sorttable_nosort">Controls</th>
				<th class="sorttable_nosort"></th>
			</tr>
			
			<c:forEach var="employee" items="${employeeList}">
				<tr>
					<td>${employee.id}</td>
					<td>${employee.fname}</td>
					<td>${employee.lname}</td>
					<td>${employee.department}</td>
					<td>${employee.status}</td>
					<td>${employee.start_date}</td>
					<td>${employee.date_resigned}</td>
					<td>${employee.position}</td>
					<td>${employee.cost}</td>
					<td><a href="editEmployee?id=${employee.id}"><img alt="Edit" width="60" height="30" src="<c:url value="/resources/images/editbut.png"/>" class="editlink"/></a></td>
					<td><a href="deleteEmployee?id=${employee.id}"><img alt="Delete" width="60" height="30" src="<c:url value="/resources/images/delbut.png"/>" class="dellink"/></a></td>
				</tr>
			</c:forEach>
			<tr><th class="sorttable_nosort" colspan="11"><div class="center">Pages: </div></th></tr>
		</table>
	<!-- center -->
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>