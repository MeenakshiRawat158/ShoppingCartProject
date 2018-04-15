<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="card">
		<div class="container-fliud">
			<div class="wrapper row">
				<div class="preview col-md-6">

					<div class="preview-pic tab-content">
						<div class="tab-pane active" id="pic-1">
							<img
								src="<c:url value="/images/${mainproduct.id}.jpg" />" />
						</div>
					</div>
				</div>
				<div class="details col-md-6">
					<h3 class="product-title" style="color: black">${mainproduct.name}</h3>

					<p class="product-description" style="color: black">${mainproduct.description}</p>
					<h4 class="price" style="color: black">
						current price: <span>&#x20b9; ${mainproduct.price}</span>
					</h4>
					<c:if test="${userRole == 'ROLE_CUSTOMER'}">
					<div class="action">
						<a class="add-to-cart btn btn-default" type="button"
							href="${pageContext.servletContext.contextPath}/cart/add/${mainproduct.id}">Add to cart</a>
					</div>
					</c:if>
					
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>