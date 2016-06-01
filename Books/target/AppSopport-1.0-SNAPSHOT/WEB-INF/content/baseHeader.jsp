<%@
 taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book</title>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/css/materialize.min.css">
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="/public/css/style.css"/>
</head>
<body>
<div id="navConteiner" class="nav-wrapper mNav center">
    <a class="waves-effect waves-light btn-large light-blue"
       href="<tag:url value="/books"><tag:param name="action" value="list"/></tag:url>">Libros</a>
    <a class="waves-effect waves-light btn-large light-blue"
       href="<tag:url value="/books"><tag:param name="action" value="add"/></tag:url>">Subir Libro</a>
    <div class="divider" style="margin-top: 1%"></div>

</div>

