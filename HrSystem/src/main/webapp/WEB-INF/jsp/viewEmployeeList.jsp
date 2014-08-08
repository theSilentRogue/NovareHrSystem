<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Employee List</title>
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
				/* var maxRow = $("#rowNum select").val("option:selected"); */
				$('#empPage').jPaginate(
				{
 					'max': 5,//maxRow,
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
<div id="title">Employee List</div>
	<%@include file="navlinks.jsp"%>
	<div class="optionElements">
		<!-- center -->
		
		<!-- <select name="rowNum" id="rowNum">
		<option value="5" selected>5</option>
		<option value="10">10</option>
		<option value="20">20</option>
		<option value="30">30</option>
		<option value="40">40</option>
		<option value="50">50</option>
		<option value="100">100</option>
		<option value="1000">1000</option>
		</select> -->
		<div id="searchBox">
			<form:form modelAttribute="employee" action="searchEmp" method="post">
				Search Table: <form:input type="text" path="searchquery" />&nbsp;<input
					type="submit" value="Search" />
			</form:form>
		</div>
			<form:form modelAttribute="employee" method="post"
				action="filterEmployee" id="filterSelect">
						Status Filter:  <form:select path="filterStat"
					items="${map.status}" />
				<input type="submit" value="Filter" />
			</form:form>
			<form:form modelAttribute="employee" method="post"
				action="viewEmployeeList" id="filterSelect">
				<input type="submit" value="Reset" />
			</form:form>&nbsp;
			<span class="floaterButEmp">
			<a href="addEmployee"><img alt="Add New Employee" width="150"
				height="33" src="<c:url value="/resources/images/addEmp.png"/>"
				class="addEmplink" /></a>
			</span>
			<span class="floatReportBut">
			<a href="reportPDFEmp"><img alt="Generate Report" width="150"
				height="33"
				src="<c:url value="/resources/images/genReport.png"/>"
				class="genReportlink" /></a>
				</span>
		</div>
		<%-- <form:form method="get" action="reportPDF"> --%>
			
			<%-- </form:form> --%>
		<br />
		<table class="sortable" id="empPage">
			<thead>
				<tr>
					<!-- <th>Employee Id</th> -->
					<th>First Name</th>
					<th>Last Name</th>
					<th>Department</th>
					<th>Status</th>
					<th>Start Date</th>
					<th>Date Resigned</th>
					<th>Position</th>
					<th>Cost</th>
					<th class="sorttable_nosort">Controls</th>
					<!-- <th class="sorttable_nosort"></th> -->
				</tr>
			</thead>
			<tfoot class="sorttable_nosort">
				<!-- 			<tr><th class="sorttable_nosort" colspan="11"><div class="center">Pages: </div></th></tr> -->
			</tfoot>
			<tbody>
				<c:forEach var="employee" items="${map.employeeList}">
					<tr>
						<%-- <td>${employee.id}</td> --%>
						<td>${employee.fname}</td>
						<td>${employee.lname}</td>
						<td>${employee.department}</td>
						<td>${employee.status}</td>
						<td>${employee.start_date}</td>
						<td>${employee.date_resigned}</td>
						<td>${employee.position}</td>
						<td>Php${employee.cost}</td>
						<td><a href="editEmployee?id=${employee.id}"><img
								alt="Edit" width="85" height="29"
								src="<c:url value="/resources/images/editBut.png"/>"
								class="editlink" /></a>
						<a href="deleteEmployee?id=${employee.id}"
							onclick="return confirmAction()"><img alt="Delete" width="85"
								height="29" src="<c:url value="/resources/images/delBut.png"/>"
								class="dellink" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- center -->
	<%@include file="footer.jsp"%>
</body>
</html>