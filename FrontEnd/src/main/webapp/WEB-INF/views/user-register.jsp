
<form action="${pageContext.servletContext.contextPath}/createuser" method="POST">
	<div class="form-group">
		<label for="name">Name</label> <input type="text" class="form-control"
			name="name" id="name" placeholder="First Name" value="${name}">
	</div>
	<div class="form-check form-check-inline">
		<input class="form-check-input" type="radio" name="exampleRadios"
			id="exampleRadios1" value="option1" checked> <label
			class="form-check-label" for="exampleRadios1"> Male </label>
	</div>
	<div class="form-check form-check-inline">
		<input class="form-check-input" type="radio" name="exampleRadios"
			id="exampleRadios2" value="option2"> <label
			class="form-check-label" for="exampleRadios2"> Female </label>
	</div>
	<div class="form-group">
		<label for="phoneNo">Phone No</label> <input type="text"
			class="form-control" name="mobile" id="phoneNo"
			placeholder="+91-xxxxxxxxxx" value="${mobile}">
	</div>
	<div class="form-group">
		<label for="emailAddress">Email Address</label> <input type="email"
			class="form-control" id="emailAddress" name="email"
			placeholder="jodn@doe.com" value="${email} ">
	</div>
	<div class="form-row">
		<div class="col">
			<label for="pass1">Password</label> <input type="password"
				class="form-control" id="pass1" placeholder="password"
				name="password1">
		</div>
		<div class="col">
			<label for="pass2">Confirm Password</label> <input type="password"
				class="form-control" id="pass2" placeholder="confirm password"
				name="password2">
		</div>
	</div>
	<div class="form-group">
		<label for="address">Address</label>
		<textarea class="form-control" id="address" rows="3" name="address">${address}</textarea>
	</div>
	<button type="submit" class="btn btn-primary">Submit</button>
</form>
