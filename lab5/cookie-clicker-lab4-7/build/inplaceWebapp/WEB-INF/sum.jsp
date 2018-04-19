<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
    <ol>
    <c:forEach items="${list}" var="number">
        <li>${number}</li>
    </c:forEach>
    </ol>
    <form method="Post">
    <input type="text" name="a">
    <input type="text" name="b">
    <button>Send</button>
    </form>
</body>
</html>