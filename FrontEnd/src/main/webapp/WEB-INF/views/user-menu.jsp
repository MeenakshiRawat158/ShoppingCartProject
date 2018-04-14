
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:choose>
	<c:when test="${empty isUserLoggedIn}">
		<ul class="nav navbar-nav navbar-right">
		<li><a
				href="${pageContext.servletContext.contextPath}/home"><span
					class="glyphicon glyphicon-home"></span> Home</a></li>
			<li><a
				href="${pageContext.servletContext.contextPath}/registration"><span
					class="glyphicon glyphicon-user"></span> Sign Up</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/login"><span
					class="glyphicon glyphicon-log-in"></span> Login</a></li>
		</ul>
	</c:when>
	<c:otherwise>
		<ul class="nav navbar-nav navbar-right">
		<li><a
				href="${pageContext.servletContext.contextPath}/home"><span
					class="glyphicon glyphicon-user"></span> Home</a></li>
			<c:if test="${fn:contains(userRole, 'ROLE_ADMIN')}">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> Manage <span
						class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">


						<li><a
							href="${pageContext.servletContext.contextPath}/manage_category">Manage Category</a></li>
						<li><a
							href="${pageContext.servletContext.contextPath}/manage_product">Manage Product</a></li>
						<li><a
							href="${pageContext.servletContext.contextPath}/manage_supplier">Manage Supplier</a></li>


					</ul></li>

			</c:if>
			<c:if test="${fn:contains(userRole, 'ROLE_CUSTOMER')}">
				<li><a href="${pageContext.servletContext.contextPath}/myCart"><span
						class="glyphicon glyphicon-shopping-cart"></span></a></li>

			</c:if>

			<li><a
				href="${pageContext.servletContext.contextPath}/j_spring_security_logout"><span
					class="glyphicon glyphicon-log-out"></span> Logout</a></li>
		</ul>

	</c:otherwise>
</c:choose>