<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2><a href="managecategories">Manage categories</a>
<a href="managesupplier">Manage suppliers</a>
<a href="manageproducts">Manage products</a></h2>
<br>
<c:if test="${isAdminClickedMangageCategories==true}">
<jsp:include
</c:if>



<jsp:include page="category.jsp"></jsp:include>
<jsp:include page="product.jsp"></jsp:include>
<jsp:include page="supplier.jsp"></jsp:include>

</body>
</html>