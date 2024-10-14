<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customer List</title>
</head>
<body>
<h1>Customer List</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Address</th>
    </tr>
    <c:forEach items="${customers}" var="customer">
        <tr>
            <td>${customer.id}</td>
            <td><a href="/details/${customer.id}">${customer.name}</a></td>
            <td>${customer.email}</td>
            <td>${customer.address}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>