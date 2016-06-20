<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Discussion Thread Registration</title>
    </head>
    <body>
        <h1>Register</h1>
        <p>${error}</p>
        <p>${msg}</p>
        <form action="Authenticate" method="post">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" />
            <label for="password">Password</label>
            <input type="password" name="password" id="password" />
            <label for="verify">Verify Password</label>
            <input type="password" name="verify" id="verify" />
            <button type="submit" name="action" value="signup">Sign Up</button>
        </form>
        <p>If you already have an account with us, sign in <a href="SignIn.jsp">here</a></p>
    </body>
</html>