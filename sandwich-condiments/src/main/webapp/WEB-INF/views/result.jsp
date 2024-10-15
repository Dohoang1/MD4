<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Kết Quả Lựa Chọn</title>
</head>
<body>
<h2>Các Gia Vị Đã Chọn</h2>
<ul>
    <c:forEach var="condiment" items="${selectedCondiments}">
        <li>${condiment}</li>
    </c:forEach>
</ul>
<a href="/condiments">Quay lại</a>
</body>
</html>