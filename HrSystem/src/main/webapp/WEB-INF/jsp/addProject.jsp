<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Add Project</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css"/>"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/imageClasses.css"/>"/>
</head>

<body>
	<%@include file = "navlinks.jsp" %>
	<div style="width: 100%; text-align: center;">
		<div style ="display: inline-block;">
		<h2>Add New Project</h2> 
			<form:form method="post" action="insertProject" modelAttribute="project">
				<table>
					<tr>
					<td>
					<div class="centerContent">
						<strong>Client : *</strong>
						<form:input path="client" required="required" /><br/>
						<strong>Project Name : *</strong>
						<form:input path="project_name" required="required"/><br/>
						<strong>Start Date : *</strong>
						<form:input path="start_date" type="date" required="required"/><br/>
						Date Resigned :
						<form:input path="end_date" type="date"  /><br/>
						<hr/>
						<input type="submit" value="Save" /><br/><br/>
						<strong><em>* = required fields</em></strong><br/>
						<hr/>
						<a href="viewProjectList"><img alt="View Project Table" width="150" height="37.5" src="<c:url value="/resources/images/viewProj.png"/>" class="viewProjlink"/></a>
						</div>
						</td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>