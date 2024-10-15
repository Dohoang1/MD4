<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chọn Gia Vị</title>
</head>
<body>
<h2>Chọn Gia Vị cho Sandwich</h2>
<form action="/save" method="post">
    <input type="checkbox" name="condiment" value="Ketchup"> Ketchup<br>
    <input type="checkbox" name="condiment" value="Mustard"> Mustard<br>
    <input type="checkbox" name="condiment" value="Mayo"> Mayo<br>
    <input type="checkbox" name="condiment" value="Pickles"> Pickles<br>
    <input type="checkbox" name="condiment" value="Onions"> Onions<br>
    <input type="submit" value="Lưu Lựa Chọn">
</form>
</body>
</html>