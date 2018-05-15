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
        <a href='/generators'>Generators</a> |
        <a href='/events'>Events</a> |
        <a href='/auth'>Logout</a>
    </h3>
        <form method='POST'>
            <label for='GeneratorName'>Generator Name</label>
            <input type='text' name='genname' id='GeneratorName' value='${g1.getName()}'>
            <label for='GeneratorDescription'>Generator Description</label>
            <textarea name='GeneratorDescription'>${g1.getDescription()}</textarea>
            <label for='GeneratorRate'>Generator Rate</label>
            <input type='number' name='genrate' id='GeneratorRate' value='${g1.getRate()}'>
            <label for='BaseCost'>Base Cost</label>
            <input type='number' name='baseecost' id='BaseCost' value='${g1.getBaseCost()}'>
            <label for='UnlockAt'>Unlock At</label>
            <input type='number' name='unlockatt' id='UnlockAt' value='${g1.getUnlockAt()}'>
            <button>Submit</button>
        </form>
</body>

</html>