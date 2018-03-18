<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="page-header.jsp"/>
<meta charset="ISO-8859-1">
<title>Orkeios Shopping Network</title>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
		<h2 class="text-primary"> Login to Orkeios Shopping Network</h2>
		 <span class="text-danger">${errorMessage}</span> 
		</div>
		<form action="validate" method="post">
			<div class="form-group">
				<label for="email">Email address:</label> <input type="email"
					class="form-control" id="email" name="email">
			</div>
			<div class="form-group">
				<label for="pwd">Password:</label> <input type="password"
					class="form-control" id="password" name="password">
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>

	</div>

</body>
</html>