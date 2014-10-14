<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/dashboard.css"/>" />
	<link rel="stylesheet" href="<c:url value="/resources/css/RowTabs.css"/>"/>
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script> -->
	<script>
	$(document).ready(function() {
		//Below is the click function which you need AJAX to work properly
		/* $('nav ul.tabrow > li').click(function(e) {
			$("nav ul.tabrow > li").removeClass("selected");
			$(this).addClass("selected");
			//e.preventDefault();
		}); */
		
		//For Navigational Link without using AJAX
		$('.tabrow a').filter(function(){return this.href==location.href}).parent().addClass('selected').siblings().removeClass('selected');
	});
	</script>
</head>
<body>
<nav>
	<ul class="tabrow">
		<li><a href="index" class="navLinks">Home</a></li>
		<li> <!-- class="selected" --><a href="viewEmployeeList" class="navLinks">Employees</a></li>
		<li><a href="viewProjectList" class="navLinks">Projects</a></li>
		<li><a href="viewAllocationList" class="navLinks">Allocation</a></li>
	</ul>
</nav>
	<%-- <nav>
		<div class="floatNavEmp">
			<a href="viewEmployeeList" class="navLinks"><img alt="Employees"
				width="70" height="70"
				src="<c:url value="/resources/images/empBut.png"/>" class="empIcon" />Employees</a>
			<!-- </div>
		<div class="floatNavProj">	 -->
			<a href="viewProjectList" class="navLinks"><img alt="Projects"
				width="70" height="70"
				src="<c:url value="/resources/images/projBut.png"/>"
				class="projIcon" />Projects</a>
			<!-- </div>
		<div class="floatNavAlloc"> -->
			<a href="viewAllocationList" class="navLinks"><img
				alt="Allocation" width="70" height="70"
				src="<c:url value="/resources/images/allocBut.png"/>"
				class="allocIcon" />Allocation</a>
		</div>
		<!-- <a href="viewProjectStart" class="navLinks">Project Start Date</a>&nbsp;&nbsp; -->
		<!-- <a href="viewProjectEnd" class="navLinks">Project End Date</a> -->
	</nav> --%>
	<!-- <hr /> -->
	<br />
</body>
</html>