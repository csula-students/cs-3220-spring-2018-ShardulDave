<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Incremental Game</title>
</head>
<link rel='stylesheet' type='text/css' href='/app.css'>
</head>

<body>
    <h1>Incremental Game Framework</h1>
    <h3>
        <a href=''>Game Information</a> |
        <a href=''>Generators</a> |
        <a href='/admin/events'>Events</a>
        <form method='POST'>
            <label for='EventName'>Event Name</label>
            <input type='text' name='evename' id='EventName' value='${e1.getName()}'>
            <label for='EventDescription'>Event Description</label>
            <textarea name='EventDescription'>${e1.getDescription()}</textarea>
            <label for='TriggerName'>Trigger At</label>
            <input type='number' name='triggname' id='TriggerNameName' value='${e1.getTriggerAt()}'>
            <button>Edit</button>
        </form>
</body>

</html>