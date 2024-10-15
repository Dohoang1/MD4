<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Máy tính</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Máy tính</h1>
    <form action="${pageContext.request.contextPath}/calculate" method="post">
        <div class="row mb-3">
            <div class="col">
                <input type="number" step="any" class="form-control" name="num1" value="${num1}" placeholder="Số thứ nhất" required>
            </div>
            <div class="col">
                <input type="number" step="any" class="form-control" name="num2" value="${num2}" placeholder="Số thứ hai" required>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col">
                <button type="submit" class="btn btn-primary w-100" name="operator" value="+">Cộng(+)</button>
            </div>
            <div class="col">
                <button type="submit" class="btn btn-primary w-100" name="operator" value="-">Trừ(-)</button>
            </div>
            <div class="col">
                <button type="submit" class="btn btn-primary w-100" name="operator" value="*">Nhân(X)</button>
            </div>
            <div class="col">
                <button type="submit" class="btn btn-primary w-100" name="operator" value="/">Chia(/)</button>
            </div>
        </div>
    </form>

    <c:if test="${not empty error}">
        <div class="alert alert-danger mt-3">
                ${error}
        </div>
    </c:if>

    <c:if test="${not empty result}">
        <div class="alert alert-success mt-3">
            Kết quả ${operationName}: ${result}
        </div>
    </c:if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>