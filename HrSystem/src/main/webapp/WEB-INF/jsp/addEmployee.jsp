<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Register Employee</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/dashboard.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/imageClasses.css"/>" />
</head>
<body>
	<%@include file="navlinks.jsp"%>
	<div style="width: 100%; text-align: center;">
		<div style="display: inline-block;">
			<h2>Register New Employee</h2>
			<form:form method="post" action="insertEmployee"
				modelAttribute="employee">
				<table>
					<tr>
						<td>
							<div class="centerContent">
								<strong>First Name : *</strong>
								<form:input path="fname" required="required" />
								<br /> <strong>Last Name : *</strong>
								<form:input path="lname" required="required" />
								<br /> <strong>Department : *</strong>
								<form:input path="department" required="required" />
								<br /> <strong>Status : *</strong><br />
								<form:select path="status" items="${map.status}" />
								<br /> <strong>Start Date : *</strong>
								<form:input path="start_date" type="date" required="required" />
								<br /> Date Resigned :
								<form:input path="date_resigned" type="date" />
								<br /> <strong>Position : *</strong>
								<form:input path="position" required="required" />
								<br /> <strong>Cost : *</strong>
								<form:input path="cost" required="required" />
								<br />
								<hr />
								<input type="submit" value="Save" /><br />
								<br /> <strong><em>* = required fields</em></strong><br />
								<hr />
								<a href="viewEmployeeList"><img alt="View Employee Table"
									width="150" height="37.5"
									src="<c:url value="/resources/images/viewEmp.png"/>"
									class="viewEmplink" /></a>
							</div>
						</td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>