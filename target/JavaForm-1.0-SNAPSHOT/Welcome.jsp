<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Discussion Thread Welcome</title>
    </head>
    <body>
        <h1>Welcome to Our Forum</h1>
        <h3>Feel free to view and post comments or sign out.</h3>

        <p>${error}</p>
        <p>${msg}</p>
        <form action="Forum" method="post">
            <button type="submit" name="action" value="view_comments">View Comments</button>
        </form>
        <form action="Authenticate" method="post">
            <button type="submit" name="action" value="signout">Sign Out</button>
        </form>
    </body>
</html>