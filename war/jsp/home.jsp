<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
<link rel="styelsheet"  type="text/css" />
</head>

<body >




<h1> HOME PAGE </h1>

<p> Welcome b2a ya${it.name} </p>



<form action="/social/search" method="post">
<p>Enter User Name :</p>
<input type="text" name="name" value=" ">
<input type="submit" name=" Search">



</form>

<a href="/social/login/"> Log Out</a> <br>

<a href="/social/requests/">See Friend requests</a>

</body>
</html>