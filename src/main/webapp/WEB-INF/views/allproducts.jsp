<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Discounts Enrollments</title>

	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>

</head>


<body>
	<h2>List of Discounts</h2>
	<table>
		<tr>
			<td>NAME</td><td>Joining Date</td><td>Salary</td><td>SSN</td><td></td>
		</tr>
		<c:forEach items="${products}" var="product">
			<tr>
			<td>${product.name}</td>
			<td>${product.joiningDate}</td>
			<td>${product.salary}</td>
			<td><a href="<c:url value='/edit-${product.ssn}-product' />">${product.ssn}</a></td>
			<td><a href="<c:url value='/delete-${product.ssn}-product' />">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value='/new' />">Post an Offer</a>
</body>
</html>