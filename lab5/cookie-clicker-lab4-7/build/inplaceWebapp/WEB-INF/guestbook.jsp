<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Hello GuestBook</h1>
<table border='1'>
    <c:forEach items="${guestbookentries" var="entry">
    <tr>
        <td>${entry.username}</td>
        <td>${entry.comment}</td>
        <td>
            <a href='/guestbook/edit?id=${entry.id}'> Edit</a>|
            <a href='/guestbook/delete?id=${entry.id}'> Delete</a>
        </td>
    </tr>
    </c:forEach>
</table>
<form method='POST'>
    <h2>Add comment
        <h2>
            <label for='name'>Name:</label>
            <input name='username' id='name' type='text' />
            <textarea name='comment'></textarea>
            <button>Submit</button>
</form>