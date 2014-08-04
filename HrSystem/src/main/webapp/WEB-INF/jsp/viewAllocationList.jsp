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
				$('#allocPage').jPaginate(
				{
					'max': 4,
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
	<%@include file="navlinks.jsp"%>
	<div align="center">
		<!-- center -->
		<h1>Allocation List</h1>
		<div class="floaterButAlloc">
			<a href="addAllocation"><img alt="Add New Allocation" width="150"
				height="37.5"
				src="<c:url value="/resources/images/addnewAlloc.png"/>"
				class="addAlloclink" /></a>
		</div>
		<br />
		<div id="searchBox">
			<form:form modelAttribute="project" action="searchAlloc" method="post">
				Search Table: <form:input type="text" path="searchquery" />&nbsp;<input
					type="submit" value="Search" />
			</form:form>
		</div>
		<div>
			<form:form modelAttribute="project" method="post"
				action="filterAlloc" id="filterSelect">
				Filter: <form:select path="project_name" items="${map.names}"/>
				<input type="submit" value="Filter" />
			</form:form>
			<form:form modelAttribute="project" method="post"
				action="viewAllocationList" id="filterSelect">
				<input type="submit" value="Reset" />
			</form:form>
		</div>
		<a href="reportPDFAlloc">Download Report</a>
		<br />
		<br />
		<table class="sortable" id="allocPage">
			<thead>
				<tr>
					<!-- <th>Allocation Id</th> -->
					<th>Employee</th>
					<th>Project Name</th>
					<th>Percent</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th class="sorttable_nosort">Controls</th>
					<th class="sorttable_nosort"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="allocation" items="${map.allocationList}">
					<tr>
						<%-- <td>${allocation.id}</td> --%>
						<td>${allocation.employee_name}</td>
						<td>${allocation.project}</td>
						<td>${allocation.percent}</td>
						<td>${allocation.start_date}</td>
						<td>${allocation.end_date}</td>
						<td><a href="editAllocation?id=${allocation.id}"><img
								alt="Edit" width="60" height="30"
								src="<c:url value="/resources/images/editbut.png"/>"
								class="editlink" /></a></td>
						<td><a href="deleteAllocation?id=${allocation.id}" onclick="return confirmAction()"><img
								alt="Delete" width="60" height="30"
								src="<c:url value="/resources/images/delbut.png"/>"
								class="dellink" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<!-- <tr><th class="sorttable_nosort" colspan="11"><div class="center">Pages: </div></th></tr> -->
			</tfoot>
		</table>

		<!-- center -->
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>