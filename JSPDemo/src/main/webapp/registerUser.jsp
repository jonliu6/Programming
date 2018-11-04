<%@ page autoFlush="true" buffer="256kb" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>User Registration</title>
    </head>
    <body>
        <form action="registerUser" method="post">
            <label for="userFirst">First Name: </label><input id="userFirst" name="first_name" type="text" value="" /> <br/>
            <label for="userLast">Last Name: </label><input id="userLast" name="last_name" type="text" value="" /> <br/>
            <input type="submit" value="Register" />
            <input type="reset" value="Cancel" />
        </form>
    </body>
</html>