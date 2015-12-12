<%@include file="WEB-INF/include/heap.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<form action="" method="post">
    <h1 style="color: green">${success}</h1>
    <label for="firstName">First name </label>
    <input id="firstName" type="text" name="firstName" value="${userDTO.firstName}"> <br/><br/>

    <label for="lastName">Last name </label>
    <input id="lastName" type="text" name="lastName" value="${userDTO.lastName}"> <br/><br/>

    <label for="email">Email </label>
    <input id="email" type="text" name="email" value="${userDTO.email}"> <span style="color: #D50000">${errors.emailFail}</span> <br/><br/>

    <label for="password">Password </label>
    <input id="password" type="password" name="password"> <span style="color: #D50000">${errors.passwordFail}</span> <br/><br/>

    <label for="avatar">Image upload </label>
    <input id="avatar" type="file"> <br/><br/>

    <input type="submit" value="Send">
</form>

</body>
</html>

