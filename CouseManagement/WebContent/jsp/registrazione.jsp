<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>registrazione</title>
</head>
<body bgcolor="lightcyan" style="font-family: Arial, Helvetica, sans-serif">

<h1>Utente non trovato! Registrati</h1>

<form action="login" method="get">

	${errorMsg2}
	<h2>${errorMsg3}</h2>
	<hr><br>
	
	<label for="username">Username </label>
	<input type="text" name="username" placeholder=" inserisci username"/>
	<br><br>

	<label for="password">Password </label>
	<input type="password" name="password" placeholder=" inserisci password"/>
	${errorMsg4} ${errorMsg5}
	
	<br><br>
	
	<label for="confermaPass">Conferma Password </label>
	<input type="password" name="confermaPass" placeholder=" conferma password"/>
	
	<br><br><br>	
	
	<button type="submit">registrati</button>

</body>
</html>