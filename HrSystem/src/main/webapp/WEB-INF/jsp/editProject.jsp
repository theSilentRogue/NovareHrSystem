<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<title>Edit Project</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/dashboard.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/imageClasses.css"/>" />
</head>
<body>
<div id="title">Edit Project Details</div>
	<%@include file="navlinks.jsp"%>

	<!-- <div style="width: 100%; text-align: center;">
		<div style="display: inline-block;"> -->
			<form:form method="post" action="updateProject"
				modelAttribute="project">
							<div class="optionElements">
								<strong>Client :</strong><strong style="padding-left: 186px;">Project Name :</strong><br />
								<form:input path="client" value="${map.project.client}"
									required="required" />
									<span style="padding-left: 40px;">
										<form:input path="project_name"
										value="${map.project.project_name}" required="required" />
									</span>
								<br /><br/> <strong>Start Date :</strong><strong style="padding-left:155px;">End Date :</strong><br/>
								<form:input path="start_date" value="${map.project.start_date}"
									type="date" required="required" />
									<span style="padding-left: 60px;">
								<form:input path="end_date" value="${map.project.end_date}"
									type="date" />
									</span>
								<br /><br/>
								<input type="submit" value="Save Record" />
								<input type="reset" value="Reset Fields"/>
								<br />
								<a href="viewProjectList"><img alt="View Project List" width="150" height="33" 
								src="<c:url value="/resources/images/viewProj.png"/>" class="viewProjlink"/></a>
							</div>
				<form:hidden path="id" value="${map.project.id}" />

			</form:form>
		<!-- </div>
	</div> -->
	<%@include file="footer.jsp"%>
</body>
</html>