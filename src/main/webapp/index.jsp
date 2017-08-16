
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Index</title>
    <style>
        <%@include file='WEB-INF/css/style.css' %>
    </style>

</head>
<body>

<div class="centerLayer">
    <h1>Log in to chat</h1>
    <form name="login_form" method="post" action="chat">

        <table align="center">
            <tbody>
            <tr>
                <td >Enter name :</td>
                <td>
                    <input type="text" name="login" value="" />
                    <input type="submit" name="login" value="To chat" />
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
