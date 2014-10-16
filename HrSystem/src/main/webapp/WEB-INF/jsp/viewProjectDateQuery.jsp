<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Monthly Allocation Forecast</title>
<%-- Load latency(long) purpose --%>
<%-- <link rel="stylesheet"
	href="<c:url value="/resources/css/jquery-ui.min.css"/>"/>--%>
	<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>-->
	<!-- <script src="/resources/scripts/jquery-ui.min.js"></script>-->
	
<%-- Used for better latency load times --%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.js" ></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/themes/redmond/jquery-ui.css" />

<%-- The rest of the scripts go here... --%>
<script src="<c:url value="/resources/scripts/jPaginate.js"/>"></script>
<script src="<c:url value="/resources/scripts/sorttable.js"/>"></script>
<link rel="stylesheet"
	href="<c:url value="/resources/css/jPaginate-default.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/dashboard.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/imageClasses.css"/>" />
</head>
<body>
<div id="title">Monthly Allocation Forecast</div>
	<%@include file="navlinks.jsp"%>
		<br />
		<div class="center">
		<a href="/HrSystem/viewAllocationList">Back to Allocation Table</a>
		</div>
		<br />
		<table class="sortable" id="allocPage">
			<thead>
				<tr>
					<!-- <th>Allocation Id</th> -->
					<th>Project Name</th>
					<th>Month</th>
					<th>Year</th>
					<th>Plan</th>
					<th>Total Percent</th>
					<th>Daily Cost</th>
					<!-- <th>Start Date<br/>(YYYY-MM-DD)</th>
					<th>End Date<br/>(YYYY-MM-DD)</th> -->
					<!-- <th class="sorttable_nosort">Controls</th> -->
					<!-- <th class="sorttable_nosort"></th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach var="project" items="${map.projectList}">
					<tr>
						<%-- <td>${allocation.id}</td> --%>
						<td>${project.project_name}</td>
						<td>${project.month}</td>
						<td>${project.year}</td>
						<td>${project.plannedheadcount}</td>
						<td>${project.totalAllocation} %</td>
						<%-- <td>${project.totalAlloc} %</td> --%>
						<td>Php ${project.dailycost}</td>
						<%-- <td>Php ${project.dailyCostMonth}</td> --%>
						<%-- <td>${project.start_date}</td>
						<td>${project.end_date}</td> --%>
						<%-- <td><a href="editAllocation?id=${allocation.id}"><img
								alt="Edit" width="85" height="29"
								src="<c:url value="/resources/images/editBut.png"/>"
								class="editlink" /></a>
						<a href="deleteAllocation?id=${allocation.id}" class="delConfirm" onclick="return confirmAction()"><img
								alt="Delete" width="85" height="29"
								src="<c:url value="/resources/images/delBut.png"/>"
								class="dellink" /></a></td> --%>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr><th class="sorttable_nosort" colspan="11">Grand Total: </th></tr>
			</tfoot>
		</table>

	<%@include file="footer.jsp"%>
<script>			
			$(document).ready(function()
			{
				$('#allocPage').jPaginate(
				{
					'max': 10,
					'page': 1,
					'links': 'selectButtons'
				});
				
				$("#message").dialog({
					autoOpen: false,
					//Remove the 'X' button in the top right corner
					open: function(event, ui){
						$(".ui-dialog-titlebar-close").hide();
						setTimeout("$('#message').dialog('close')",1500);
					},
					modal: true,
					draggable: false,
					resizable: false,
					show: "fade",
					hide: "fade",
					buttons: {
				        OK: function() {
				          $( this ).dialog( "close" );
				        }
				      }
					});
				
				$("#firstBut").click(function(event){
					$("#message").dialog("open");
					event.preventDefault();
				});
				
				$("#lastBut").click(function(event){
					$("#message").dialog("open");
					event.preventDefault();
				});
				
				$(".delConfirm").click(function(event){
					var $targetItem = $(this).attr('href');
					$("#message-delete").dialog({
						autoOpen: false,
						//Remove the 'X' button in the top right corner and set timeout
						open: function(event, ui){$(".ui-dialog-titlebar-close").hide();},
						modal: true,
						draggable: false,
						resizable: false,
						show: "fade",
						hide: "fade",
						width: 320,
						buttons: {
					        Delete: function() {
					        	window.location.href = $targetItem;
					          $( this ).dialog( "close" );
					        },
							Cancel: function(){
								$( this ).dialog( "close" );
							}
					      }
						});
					$("#message-delete").dialog("open");
					event.preventDefault();
				});
				
			});(jQuery)
				/* function confirmAction()
				{
					var act = confirm("Do you want to delete this item?");
					return act;
				} */
</script>
</body>
</html>