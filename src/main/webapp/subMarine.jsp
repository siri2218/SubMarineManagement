<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Sub Marine</title>
</head>
<body>

	<c:if test="${not empty message}">
		<p>${message}</p>
	</c:if>

	<c:if test="${not empty error}">
		<p>${error}</p>
	</c:if>
	<h1>Sub Marines</h1>
	<h2>
		<form action="subMarine">
			<label for="name">Submarine Name:</label> <input type="text"
				id="name" name="name" required>
			<button type="submit">Register</button>
		</form>
	</h2>

	<h2>List of SubMarines</h2>
	<ul>
		<c:forEach items="${submarinelist}" var="submarine">
			<li>${submarine.name}<c:if test="${not submarine.isHidden}">
					<form action="/hideSubmarine">
						<input type="hidden" name="name" value="${submarine.name}">
						<button type="submit">Hide</button>
					</form>
				</c:if></li>
		</c:forEach>
	</ul>


</body>
</html>