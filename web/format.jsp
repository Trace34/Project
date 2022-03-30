<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Органайзер событий</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: #2984c9">
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()+ "/"%>"
                   class="nav-link">Главная страница</a></li>
        </ul>
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()+ "/list"%>"
                   class="nav-link">Список событий</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <%--@elvariable id="event" type="ru.vsu.afanasenko.models.Event"--%>
            <c:if test="${event != null}">
            <form action="${"update"}" method="post"></c:if>
                <c:if test="${event == null}">
                <form action="${"insert"}" method="post"></c:if>
                    <caption>
                        <h2>
                            <c:if test="${event == null}">Добавление события</c:if>
                            <c:if test="${event != null}">Редактирование события</c:if>
                        </h2>
                    </caption>
                    <br/>
                </form>
            </form>
            <form action="${"format"}" method="post">
                <c:if test="${event != null}">
                    <fieldset class="form-group">
                        <label style="width: 120px; display: inline-block">ID: </label><label>
                        <input type="number"
                               style="width: 200px; height: 30px;"
                               value="<c:out value='${event.id}'/>" class="form-control"
                               name="id"
                               readonly>
                    </label>
                    </fieldset>
                </c:if>
                <label>
                    <label style="width: 120px; display: inline-block">Тип события: </label><label>
                    <select name="types" style="width: 200px; height: 30px;">
                        <option value="birthday">День рождения</option>
                        <option value="meeting">Встреча</option>
                    </select>
                </label>
                </label>
                <br>
                <div class="container text-right">
                    <input type="submit" style="width: 150px; height: 40px" class="btn btn-success" value="Продолжить"/>
                </div>
                <br>
            </form>
            <form action="${"list"}" method="post">
                <div class="container text-right">
                    <input type="submit" style="width: 150px; height: 40px" class="btn btn-info" value="Вернуться"/>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
