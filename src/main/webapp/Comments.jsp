<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Discussion Thread Comments/Posts</title>
    </head>
    <body>
        <h1>Forum</h1>
        <p>${error}</p>
        <p>${info}</p>
        <p>${msg}</p>
        <h2>Current posts</h2>
        <ul>
            <c:forEach items="${comments}" var="comment">
                <li>${comment}</li><br />
            </c:forEach>
        </ul>
        <h2>Post a new comment</h2>
        <form action="Forum" method="post">
            <label for="comment">Comment</label>
            <textarea id="comment" name="comment" class="form-control" rows="3"></textarea>
            <button type="submit" name="action" value="post_comment">Submit Post</button>
        </form>
        <form action="Authenticate" method="post">
            <button type="submit" name="action" value="signout">Log Out</button>
        </form>
    </body>
</html>