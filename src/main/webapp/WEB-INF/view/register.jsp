<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
    <head>
        <title>Spring Security Example </title>
    </head>
    <body>
        
        <form action="/user" method="post">
            <div><label> User Name : <input type="text" name="email"/> </label></div>
            <div><label> Password: <input type="password" name="password"/> </label></div>
            
            <input type="hidden" name="activated" value="true"/>
            <input type="hidden" name="role" value="COACH" />
            <div><input type="submit" value="Sign In"/></div>
            <input type="hidden" name="${_csrf.parameterName}"
                value="${_csrf.token}" />
        </form>
    </body>
</html>