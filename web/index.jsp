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
</body>
</html>
