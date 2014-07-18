<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
	<title>Edit Project</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css"/>"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/imageClasses.css"/>"/>
</head>
<body>
	<%@include file = "navlinks.jsp" %>

	<div style="width: 100%; text-align: center;">
		<div style ="display: inline-block;">
			<b>Edit Project Details </b>
			<form:form method="post" action="updateProject" modelAttribute="project">
				<table>
					<tr>
						<td>
						<div class="centerContent">
						<strong>Client :</strong>
						<form:input path="client" value="${map.project.client}" required="required"/><br/>
						<strong>Project Name :</strong>
						<form:input path="project_name" value="${map.project.project_name}" required="required"/><br/>
						<strong>Start Date :</strong>
						<form:input path="start_date" value="${map.project.start_date}" type="date" required="required"/><br/>
						End Date :
						<form:input path="end_date" value="${map.project.end_date}" type="date"  /><br/>
						<hr/>
						<input type="submit" value="Save" />
						</div>
						</td>
					</tr>
				</table>
				<form:hidden path="id" value="${map.project.id}" />

			</form:form>
		</div>
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>