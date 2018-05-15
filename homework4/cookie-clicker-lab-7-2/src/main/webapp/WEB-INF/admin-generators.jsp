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
        <div class='row'>
            <div class='column'>
                <div class='flex-container'>
                    <form method='POST'>
                        <div>
                            <label for='GeneratorName'>Generator Name</label>
                        </div>
                        <div>
                            <input type='text' name='genname' id='GeneratorName' </div>
                            <div>
                                <label for='GeneratorDescription'>Generator Description</label>
                            </div>
                            <div>
                                <textarea name='GeneratorDescription'></textarea>
                            </div>
                            <div>
                                <div>
                                    <label for='GeneratorRate'>Generator Rate</label>
                                </div>
                                <div>
                                    <input type='number' name='genrate' id='GeneratorRate'>
                                </div>
                                <div>
                                    <label for='BaseCost'>Base Cost</label>
                                </div>
                                <div>
                                    <input type='number' name='baseecost' id='BaseCost'>
                                </div>
                                <div>
                                    <label for='UnlockAt'>Unlock At</label>
                                </div>
                                <div>
                                    <input type='number' name='unlockatt' id='UnlockAt'>
                                </div>
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
                    <th>Rate</th>
                    <th>Cost</th>
                    <th>Unlock At</th>
                    <th>Actions</th>
                </tr>
                <c:forEach items="${generators}" var="g">
                    <tr>
                        <td>${g.getName()}</td>
                        <td>${g.getDescription()}</td>
                        <td>${g.getRate()}</td>
                        <td>${g.getBaseCost()}</td>
                        <td>${g.getUnlockAt()}</td>
                        <td>
                            <a href='/generatorsedit?id=${g.getId()}'>edit</a>|
                            <a href='/generatorsremove?id=${g.getId()}'>delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
</body>

</html>