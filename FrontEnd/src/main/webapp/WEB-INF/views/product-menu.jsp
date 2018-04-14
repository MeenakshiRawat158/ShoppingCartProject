<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul class="nav navbar-nav  " role="tablist">
	<c:forEach items="${categoryList}" var="category">
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="${pageContext.servletContext.contextPath}/product/${category.id}">
				${category.name} <span class="caret"></span>
		</a> <c:if test="${not empty category.products}">
				<ul class="dropdown-menu" role="menu">

					<c:forEach items="${category.products}" var="product">

						<li><a href="${pageContext.servletContext.contextPath}/display_product/get/${product.id}">${product.name}</a></li>

					</c:forEach>
					<li><a href="${pageContext.servletContext.contextPath}/category/${category.id}">All Products</a></li>
				</ul>
			</c:if></li>
	</c:forEach>

</ul>