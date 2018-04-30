<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Incremental Game</title>
    <link rel='stylesheet' type='text/css' href='/app.css'>
</head>

<body>
    <h1>Incremental Game Framework</h1>
    <h3>
        <a href=''>Game Information</a> |
        <a href=''>Generators</a> |
        <a href='/events'>Events</a> |
        <a href='/auth'>Logout</a>
        <div class='row'>
            <div class='column'>
                <div class='flex-container'>
                    <form method='POST'>
                        <div>
                            <label for='EventName'>Event Name</label>
                        </div>
                        <div>
                            <input type='text' name='evename' id='EventName' </div>
                            <div>
                                <label for='EventDescription'>Event Description</label>
                            </div>
                            <div>
                                <textarea name='EventDescription'></textarea>
                            </div>
                            <div>
                                <label for='TriggerName'>Trigger At</label>
                            </div>
                            <div>
                                <input type='number' name='triggname' id='TriggerNameName'>
                            </div>
                            <div>
                                <button>Submit</button>
                            </div>
                    </form>
                    </div>
                </div>
                <div class='column'></div>
            </div>
            <table border='1' cellpadding='15'>

                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Trigger At</th>
                    <th>Actions</th>
                </tr>
                <c:forEach items="${events}" var="e">
                <tr>
                    <td>${e.getName()}</td>
                    <td>${e.getDescription()}</td>
                    <td>${e.getTriggerAt()}</td>
                    <td>
                        <a href='/eventsedit?id=${e.getId()}'>edit</a>|
                        <a href='/eventsremove?id=${e.getId()}'>delete</a>
                    </td>
                </tr>
                </c:forEach>
            </table>
</body>

</html>