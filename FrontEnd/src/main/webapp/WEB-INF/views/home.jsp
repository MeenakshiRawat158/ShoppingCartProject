<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE>
<html>
<head>
<title>Orkeios Shopping network</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<spring:url value="/css/main.css" var="crunchifyCSS" />
<link href="${crunchifyCSS}" rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<jsp:include page="product-menu.jsp"></jsp:include>
			<jsp:include page="user-menu.jsp"></jsp:include>
		</div>
	</nav>
	<div class="container">
		<c:if test="${not empty msg }">
			<div class="alert alert-success" role="alert">${msg}</div>
		</c:if>
		<c:if test="${not empty errorMessage }">
			<div class="alert alert-danger" role="alert">${errorMessage}</div>
		</c:if>
		<!-- Show login page when user clicked login in -->
		<c:if test="${isUserClickedLogin==true}">
			<c:if test="${empty userRole}">
				<jsp:include page="user-login.jsp"></jsp:include>
			</c:if>
		</c:if>

		<!-- Show login page when user clicked login in -->
		<c:if test="${isUserClickedRegister==true}">
			<c:if test="${empty userRole}">
				<jsp:include page="user-register.jsp"></jsp:include>
			</c:if>
		</c:if>

		<c:if test="${isAdminClickedCategories==true}">
			<jsp:include page="admin-category.jsp"></jsp:include>
		</c:if>

		<c:if test="${isAdminClickedProduct==true}">
			<jsp:include page="admin-product.jsp"></jsp:include>
		</c:if>

		<c:if test="${isAdminClickedSupplier==true}">
			<jsp:include page="admin-supplier.jsp"></jsp:include>
		</c:if>

		<c:if test="${userClickedProduct == true }">
			<jsp:include page="product-view.jsp"></jsp:include>
		</c:if>
		<c:if test="${userClickedCart == true }">
			<jsp:include page="cart-view.jsp"></jsp:include>
		</c:if>
		<c:if test="${userClickedCheckout == true }">
			<jsp:include page="cart-checkout.jsp"></jsp:include>
		</c:if>
		<c:if test="${userClickedPlaced == true }">
			<jsp:include page="cart-checkout-complete.jsp"></jsp:include>
		</c:if>
		<c:if test="${userClickedGetProductList == true }">
			<jsp:include page="product-list.jsp"></jsp:include>
		</c:if>

	</div>
</body>
</html>