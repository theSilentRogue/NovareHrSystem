<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Project List</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/dashboard.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/imageClasses.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/jPaginate-default.css"/>" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="<c:url value="/resources/scripts/sorttable.js"/>"></script>
<script src="<c:url value="/resources/scripts/jPaginate.js"/>"></script>
<script>			
			$(document).ready(function()
			{
				$('#projPage').jPaginate(
				{
					'max': 5,
					'page': 1,
					'links':'selectButtons'
				});
			});
		</script>
		<script>
			function confirmAction()
			{
				var act = confirm("Do you want to delete this item?");
				return act;
			}
		</script>
</head>
<body>
<div id="title">Project List</div>
	<%@include file="navlinks.jsp"%>
	<div class="optionElements">
		<!-- center -->
		<div id="searchBox">
			<form:form modelAttribute="project" action="searchProject" method="post">
				Search Table: <form:input type="text" path="searchquery" />&nbsp;<input
					type="submit" value="Search" />
			</form:form>
		</div>
			<form:form modelAttribute="project" method="post"
				action="filterProject" id="filterSelect">
				Filter: <form:select path="project_name" items="${map.names}"/>
				<input type="submit" value="Filter" />
			</form:form>
			<form:form modelAttribute="project" method="post"
				action="viewProjectList" id="filterSelect">
				<input type="submit" value="Reset" />
			</form:form>&nbsp;
			<span class="floaterButProj">
			<a href="addProject"><img alt="Add New Project" width="150"
				height="33"
				src="<c:url value="/resources/images/addProj.png"/>"
				class="addProjlink" /></a>
			</span>
			<span class="floatReportBut">
			<a href="reportPDFProj"><img alt="Generate Report" width="150"
				height="33"
				src="<c:url value="/resources/images/genReport.png"/>"
				class="genReportlink" /></a>
				</span>
		</div>
		<br />
		<table class="sortable" id="projPage">
			<thead>
				<tr>
					<!-- <th>Project Id</th> -->
					<th>Client</th>
					<th>Project Name</th>
					<th>Start Date<br/>(YYYY-MM-DD)</th>
					<th>End Date<br/>(YYYY-MM-DD)</th>
					<th class="sorttable_nosort">Controls</th>
					<!-- <th class="sorttable_nosort"></th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach var="project" items="${map.projectList}">
					<tr>
						<%-- <td>${project.id}</td> --%>
						<td>${project.client}</td>
						<td>${project.project_name}</td>
						<td>${project.start_date}</td>
						<td>${project.end_date}</td>
						<td><a href="editProject?id=${project.id}"><img
								alt="Edit" width="85" height="29"
								src="<c:url value="/resources/images/editBut.png"/>"
								class="editlink" /></a>
						<a href="deleteProject?id=${project.id}" onclick="return confirmAction()"><img
								alt="Delete" width="85" height="29"
								src="<c:url value="/resources/images/delBut.png"/>"
								class="dellink" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<!-- <tr><th class="sorttable_nosort" colspan="11"><div class="center">Pages: </div></th></tr> -->
			</tfoot>
		</table>

		<!-- center -->
	<%@include file="footer.jsp"%>
</body>
</html>