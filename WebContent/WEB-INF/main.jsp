<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<title>Main Page</title>
</head>
<body>
	<nav class="navbar navbar-default"> 
	
		<ul class="nav nav-tabs">
			<li class="active"><a aria-expanded="false" href="#students" data-toggle="tab">Students</a></li>
			<li><a aria-expanded="true" href="#course" data-toggle="tab">Course</a></li>
			<ul class="nav navbar-nav navbar-right" style="margin-right: 30px;">
				<li><a href="AddStudent">Add Student</a></li>
				<li><a href="AddCourse">Add Course</a></li>
				<li><a href="#">Add Grade</a></li>
			</ul>
	</ul>
	
		<div class="tab-content" id="myTabContent">
			<div class="tab-pane fade active in" id="students">
				<table class="table table-striped table-hover ">
				 <thead>
					    <tr>
					      <th>Id</th>
					      <th>Name</th>
					      <th>Start Year</th>
					      <th>Admission Grade</th>
					    </tr>
					  </thead>
					  <tbody>
					<c:forEach items="${students}" var="student">
					    <tr>
					    	<td><c:out value="${student.id}"/></td>  
					    	<td><c:out value="${student.name}"/></td>  
					    	<td><c:out value="${student.startingYear}"/></td>  
					    	<td><c:out value="${student.admissionGrade}"/></td>  
					    </tr>
					</c:forEach>
					  </tbody> 
					</table> 
			</div>
			<div class="tab-pane fade" id="course">
				<p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla
					single-origin coffee squid. Exercitation +1 labore velit, blog
					sartorial PBR leggings next level wes anderson artisan four loko
					farm-to-table craft beer twee. Qui photo booth letterpress, commodo
					enim craft beer mlkshk aliquip jean shorts ullamco ad vinyl cillum
					PBR. Homo nostrud organic, assumenda labore aesthetic magna delectus
					mollit.</p>
			</div>
		</div>
	</nav>
	
</body>
</html>