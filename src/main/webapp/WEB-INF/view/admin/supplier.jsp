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
<form action="supplier/save/" method="post">
	<table>
		<tr>
			<td>ID</td>
			<td><input type="text" name="id" value="${selectedSupplier.id}"></td>
		</tr>
		<tr>
			<td>Address</td>
			<td><input type="text" name="address" value="${selectedSupplier.address}"></td>
		</tr>
		<tr>
			<td>Name</td>
			<td><input type="text" name="name" value="${selectedSupplier.name}"></td>
		</tr>
		<tr>
			<td><input type="submit" value="create supplier"></td>
		</tr>
	</table>
	</form>
	<div>
		
		<table>
			<caption>Suppliers</caption>
			<tr>
				<th>Supplier ID</th>
				<th>Supplier Address</th>
				<th>Supplier Name</th>
				<th>Action</th>
			</tr>
			<c:forEach var="supplier" items="${suppliers}">
				<tr>
					<td>${supplier.id}</td>
					<td>${supplier.address}</td>
					<td>${supplier.name}</td>
					<td>
					<a href="supplier/delete/?id=${supplier.id}">Delete</a>
					<a href="supplier/edit/?id=${supplier.id}">Edit</a>
					
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>