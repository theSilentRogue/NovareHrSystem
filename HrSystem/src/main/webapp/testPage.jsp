<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Test Page</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css"/>"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/imageClasses.css"/>"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/jPaginate-default.css"/>"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/RowTabs.css"/>"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="<c:url value="/resources/scripts/sorttable.js"/>"></script>
	<script src="<c:url value="/resources/scripts/confirmDel.js"/>"></script>
	<script src="<c:url value="/resources/scripts/jPaginate.js"/>"></script>
	<script>			
			$(document).ready(function()
			{
				$('#linkPages').jPaginate(
				{
					'max': 3,
					'page': 1,
					'links':'text'
				});
			});
			
			$(document).ready(function()
					{
						$('#selectPages').jPaginate(
						{
							'max': 5,
							'page': 2,
							'links':'select'
						});
					});
			
			$(document).ready(function()
					{
						$('#buttonPages').jPaginate(
						{
							'max': 5,
							'page': 3,
							'links':'buttons'
						});
					});
		</script>
	
</head>
<body>
	<!-- <div id="bgOne"> -->
	<div id="title">Test Page</div>
		<%@include file = "WEB-INF/jsp/navlinks.jsp" %>
	<!-- </div> -->
	<div align="center">
	<!-- center -->
		<!-- <h1>Test Page</h1> -->
		<div>
		This is where experiments come and go.
		</div>
		<hr>
		<!-- <h1>Pagination Test - Links </h1>	
		<table class="sortable" id="linkPages">
		<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
				</tr>
			</thead>
			<tfoot>
			</tfoot>
			<tbody>
				<tr>
					<td>1</td>
					<td>Jean Peplu</td>
				</tr>
				<tr>
					<td>2</td>
					<td>Marc Assin</td>
				</tr>
				<tr>
					<td>3</td>
					<td>Pierre Defeu</td>
				</tr>
				<tr>
					<td>4</td>
					<td>Justine Gout</td>
				</tr>
				<tr>
					<td>5</td>
					<td>Vincent Timettre</td>
				</tr>
				<tr>
					<td>6</td>
					<td>Alex Terieur</td>
				</tr>
				<tr>
					<td>7</td>
					<td>Pat Bol</td>
				</tr>
				<tr>
					<td>8</td>
					<td>Th&eacute;o Blig&eacute;</td>
				</tr>
				<tr>
					<td>9</td>
					<td>Mona Bruti</td>
				</tr>
				<tr>
					<td>10</td>
					<td>Ada Core</td>
				</tr>
				<tr>
					<td>11</td>
					<td>Adam Zinmoi</td>
				</tr>
				<tr>
					<td>12</td>
					<td>Agathe Zeblouz</td>
				</tr>
				<tr>
					<td>13</td>
					<td>Homer Dalor</td>
				</tr>
				<tr>
					<td>14</td>
					<td>Akim Embet</td>
				</tr>
				<tr>
					<td>15</td>
					<td>Alain Provist</td>
				</tr>
				<tr>
					<td>16</td>
					<td>Jean Peplu</td>
				</tr>
				<tr>
					<td>17</td>
					<td>Marc Assin</td>
				</tr>
				<tr>
					<td>18</td>
					<td>Pierre Defeu</td>
				</tr>
				<tr>
					<td>19</td>
					<td>Justine Gout</td>
				</tr>
				<tr>
					<td>20</td>
					<td>Vincent Timettre</td>
				</tr>
				<tr>
					<td>21</td>
					<td>Alex Terieur</td>
				</tr>
				<tr>
					<td>22</td>
					<td>Pat Bol</td>
				</tr>
				<tr>
					<td>23</td>
					<td>Th&eacute;o Blig&eacute;</td>
				</tr>
				<tr>
					<td>24</td>
					<td>Mona Bruti</td>
				</tr>
				<tr>
					<td>25</td>
					<td>Ada Core</td>
				</tr>
				<tr>
					<td>26</td>
					<td>Adam Zinmoi</td>
				</tr>
				<tr>
					<td>27</td>
					<td>Agathe Zeblouz</td>
				</tr>
				<tr>
					<td>28</td>
					<td>Homer Dalor</td>
				</tr>
				<tr>
					<td>29</td>
					<td>Akim Embet</td>
				</tr>
				<tr>
					<td>30</td>
					<td>Alain Provist</td>
				</tr>
				<tr>
					<td>31</td>
					<td>Jean Peplu</td>
				</tr>
				<tr>
					<td>32</td>
					<td>Marc Assin</td>
				</tr>
				<tr>
					<td>33</td>
					<td>Pierre Defeu</td>
				</tr>
				<tr>
					<td>34</td>
					<td>Justine Gout</td>
				</tr>
				<tr>
					<td>35</td>
					<td>Vincent Timettre</td>
				</tr>
				<tr>
					<td>36</td>
					<td>Alex Terieur</td>
				</tr>
				<tr>
					<td>37</td>
					<td>Pat Bol</td>
				</tr>
				<tr>
					<td>38</td>
					<td>Th&eacute;o Blig&eacute;</td>
				</tr>
				<tr>
					<td>39</td>
					<td>Mona Bruti</td>
				</tr>
				<tr>
					<td>40</td>
					<td>Ada Core</td>
				</tr>
				<tr>
					<td>41</td>
					<td>Adam Zinmoi</td>
				</tr>
				<tr>
					<td>42</td>
					<td>Agathe Zeblouz</td>
				</tr>
				<tr>
					<td>43</td>
					<td>Homer Dalor</td>
				</tr>
				<tr>
					<td>44</td>
					<td>Akim Embet</td>
				</tr>
				<tr>
					<td>45</td>
					<td>Alain Provist</td>
				</tr>
			</tbody>
		</table>
		
		<h1>Pagination Test - Select Page Drop Down </h1>	
		<table class="sortable" id="selectPages">
		<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
				</tr>
			</thead>
			<tfoot>
			</tfoot>
			<tbody>
				<tr>
					<td>1</td>
					<td>Jean Peplu</td>
				</tr>
				<tr>
					<td>2</td>
					<td>Marc Assin</td>
				</tr>
				<tr>
					<td>3</td>
					<td>Pierre Defeu</td>
				</tr>
				<tr>
					<td>4</td>
					<td>Justine Gout</td>
				</tr>
				<tr>
					<td>5</td>
					<td>Vincent Timettre</td>
				</tr>
				<tr>
					<td>6</td>
					<td>Alex Terieur</td>
				</tr>
				<tr>
					<td>7</td>
					<td>Pat Bol</td>
				</tr>
				<tr>
					<td>8</td>
					<td>Th&eacute;o Blig&eacute;</td>
				</tr>
				<tr>
					<td>9</td>
					<td>Mona Bruti</td>
				</tr>
				<tr>
					<td>10</td>
					<td>Ada Core</td>
				</tr>
				<tr>
					<td>11</td>
					<td>Adam Zinmoi</td>
				</tr>
				<tr>
					<td>12</td>
					<td>Agathe Zeblouz</td>
				</tr>
				<tr>
					<td>13</td>
					<td>Homer Dalor</td>
				</tr>
				<tr>
					<td>14</td>
					<td>Akim Embet</td>
				</tr>
				<tr>
					<td>15</td>
					<td>Alain Provist</td>
				</tr>
			</tbody>

		</table>
		
		<h1>Pagination Test - Button Nav</h1>	
		<table class="sortable" id="buttonPages">
		<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
				</tr>
			</thead>
			<tfoot>
			</tfoot>
			<tbody>
				<tr>
					<td>1</td>
					<td>Jean Peplu</td>
				</tr>
				<tr>
					<td>2</td>
					<td>Marc Assin</td>
				</tr>
				<tr>
					<td>3</td>
					<td>Pierre Defeu</td>
				</tr>
				<tr>
					<td>4</td>
					<td>Justine Gout</td>
				</tr>
				<tr>
					<td>5</td>
					<td>Vincent Timettre</td>
				</tr>
				<tr>
					<td>6</td>
					<td>Alex Terieur</td>
				</tr>
				<tr>
					<td>7</td>
					<td>Pat Bol</td>
				</tr>
				<tr>
					<td>8</td>
					<td>Th&eacute;o Blig&eacute;</td>
				</tr>
				<tr>
					<td>9</td>
					<td>Mona Bruti</td>
				</tr>
				<tr>
					<td>10</td>
					<td>Ada Core</td>
				</tr>
				<tr>
					<td>11</td>
					<td>Adam Zinmoi</td>
				</tr>
				<tr>
					<td>12</td>
					<td>Agathe Zeblouz</td>
				</tr>
				<tr>
					<td>13</td>
					<td>Homer Dalor</td>
				</tr>
				<tr>
					<td>14</td>
					<td>Akim Embet</td>
				</tr>
				<tr>
					<td>15</td>
					<td>Alain Provist</td>
				</tr>
			</tbody>

		</table> -->
	<!-- center -->
	</div>
	<%@include file = "WEB-INF/jsp/footer.jsp" %>
</body>
</html>