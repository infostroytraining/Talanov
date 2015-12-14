<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="public/stylesheets/lib/materialize.min.css" />">
    <link rel="stylesheet" href="<c:url value="public/stylesheets/style.css" />">
    <script src="<c:url value="public/javascript/lib/jquery-1.11.3.min.js" />"></script>
    <script src="<c:url value="public/javascript/lib/materialize.min.js" />"></script>
</head>
<body>

<div class="row">
    <h1>${success}</h1>
    <form action="<c:url value="/index"/>" enctype="multipart/form-data" method="post" class="col s12">
        <div class="row">
            <div class="input-field col s6">
                <input id="first_name" type="text" class="validate" name="firstName" value="${userDTO.firstName}">
                <label for="first_name">First Name <span class="errors">${errors.firstName}</span></label>
            </div>
            <div class="input-field col s6">
                <input id="last_name" type="text" class="validate" name="lastName" value="${userDTO.lastName}">
                <label for="last_name">Last Name <span class="errors">${errors.lastName}</span></label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <input id="email" type="email" class="validate" name="email" value="${userDTO.email}">
                <label for="email">Email <span class="errors">${errors.email}</span></label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <input id="password" type="password" class="validate" name="password">
                <label for="password">Password <span class="errors">${errors.password}</span></label>
            </div>
        </div>

        <div class="file-field input-field">
            <div class="btn">
                <span>File</span>
                <input type="file" name="FILE">
            </div>
            <div class="file-path-wrapper">
                <input class="file-path validate" type="text">
            </div>
        </div>

        <button class="btn waves-effect waves-light" type="submit" name="action">Send
            <i class="material-icons right">send</i>
        </button>
    </form>
</div>

</body>
</html>

