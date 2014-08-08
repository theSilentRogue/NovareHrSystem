<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<title>Edit Employee</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/dashboard.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/imageClasses.css"/>" />
</head>
<body>
<div id="title">Edit User Details</div>
	<%@include file="navlinks.jsp"%>
<!-- 	<div style="width: 100%; text-align: center;">
		<div style="display: inline-block;"> -->
			<form:form method="post" action="updateEmployee"
				modelAttribute="employee">
							<div class="optionElements">
								<strong>First Name :</strong> <strong style="padding-left: 130px;">Last Name :</strong><strong style="padding-left: 140px;">Department :</strong><br />
								<span>
								<form:input path="fname" value="${map.employee.fname}"
									required="required" />
									</span>
									<span style="padding-left: 25px;">
								<form:input path="lname" value="${map.employee.lname}"
									required="required" />
									</span>
									<span style="padding-left: 30px;">
								<form:input path="department" value="${map.employee.department}"
									required="required" />
									</span>
								<br /> <br /> <strong>Status :</strong> <strong style="padding-left: 160px;">Position :</strong> <strong style="padding-left: 150px;">Cost :</strong><br />
								<span>
								<spring:bind path="status">
									<select name="status">
										<c:forEach items='${map.status}' var='status'>
											<c:choose>
												<c:when test="${statusName eq map.employee.status}">
													<option value="${status}" selected>${status}</option>
												</c:when>
												<c:otherwise>
													<option value="${status}">${status}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</spring:bind>
								</span>
								<span style="padding-left: 105px;">
								<form:input path="position" value="${map.employee.position}"
									required="required" />
									</span>
									<span style="padding-left: 30px;">
									<form:input path="cost" value="${map.employee.cost}"
									required="required" />
									</span>
									<br /><br />
								<strong>Start Date :</strong> <strong style="padding-left: 130px;">Date Resigned :</strong><br />
								<span></span>
								<form:input path="start_date" value="${map.employee.start_date}"
									type="date" required="required" />
									<span style="padding-left: 45px;">
								<form:input path="date_resigned"
									value="${map.employee.date_resigned}" type="date" />
									</span>
								<br /> 
								<br />
								
								<input type="submit" value="Save Record" />
								<input type="reset" value="Reset Fields"/>
								<br />
								<a href="viewEmployeeList"><img alt="View Employee Table"
									width="150" height="33"
									src="<c:url value="/resources/images/viewEmp.png"/>"
									class="viewEmplink" /></a>
							</div>
				<form:hidden path="id" value="${map.employee.id}" />

			</form:form>
<!-- 		</div>
	</div> -->
	<%@include file="footer.jsp"%>
</body>
</html>