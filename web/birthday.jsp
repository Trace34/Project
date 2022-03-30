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
            <%--@elvariable id="event" type="ru.vsu.afanasenko.models.impl.Birthday"--%>
            <c:if test="${event.id != 0}">
            <form action="${"update"}" method="post"></c:if>
                <c:if test="${event.id == 0}">
                <form action="${"insert"}" method="post"></c:if>
                    <caption>
                        <h2>
                            <c:if test="${event.id != 0}">Редактирование дня рождения
                            </c:if>
                            <c:if test="${event.id == 0}">Добавление дня рождения
                            </c:if>
                        </h2>
                    </caption>
                    <br>
                    <c:if test="${event.id != 0}">
                        <input type="hidden" name="id" value="<c:out value='${event.id}' />"/>
                    </c:if>
                    <fieldset class="form-group">
                        <label style="width: 120px; display: inline-block">Дата: </label><label>
                        <input type="date"
                               style="width: 200px; height: 30px;"
                               value="<c:out value='${event.date}' />" class="form-control"
                               name="date" required="required">
                    </label>
                    </fieldset>
                    <fieldset class="form-group">
                        <label style="width: 120px; display: inline-block">Время: </label><label>
                        <input type="time"
                               style="width: 200px; height: 30px;"
                               value="<c:out value='${event.time}' />" class="form-control"
                               name="time">
                    </label>
                    </fieldset>
                    <fieldset class="form-group">
                        <label style="width: 120px; display: inline-block">Описание: </label><label>
                        <input type="text"
                               style="width: 200px; height: 30px;"
                               value="<c:out value='${event.description}' />" class="form-control"
                               name="description">
                    </label>
                    </fieldset>
                    <fieldset class="form-group">
                        <label style="width: 120px; display: inline-block">Имя: </label><label>
                        <input type="text"
                               style="width: 200px; height: 30px;"
                               value="<c:out value='${event.firstName}' />" class="form-control"
                               name="firstName">
                    </label>
                    </fieldset>
                    <fieldset class="form-group">
                        <label style="width: 120px; display: inline-block">Подарок: </label><label>
                        <input type="text"
                               style="width: 200px; height: 30px;"
                               value="<c:out value='${event.gift}' />" class="form-control"
                               name="gift">
                    </label>
                    </fieldset>
                    <div class="container text-right">
                        <button type="submit" style="width: 150px; height: 40px" class="btn btn-success">Сохранить
                        </button>
                    </div>
                </form>
                <br>
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
