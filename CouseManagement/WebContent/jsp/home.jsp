<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body bgcolor="lightcyan" style="font-family: Arial, Helvetica, sans-serif">

<h1>Login</h1>

<hr><br>

<form action="login" method="post">
	<label for="username">Username</label>
	<input type="text" name="username" placeholder=" inserisci username"/>
	
	<br><br>

	<label for="password">Password</label>
	<input type="password" name="password" placeholder=" inserisci password"/>
	${erroreMsg1}
	
	<br><br><br>
	
	<button type="submit">login</button>

</form>

</body>
</html>