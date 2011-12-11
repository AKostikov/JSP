<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="http://localhost:8080/myapp/add">
			<b>Street</b>
			<input type="text" name="street" size="10"> 
			<b>Number</b>  
            <input type="text" name="number" size="10"> 
            <input type="submit" value = "add"> 
 </form>
        <br>
        <a href="http://localhost:8080/myapp/ViewBuildings">View</a>
        <br>
        <a href="http://localhost:8080/myapp/newbase">Init base (if not exists)</a>
</body>
</html>