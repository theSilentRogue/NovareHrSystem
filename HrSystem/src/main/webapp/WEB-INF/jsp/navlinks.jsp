<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css"/>"/>
</head>
<body>
	
	<nav>
	
		<div class="floatNav">
			<a href="viewEmployeeList" class="navLinks"><img alt="Employees" width="70" height="70" src="<c:url value="/resources/images/empBut.png"/>" class="empIcon"/><br/>Employees</a>
		</div>
		<div class="floatNav">	
			<a href="viewProjectList" class="navLinks"><img alt="Projects" width="70" height="70" src="<c:url value="/resources/images/projBut.png"/>" class="projIcon"/><br/>Projects</a>
		</div>
		<div class="floatNav">
			<a href="viewAllocationList" class="navLinks"><img alt="Allocation" width="70" height="70" src="<c:url value="/resources/images/allocBut.png"/>" class="allocIcon"/><br/>Allocation</a>
		</div>
		
		<!-- <a href="viewProjectStart" class="navLinks">Project Start Date</a>&nbsp;&nbsp; -->
		<!-- <a href="viewProjectEnd" class="navLinks">Project End Date</a> -->
	</nav>
	
	<hr/>
	<br/>
</body>
</html>