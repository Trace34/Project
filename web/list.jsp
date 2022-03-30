<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<div class="row">
    <div class="container">
        <h3 class="text-center">Список событий</h3>
        <hr>
        <div class="container text-right">
            <a href="<%=request.getContextPath()+ "/create"%>"
               style="width: 150px; height: 40px"
               class="btn btn-success">Добавить</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Дата</th>
                <th>Время</th>
                <th>Описание</th>
                <th>Подробнее</th>
                <th>Действия</th>
            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="events" type="java.util.List"--%>
            <c:forEach var="event" items="${events}">
                <tr>
                    <td><c:out value="${event.id}"/></td>
                    <td><c:out value="${event.date}"/></td>
                    <td><c:out value="${event.time}"/></td>
                    <td><c:out value="${event.description}"/></td>
                    <td>
                        <c:forEach var="arg" items="${event.getAttributes()}">
                            <c:out value="${arg}"/>
                            <br>
                        </c:forEach>
                    </td>
                    <td>
                        <a href=${"edit?id="}<c:out value='${event.id}'/>>Редактировать</a>
                        <br>
                        <a href=${"remove?id="}<c:out value='${event.id}'/>>Удалить</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
