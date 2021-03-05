<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Project showcase</title>
<link rel="stylesheet" type="text/css" href="css/showcase.css">
</head>
<body>
<h3>Categories</h3>
<table border='1'>
<c:forEach var="category" items="${categories}" varStatus='i'>
<tr>
<td>${i.count}</td>
<td>${category.title}</td>
</tr>
</c:forEach>
</body>
</html>