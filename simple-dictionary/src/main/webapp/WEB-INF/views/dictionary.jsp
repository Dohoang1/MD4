<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Từ điển đơn giản</title>
</head>
<body>
<h1>Từ điển đơn giản</h1>
<form action="/search" method="post">
    <input type="text" name="word" placeholder="Nhập từ cần tìm" required>
    <button type="submit">Tìm kiếm</button>
</form>

<c:if test="${not empty result}">
    <h2>Kết quả:</h2>
    <p>${result}</p>
</c:if>
</body>
</html>