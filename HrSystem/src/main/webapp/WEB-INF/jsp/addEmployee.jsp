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
<div id="title">Register New Employee</div>
	<%@include file="navlinks.jsp"%>
<!-- 	<div style="width: 100%; text-align: center;">
		<div style="display: inline-block;"> -->
			<form:form method="post" action="insertEmployee"
				modelAttribute="employee">
							<div class="optionElements">
								<strong>First Name : *</strong><strong style="padding-left:130px;">Last Name : *</strong><strong style="padding-left:130px;">Department : *</strong><br />
								<span>
									<form:input path="fname" required="required" />
								</span>
								<span style="padding-left:33px;">
									<form:input path="lname" required="required" />
								</span>
								<span style="padding-left:31px;">
									<form:input path="department" required="required" />
								</span>
								<br /><br/> <strong>Status : *</strong> <strong style="padding-left:158px;">Position : *</strong><strong style="padding-left:145px;">Cost : *</strong><br />
								<span> <!-- style="padding-left:80px;" -->
									<form:select path="status" items="${map.status}" />
								</span>
								<span style="padding-left:113px;">
									<form:input path="position" required="required" />
								</span>
								<span style="padding-left:31px;">
									<form:input path="cost" required="required" />
								</span>
								<br /><br />
								<%-- <br /> Date Resigned :
								<form:input path="date_resigned" type="date" /> --%>
								<strong>Start Date : *</strong><br />
								<form:input path="start_date" type="date" required="required" />
								<br />
								<br />
								<strong><em>* = required fields</em></strong>
								<br />
								<br />
								<input type="submit" value="Add Employee" />
								<input type="reset" value="Reset Fields" />
								<br />
								<a href="viewEmployeeList"><img alt="View Employee Table"
									width="150" height="33"
									src="<c:url value="/resources/images/viewEmp.png"/>"
									class="viewEmplink" /></a>
							</div>
			</form:form>
		<!-- </div>
	</div> -->
	<%@include file="footer.jsp"%>
</body>
</html>