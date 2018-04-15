<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
@media screen and (min-width: 1024px) {
	myNewDivHeight {
		height: 250px;
	}
	.headline h2, .headline p {
		display: inline;
		vertical-align: top;
		font-family: 'Open Sans', sans-serif;
		font-size: 20px;
		line-height: 28px;
	}
}

p {
	font-size: 14px;
}

.thumbnail {
	border: 1px solid #a91c1c;
	border-radius: 10px;
}
</style>
</head>
<div class="container">

	<div id="products" class="row list-group">


		<div class="bs-example" style="width: 500px; color: #1c26a5;">
			<div class="panel panel-info">
				<div class="panel-content">
					<div class="headline">
						<p style="color: #1f1c1c";>CATEGORY:</p>
						<h2>${categoryName}</h2>
					</div>
				</div>
			</div>
		</div>
		<c:forEach var="productItem" items="${productsList}">

			<div class="item  col-xs-4 col-lg-4">
				<div class="thumbnail">
					<img class="group list-group-image" style="height: 199px;"
						src="<c:url value="/images/${productItem.id}.jpg" />"
						alt="" />

					<div class="caption">
						<h4 class="group inner list-group-item-heading">
							${productItem.name}</h4>
						<p class="group inner list-group-item-text">
							${productItem.description }.</p>
						<div class="row">
							<div class="col-xs-12 col-md-6">
								<p class="lead">&#x20b9; ${productItem.price}</p>
							</div>
							<c:if test="${userRole == 'ROLE_CUSTOMER'}">
								<div class="col-xs-12 col-md-6">
									<a class="btn btn-success"
										href="${pageContext.servletContext.contextPath}/cart/add/${productItem.id }">Add
										to cart</a>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>

	</div>
</div>
