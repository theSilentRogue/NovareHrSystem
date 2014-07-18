<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	<title>Project List</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css"/>"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/imageClasses.css"/>"/>
	<script src="<c:url value="/resources/scripts/sorttable.js"/>"></script>
</head>
<body>
	<%@include file = "navlinks.jsp" %>
	<div align="center">
	<!-- center -->
		<h1>Project List</h1>
		<div class="floaterButProj"><a href="addProject"><img alt="Add New Project" width="150" height="37.5" src="<c:url value="/resources/images/addnewProj.png"/>" class="addProjlink"/></a></div>
		<table class="sortable">
			<tr>
				<th>Project Id</th>
				<th>Client</th>
				<th>Project Name</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th class="sorttable_nosort">Controls</th>
				<th class="sorttable_nosort"></th>
			</tr>
			
			<c:forEach var="project" items="${projectList}">
				<tr>
					<td>${project.id}</td>
					<td>${project.client}</td>
					<td>${project.project_name}</td>
					<td>${project.start_date}</td>
					<td>${project.end_date}</td>
					<td><a href="editProject?id=${project.id}"><img alt="Edit" width="60" height="30" src="<c:url value="/resources/images/editbut.png"/>" class="editlink"/></a></td>
					<td><a href="deleteProject?id=${project.id}"><img alt="Delete" width="60" height="30" src="<c:url value="/resources/images/delbut.png"/>" class="dellink"/></a></td>
				</tr>
			</c:forEach>
			<tr><th class="sorttable_nosort" colspan="11"><div class="center">Pages: </div></th></tr>
		</table>

	<!-- center -->
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>