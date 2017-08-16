<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Chat</title>
    <style>
        <%@include file = '../css/style.css'%>
    </style>
</head>


<body>
<div style="text-align: center">
    <h1>User: ${login}</h1>
</div>


<div id="wrapper">
    <div id="menu">
        <p class="welcome"><b>Chat</b></p>
        <p class="logout">Click <a id="exit" href="logoff">here</a> to logoff</p>
        <div style="clear:both"></div>
    </div>

    <div id="chatbox"></div>

    <input name="usermsg" type="text" id="usermsg"/>
    <input name="submitmsg" type="submit" id="submitmsg" value="Send"/>
</div>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var token = "empty";
        setInterval(
            function () {
                $.ajax({
                    url: "send",
                    type: "GET",
                    datatype: "text",
                    data: {timeStamp: token},
                    success: function (result) {
                        var resp = JSON.parse(result);
                        var str = "";
                        $.each(resp, function (index, element) {
                            str += "<b>" + element.login + "</b>(" + element.timeStamp + "):&nbsp;" + element.message + "</br>";
                            token = element.timeStamp;
//                            login = element.login;
                        });
                        $("#chatbox").append(str);
                    }
                });
            }, 2000);

        var sendMessage = function () {
            $.ajax({
                url: "send",
                type: "post",
                datatype: "text",
                data: {text: $("#usermsg").val()},
                success: function (result) {
                    var resp = JSON.parse(result);
                    $("#chatbox").append("<b>" + resp.login + "</b>(" + resp.timeStamp + "):&nbsp;" + resp.message + "</br>");
                    $("#usermsg").attr("value", "");
                    token = resp.timeStamp;
                }
            });
        };

        $("#submitmsg").click(sendMessage);
        $("#usermsg").bind("enterKey", function (e) {
            sendMessage();
        });
        $("#usermsg").keyup(function (e) {
            if (e.keyCode == 13) {
                $(this).trigger("enterKey");
            }
        });
    });
</script>

</body>
</html>
