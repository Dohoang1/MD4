<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Details</title>
</head>
<body>
<h1>Customer Details</h1>

<form action="/update" method="post">
    <input type="hidden" name="id" value="${customer.id}">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${customer.name}"><br><br>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="${customer.email}"><br><br>
    <label for="address">Address:</label>
    <input type="text" id="address" name="address" value="${customer.address}"><br><br>
    <input type="submit" value="Update">
</form>

<br>
<a href="/customers">Back to List</a>

</body>
</html>