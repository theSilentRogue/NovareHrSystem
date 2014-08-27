<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Allocation</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/dashboard.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/imageClasses.css"/>" />
</head>

<body>
<div id="title">Add New Allocation</div>
	<%@include file="navlinks.jsp"%>
	<!-- <div style="width: 100%; text-align: center;">
		<div style="display: inline-block;"> -->
		<div class="optionElements">
			<form:form method="post" action="insertAllocation"
				modelAttribute="allocation">
							
								<strong>Employee : *</strong><strong style="padding-left:115px;">Project Name : *</strong> <strong style="padding-left:115px;">Percent : *</strong><br/>
								<%-- <form:input path="employee_id" required="required" /> --%>
									<form:select path="employee.id" required="required">
										<form:options items="${employees}" var="employee" itemValue="value" itemLabel="key"/>
									</form:select>
									<span style="padding-left:34px;">
								<%-- <form:input path="project_id" required="required" /> --%>
									<form:select path="project.id" required="required">
										<form:options items="${projects}" var="project" itemValue="value" itemLabel="key"/>
									</form:select>
									</span>
									<span style="padding-left: 18px;">
									<form:input path="percent" required="required" />
									</span>
								<br />
								<br /> <strong>Start Date : *</strong><br/>
								<form:input path="start_date" type="date" required="required" placeholder="09-09-2014"/>
								&nbsp;<%--  End Date :
								<form:input path="end_date" type="date" placeholder="10-29-2015"/>
								--%><br /><br />
								<strong><em>* = required fields</em></strong>
								<br /><br />
								<input type="submit" value="Add Allocation" />
								<input type="reset" value="Reset Fields" />
								<br />
								<a href="viewAllocationList"><img
									alt="View Allocation List" width="150" height="33"
									src="<c:url value="/resources/images/viewAlloc.png"/>"
									class="viewAlloclink" /></a>
								<br />
								<%-- <a href="viewAllocationList"><img
									alt="View Allocation Table" width="150" height="37.5"
									src="<c:url value="/resources/images/viewAlloc.png"/>"
									class="viewAlloclink" /></a> --%>
			</form:form>
			</div>
<!-- 		</div>
	</div> -->
	<%@include file="footer.jsp"%>
</body>
</html>