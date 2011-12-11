<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP calculator</title>
    </head>
    <body>
        <jsp:useBean class="org.javatrainer.Calc" id="calc" scope="session" />
        <hr>
        <form action="http://localhost:8080/myapp/jspcalc.jsp">
            <input type="text" name="a" size="10" value="${param.a}">  + 
            <input type="text" name="b" size="10" value="${param.b}"> 
            <input type="submit" value="   =   "> 
            <jsp:setProperty name="calc" property="a" value="${param.a}" />            
            <jsp:setProperty name="calc" property="b" value="${param.b}" />
            <input type="text" name="c" size="10" value="${calc.c}">
			<% session.setAttribute("result", calc.getC()); %>
            <br>
            <a href="http://localhost:8080/myapp/result.jsp" onclick="f();">Show result(passed through session) on another page</a>
        </form>
     </body>
</html>