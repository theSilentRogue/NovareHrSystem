<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Allocation List</title>
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
<div id="title">Allocation List</div>
	<%@include file="navlinks.jsp"%>
	<div class="optionElements">
		<!-- center -->
		<div id="searchBox">
			<form:form modelAttribute="project" action="searchAlloc" method="post">
				Search Table: <form:input type="text" path="searchquery" />&nbsp;<input
					type="submit" value="Search" />
			</form:form>
		</div>
			<form:form modelAttribute="project" method="post"
				action="filterAlloc" id="filterSelect">
				Filter: <form:select path="project_name" items="${map.names}"/>
				<input type="submit" value="Filter" />
			</form:form>
			<form:form modelAttribute="project" method="post"
				action="viewAllocationList" id="filterSelect">
				<input type="submit" value="Reset Page" />
			</form:form>&nbsp;
			<span class="floaterButAlloc">
			<a href="addAllocation"><img alt="Add New Allocation" width="150"
				height="33"
				src="<c:url value="/resources/images/addAlloc.png"/>"
				class="addAlloclink" /></a>
			</span>
			<%-- <span class="floatReportBut">
			<a href="reportPDFAlloc"><img alt="Generate Report" width="150"
				height="33"
				src="<c:url value="/resources/images/genReport.png"/>"
				class="genReportlink" /></a>
				</span> --%>
				<br/>
			<form:form modelAttribute = "allocation" method = "get"
				action="datefilterAlloc">
				Search by date: &nbsp;
				Start <form:input path="start_date" type="date" placeholder="2014-08-01"/> &nbsp; <!-- was required="required" -->
				End <form:input path="end_date" type="date" placeholder="2014-12-31"/>
				<input type="submit" value="Generate Web Report" />
				<input type="reset" value="Reset Fields" />
			</form:form>
			<form:form modelAttribute = "allocation" method = "get"
				action="reportPDFAlloc"><%-- was action="reportMonthPDF" and modelAttribute="project" --%>
				Monthly Report: &nbsp;
				Start <form:input id="jDate" path="start_date" type="date" placeholder="2014-08-01"/><!-- was required="required" -->
				End <form:input id="jDate" path="end_date" type="date" placeholder="2014-12-31"/>
				<input type="submit" value="Generate PDF Report" />
				<input type="reset" value="Reset Fields" />
			</form:form>
		</div>
		<br />

		<table class="sortable" id="allocPage">
			<thead>
				<tr>
					<!-- <th>Allocation Id</th> -->
					<th>Employee</th>
					<th>Project Name</th>
					<th>Percent</th>
					<th>Start Date<br/>(YYYY-MM-DD)</th>
					<th>End Date<br/>(YYYY-MM-DD)</th>
					<th class="sorttable_nosort">Controls</th>
					<!-- <th class="sorttable_nosort"></th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach var="allocation" items="${map.allocationList}">
					<tr>
						<%-- <td>${allocation.id}</td> --%>
						<td>${allocation.employee_name}</td>
						<td>${allocation.project_name}</td>
						<td>${allocation.percent}%</td>
						<td>${allocation.start_date}</td>
						<td>${allocation.end_date}</td>
						<td><a href="editAllocation?id=${allocation.id}"><img
								alt="Edit" width="85" height="29"
								src="<c:url value="/resources/images/editBut.png"/>"
								class="editlink" /></a>
						<a href="deleteAllocation?id=${allocation.id}" class="delConfirm"><img
								alt="Delete" width="85" height="29"
								src="<c:url value="/resources/images/delBut.png"/>"
								class="dellink" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
			</tfoot>
		</table>
		<div id="message-delete" title="Delete Record" class="ui-helper-hidden">
		<span class="ui-state-default">
		<span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 0 0;"></span></span>
			<div style="margin-left: 23px;">
			<p>Are you sure to delete this item?</p>
			</div>
		</div>
		<!-- center -->
	<%@include file="footer.jsp"%>
<script>
$(document).ready(function(){

	$('#allocPage').jPaginate(
			{
				'max': 10,
				'page': 1,
				'links': 'selectButtons'
			});
	
		//$('#jButton').button();
	
		$("#message").dialog({
			autoOpen: false,
			//Remove the 'X' button in the top right corner and set timeout
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
				//Remove the 'X' button in the top right corner
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
		
	})(jQuery);

/* function confirmAction()
{	
	var act = confirm("Do you want to delete this item?");
	return act;
} */
</script>
</body>
</html>