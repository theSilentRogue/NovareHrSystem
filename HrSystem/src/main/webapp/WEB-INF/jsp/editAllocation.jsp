<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Allocation</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/dashboard.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/imageClasses.css"/>" />
</head>
<body>
<div id="title">Edit Allocation Details</div>
	<%@include file="navlinks.jsp"%>
<!-- 	<div style="width: 100%; text-align: center;">
		<div style="display: inline-block;"> -->
			<form:form method="post" action="updateAllocation"
				modelAttribute="allocation">
							<div class="optionElements">
								<strong>Employee Name :</strong><strong style="padding-left:100px;">Project Name :</strong><strong style="padding-left:160px;">Percent :</strong><br />
								<form:hidden path="employee.id" value="${map.empID}"/>
								<form:input path="employee.id" value="${map.allocation.employee_name}" required="required"/>
								<%-- <form:input path="employee_name"
									value="${map.allocation.employee_name}" required="required" readonly="true"/> --%>
									<span style="padding-left:25px;">
									<form:select path="project.id" required="required">
										<option value="">--Select Project--</option>
										<form:options items="${projects}" var="project" itemValue="value" itemLabel="key"/>
								</form:select>
								</span>
								<%-- <spring:bind path="project">
								<span style="padding-left:25px;">
									<select name="project">
										<c:forEach items='${map.names}' var='name'>
											<c:choose>
												<c:when test="${name eq map.allocation.project_name}">
													<option value="${name}" selected>${name}</option>
												</c:when>
												<c:otherwise>
													<option value="${name}">${name}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
									</span>
								</spring:bind> --%>
								<%-- <form:input path="project_id"
									value="${map.allocation.project_id}" required="required" /> --%>
									<span style="padding-left:60px;">
										<form:input path="percent" value="${map.allocation.percent}"
										required="required" />
									</span>
									<br />
								<br /> <strong>Start Date :</strong><strong style="padding-left:140px;">End Date :</strong><br />
								<form:input path="start_date"
									value="${map.allocation.start_date}" type="date"
									required="required" />
			
								<span style="padding-left:48px;">
									<form:input path="end_date" value="${map.allocation.end_date}"
										type="date" />
								</span>
								<br /><br />
								<input type="submit" value="Save Record" />
								<input type="reset" value="Reset Fields"/>
								<br />
								<a href="viewAllocationList"><img
									alt="View Allocation List" width="150" height="33"
									src="<c:url value="/resources/images/viewAlloc.png"/>"
									class="viewAlloclink" /></a>
							</div>
				<form:hidden path="id" value="${map.allocation.id}" />
			</form:form>
		<!-- </div>
	</div> -->
	<%@include file="footer.jsp"%>
</body>
</html>