<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Discussion Thread Sign In</title>
    </head>
    <body>
        <h1>Sign In</h1>
        <p>${error}</p>
        <p>${msg}</p>
        <form action="Authenticate" method="post">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" />
            <label for="password">Password</label>
            <input type="password" name="password" id="password" />
            <button type="submit" name="action" value="signin">Sign In</button>
        </form>
        <p>If you don't have an account, please register <a href="SignUp.jsp">here</a></p>
    </body>
</html>