<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
    <h1>${test}</h1>
    <h2>${true}|${false}</h2>
    <c:out value="hello jstl"/>
    <ol>
    <c:forEach items="${list}" var="number">
       <li> ${number}</li>
    </c:forEach>
    </ol>
    <form method="POST">
    <input type="text" name="a">
    <input type="text" name="b">
    <button>Submit</button>
    </form>
</body>
</html>