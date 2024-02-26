<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Control Room</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <h1>Welcome to Control Room</h1>
    

    <c:if test="${not empty error}">
        <p>${error}</p>
    </c:if>
    
    <form action="/registerSubmarine" method="post">
        <label for="name">Submarine Name:</label>
        <input type="text" id="name" name="name" required>
        <button type="submit">Register</button>
    </form>
    
    <h2>Submarine List</h2>
    <ul>
        <c:forEach items="${submarines}" var="submarine">
            <li>${submarine.name}
                <form action="/hideSubmarine" method="post">
                    <input type="hidden" name="name" value="${submarine.name}">
                    <button type="submit">Hide</button>
                </form>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
