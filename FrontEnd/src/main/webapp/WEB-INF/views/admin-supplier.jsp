
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<h4 style="color: black" class="red-text text-center">Supplier
		Management</h4>

	<div class="container" style="color: black">
		<div class="panel panel-primary">
			<div class="panel-heading">Supplier Form</div>
			<div class="panel-body">
				<c:choose>
					<c:when test="${empty supplier.id}">
						<c:url var="addAction" value="manage_supplier_create"></c:url>
					</c:when>
					<c:otherwise>
						<c:url var="addAction" value="manage_supplier_update"></c:url>
					</c:otherwise>
				</c:choose>

				<form action="${pageContext.servletContext.contextPath}/${addAction}" method="post" class="form-inline">
					<div class="form-group">
						<div class="col-xs-8">
							<label for="id" class="col-xs-4 control-label">ID:</label>
							<c:choose>
								<c:when test="${empty supplier.id}">
									<input type="text" class="form-control" name="id">
								</c:when>
								<c:otherwise>

									<input type="text" class="form-control" value="${supplier.id}"
										readonly="readonly" disabled="disabled" name="id">
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-8">
							<label for="name" class="col-xs-4 control-label">Name:</label>
							<c:choose>
								<c:when test="${empty supplier.id}">
									<input class="form-control" type="text" name="name">

								</c:when>
								<c:otherwise>
									<input class="form-control" type="text" name="name"
										value="${supplier.name}">

								</c:otherwise>
							</c:choose>

						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-8">
							<label for="description" class="col-xs-4 control-label">Description:</label>
							<c:choose>
								<c:when test="${empty supplier.id}">
									<input class="form-control" type="text" name="description">
								</c:when>
								<c:otherwise>
									<input class="form-control" type="text" name="description"
										value="${supplier.description}">

								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-8">
							<label for="description" class="col-xs-4 control-label">Address:</label>
							<c:choose>
								<c:when test="${empty supplier.address}">
									<input class="form-control" type="text" name="address">
								</c:when>
								<c:otherwise>
									<input class="form-control" type="text" name="address"
										value="${supplier.address}">

								</c:otherwise>
							</c:choose>
						</div>
					</div>


					<c:choose>
						<c:when test="${empty supplier.id}">
							<input type="submit" value="Publish">
						</c:when>
						<c:otherwise>
							<input type="submit" value="Update">
						</c:otherwise>
					</c:choose>
				</form>
			</div>
		</div>
	</div>
	<div class="container" style="color: black">
		<table border="2" class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>#</th>
					<th>ID</th>
					<th>Name</th>
					<th>Description</th>
					<th>Address</th>
					<th>Action</th>
				</tr>
			</thead>
			<c:set var="count" value="0" scope="page" />
			<c:forEach var="supplierItem" items="${supplierList}">
				<c:set var="count" value="${count + 1}" scope="page" />
				<tr>
					<th scope="row">${count}</th>
					<td>${supplierItem.id}</td>
					<td>${supplierItem.name}</td>
					<td>${supplierItem.description}</td>
					<td>${supplierItem.address}</td>
					<td><a class="btn btn-primary"
						onclick="return confirm('Are you sure you want to edit this supplier?');"
						href="<c:url value='manage_supplier_edit/${supplierItem.id}' />">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							Edit
					</a> <a class="btn btn-primary"
						onclick="return confirm('Are you sure you want to delete this Supplier?');"
						href="<c:url value='manage_supplier_delete/${supplierItem.id}' />">
							<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
							Delete
					</a> </nobr></td>
				</tr>
			</c:forEach>
		</table>
	</div>
