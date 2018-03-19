<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
}

li {
	float: left;
}

li a, .dropbtn {
	display: inline-block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li a:hover, .dropdown:hover .dropbtn {
	background-color: red;
}

li.dropdown {
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f9f9f9;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
	text-align: left;
}

.dropdown-content a:hover {
	background-color: #f1f1f1
}

.dropdown:hover .dropdown-content {
	display: block;
}
</style>
</head>
<body>

	<ul>
	 <j:forEach var="category" items="${categories}">
	 ${category.name}
	<li class="dropdown"><a href="javascript:void(0)" class="dropbtn">${categories.name}</a>
	<j:forEach var="product" items="${category.products}" >
	${Product.name}
			<div class="dropdown-content">
				<a href="#">TV</a> <a href="#">Mobile</a> <a href="#">Laptop</a>
			</div>
			</j:forEach></li>
	</j:forEach>
	</ul>
	

		<!--  <li class="dropdown"><a href="javascript:void(0)" class="dropbtn">Electronics</a>
			<div class="dropdown-content">
				<a href="#">TV</a> <a href="#">Mobile</a> <a href="#">Laptop</a>
			</div></li>
		<li class="dropdown"><a href="javascript:void(0)" class="dropbtn">Books</a>
			<div class="dropdown-content">
				<a href="#">Java</a> <a href="#">HTML</a> <a href="#">AngularJS</a>
			</div></li>

		<li class="dropdown"><a href="javascript:void(0)" class="dropbtn">Furniture</a>
			<div class="dropdown-content">
				<a href="#">Chairs</a> <a href="#">Tables</a> <a href="#">Beds</a>
			</div></li>-->
	


</body>
</html>












