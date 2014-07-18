<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
	<title>Edit Employee</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css"/>"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/imageClasses.css"/>"/>
</head>
<body>
	<%@include file = "navlinks.jsp" %>
	<div style="width: 100%; text-align: center;">
		<div style ="display: inline-block;">
			<h2>Edit User Details </h2>
			<form:form method="post" action="updateEmployee" modelAttribute="employee">
				<table>
					<tr>
						<td>
						<div class="centerContent">
						<strong>First Name :</strong>
						<form:input path="fname" value="${map.employee.fname}" required="required"/><br/>
						<strong>Last Name :</strong>
						<form:input path="lname" value="${map.employee.lname}" required="required"/><br/>
						<strong>Department :</strong>
						<form:input path="department" value="${map.employee.department}" required="required"/><br/>
						<strong>Status :</strong><br/>
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
								</select><br/>
						</spring:bind>
						<strong>Start Date :</strong>
						<form:input path="start_date" value="${map.employee.start_date}" type="date" required="required"/><br/>
						Date Resigned :
						<form:input path="date_resigned" value="${map.employee.date_resigned}" type="date"  /><br/>
						<strong>Position :</strong>
						<form:input path="position" value="${map.employee.position}" required="required"/><br/>
						<strong>Cost :</strong>
						<form:input path="cost" value="${map.employee.cost}" required="required"/><br/>
						<hr/>
						<input type="submit" value="Save" />
						</div>
						</td>
					</tr>
				</table>
				<form:hidden path="id" value="${map.employee.id}" />

			</form:form>
		</div>
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>