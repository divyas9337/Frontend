<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li a:hover {
	background-color: red;
}
</style>
</head>
<body>
	<ul>
		<j:forEach var="category" items="${categories}">
	 ${category.name}
	<li><a href="#">${categories.name}</a>
			<j:forEach var="product" items="${category.products}">
	${Product.name}
			</j:forEach></li>
		</j:forEach>
	</ul>
</body>
</html>












