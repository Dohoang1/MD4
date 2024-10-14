<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Currency Converter</title>
</head>
<body>
<h1>Currency Converter</h1>
<form action="/converter" method="post">
    <label for="amount">Amount:</label>
    <input type="number" id="amount" name="amount" required step="0.01"><br><br>

    <label for="fromCurrency">From:</label>
    <select id="fromCurrency" name="fromCurrency">
        <option value="USD">USD</option>
        <option value="VND">VND</option>
    </select><br><br>

    <label for="toCurrency">To:</label>
    <select id="toCurrency" name="toCurrency">
        <option value="VND">VND</option>
        <option value="USD">USD</option>
    </select><br><br>

    <input type="submit" value="Convert">
</form>

<% if (request.getAttribute("result") != null) { %>
<h2>Result: <%= request.getAttribute("result") %></h2>
<% } %>
</body>
</html>