<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="p" uri="WEB-INF/custom.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="public/stylesheets/lib/materialize.min.css" />">
    <link rel="stylesheet" href="<c:url value="public/stylesheets/style.css" />">
</head>
<body>

<div class="row fields">
    <div class="col s12 m4">&nbsp;</div>
    <div class="col s12 m4">
        <div class="row blue-grey darken-1 oops">
            <div class="input-field col m6 s12">
                <i class="material-icons prefix">account_circle</i>
                <input id="first_name" type="text" value="balla" class="validate">
                <label for="first_name" id="fist_name_error"><a>First Name</a></label>
            </div>
            <div class="input-field col m6 s12">
                <i class="material-icons prefix">perm_identity</i>
                <input id="last_name" type="text" value="jfkdhjfk" class="validate">
                <label for="last_name" id="last_name_error"><a>Last Name</a></label>
            </div>
            <div class="input-field col m6 s12">
                <i class="material-icons prefix">email</i>
                <input id="email" type="email" value="you-mail@gmail.com" class="validate">
                <label for="email" id="email_error"><a>Email</a></label>
            </div>
            <div class="input-field col m6 s12">
                <i class="material-icons prefix">https</i>
                <input id="password" type="password" value="0999236364zDTicwCC" class="validate">
                <label for="first_name" id="password_error"><a>Password</a></label>
            </div>
            <div class="col m12 s12">
                <form action="#" enctype="multipart/form-data">
                    <div class="file-field input-field">
                        <div class="btn">
                            <span>File</span>
                            <input type="file">
                        </div>
                        <div class="file-path-wrapper">
                            <input class="file-path validate" type="text">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<div class="row oops">
    <div class="col s12 m4">&nbsp;</div>
    <div class="col s12 m2">
        <div class="card blue-grey darken-1">
            <div class="card-content white-text">
                <span class="card-title"><p:captcha/> &nbsp;
                    <button class="waves-effect waves-light btn-floating btn-large">
                        <span class="black-text">
                            <i id="generateCaptcha" class="material-icons medium">replay</i>
                        </span>
                    </button>
                </span>
                <div class="input-field">
                    <i class="material-icons prefix">vpn_key</i>
                    <input id="captcha" type="text" class="validate">
                    <label for="captcha" id="captcha_error">Captcha</label>
                </div>
                <div class="progress" style="display: none">
                    <div class="indeterminate"></div>
                </div>
            </div>
            <div class="card-action">
                <a href="#" id="register">Register</a>
            </div>
        </div>
    </div>
</div>



<script src="<c:url value="public/javascript/lib/jquery-1.11.3.min.js" />"></script>
<script src="<c:url value="public/javascript/lib/materialize.min.js" />"></script>
<script src="<c:url value="public/javascript/service/registration.js" />"></script>
<script src="<c:url value="public/javascript/service/uploadFile.js" />"></script>
<script src="<c:url value="public/javascript/app.js" />"></script>
</body>
</html>

