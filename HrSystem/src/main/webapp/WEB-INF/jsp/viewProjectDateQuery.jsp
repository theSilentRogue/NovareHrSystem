<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Monthly Allocation Forecast</title>
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
					'max': 5,
					'page': 1,
					'links': 'selectButtons'
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
<div id="title">Monthly Allocation Forecast</div>
	<%@include file="navlinks.jsp"%>
	<div class="optionElements">
		<!-- center -->
		<%-- <div id="searchBox">
			<form:form modelAttribute="project" action="searchAlloc" method="post">
				Search Table: <form:input type="text" path="searchquery" />&nbsp;<input
					type="submit" value="Search" />
			</form:form>
		</div>
			<form:form modelAttribute="project" method="post"
				action="filterAlloc" id="filterSelect">
				Filter: <form:select path="project_name" items="${map.names}"/>
				<input type="submit" value="Filter" />
			</form:form>
			<form:form modelAttribute="project" method="post"
				action="viewAllocationList" id="filterSelect">
				<input type="submit" value="Reset" />
			</form:form>&nbsp;
			<span class="floaterButAlloc">
			<a href="addAllocation"><img alt="Add New Allocation" width="150"
				height="33"
				src="<c:url value="/resources/images/addAlloc.png"/>"
				class="addAlloclink" /></a>
			</span>
			<span class="floatReportBut">
			<a href="reportPDFAlloc"><img alt="Generate Report" width="150"
				height="33"
				src="<c:url value="/resources/images/genReport.png"/>"
				class="genReportlink" /></a>
				</span>
				<br/> --%>
			<%-- <form:form modelAttribute = "project" method = "get"
				action="datefilterAlloc">
				<form:input path="start_date" type="date" required="required"/>
				<form:input path="end_date" type="date"/>
				<input type="submit" value="Month Report" />
			</form:form> --%>
		</div>
		<br />
		<div class="center">
		<a href="/HrSystem/viewAllocationList">Back to Allocation Table</a>
		</div>
		<br />
		<table class="sortable" id="allocPage">
			<thead>
				<tr>
					<!-- <th>Allocation Id</th> -->
					<th>Project Name</th>
					<th>Plan</th>
					<th>Total Percent</th>
					<th>Daily Cost</th>
					<th>Start Date<br/>(YYYY-MM-DD)</th>
					<th>End Date<br/>(YYYY-MM-DD)</th>
					<!-- <th class="sorttable_nosort">Controls</th> -->
					<!-- <th class="sorttable_nosort"></th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach var="project" items="${map.projectList}">
					<tr>
						<%-- <td>${allocation.id}</td> --%>
						<td>${project.project_name}</td>
						<td>${project.plannedheadcount}</td>
						<td>${project.totalpercent} %</td>
						<td>Php ${project.dailycost}</td>
						<td>${project.start_date}</td>
						<td>${project.end_date}</td>
						<%-- <td><a href="editAllocation?id=${allocation.id}"><img
								alt="Edit" width="85" height="29"
								src="<c:url value="/resources/images/editBut.png"/>"
								class="editlink" /></a>
						<a href="deleteAllocation?id=${allocation.id}" onclick="return confirmAction()"><img
								alt="Delete" width="85" height="29"
								src="<c:url value="/resources/images/delBut.png"/>"
								class="dellink" /></a></td> --%>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr><th class="sorttable_nosort" colspan="11">Grand Total: </th></tr>
			</tfoot>
		</table>

		<!-- center -->
	<%@include file="footer.jsp"%>
</body>
</html>