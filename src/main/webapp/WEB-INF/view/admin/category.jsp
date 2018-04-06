<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
${successMessage} ${errorMessage}


<html>
<head>
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	background-color: #f08080;
}
th, td {
	padding: 15px;
	text-align: left;
}

</style>
		</head>
<body>
	<form action="category/save/" method="post">
		<table>
			<tr>
				<td>ID</td>
				<td><input type="text" name="id" value="${selectedCategory.id}">
			</tr>
			<tr>
				<td>Description</td>
				<td><input type="text" name="description" value="${selectedCategory.description}"></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name" value="${selectedCategory.name}"></td>
			</tr>
			<tr>
				<td><input type="submit" value="create category" /></td>
			</tr>
		</table>
	</form>
	<div>
		
		<table>
			<caption>Categories</caption>
			<tr>
				<th>Category ID</th>
				<th>Category Description</th>
				<th>Category Name</th>
				<th>Action</th>
			</tr>
			<c:forEach var="category" items="${categories}">
				<tr>
					<td>${category.id}</td>
					<td>${category.description}</td>
					<td>${category.name}</td>
					<td>
					<a href="category/delete/?id=${category.id}">Delete</a>
					<a href="category/edit/?id=${category.id}">Edit</a>
					
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>

</html>