<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Project</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/dashboard.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/imageClasses.css"/>" />
</head>

<body>
<div id="title">Add New Project</div>
	<%@include file="navlinks.jsp"%>
	<!-- <div style="width: 100%; text-align: center;">
		<div style="display: inline-block;"> -->
			<form:form method="post" action="insertProject"
				modelAttribute="project">
							<div class="optionElements">
								<strong>Client : *</strong><strong style="padding-left:160px;">Project Name : *</strong><br/>
								<form:input path="client" required="required" />
								<span style="padding-left:25px;">
									<form:input path="project_name" required="required" />
								</span>
								<br /><br/> <strong>Start Date : *</strong><br/>
								<form:input path="start_date" type="date" required="required" />
								<br /><br />
								<strong><em>* = required fields</em></strong>
								<br /><br />
								<input type="submit" value="Add Project" />
								<input type="reset" value="Reset Fields" />
								<br />
								<a href="viewProjectList"><img alt="View Project List" width="150" height="33" 
								src="<c:url value="/resources/images/viewProj.png"/>" class="viewProjlink"/></a>
								<%-- <a href="viewProjectList"><img alt="View Project Table"
									width="150" height="37.5"
									src="<c:url value="/resources/images/viewProj.png"/>"
									class="viewProjlink" /></a> --%>
							</div>
			</form:form>
		<!-- </div>
	</div> -->
	<%@include file="footer.jsp"%>
</body>
</html>