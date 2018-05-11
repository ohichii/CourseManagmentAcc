<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
  <head>

    <title> CM | Courses Management</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,900" rel="stylesheet">
	<link rel="icon" type="image/png" href="images/cm9.PNG">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/owl.carousel.min.css">

    <link rel="stylesheet" href="fonts/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="fonts/fontawesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="fonts/flaticon/font/flaticon.css">

    <!-- Theme Style -->
    <link rel="stylesheet" href="css/style.css">
  </head>
  <body>

    <header role="banner">
     
      <nav class="navbar navbar-expand-md navbar-dark bg-light"> 
        <div class="container">
          <img src="images/cm9.PNG" width="13%" height="13%" alt="" >
          <a class="navbar-brand absolute" href="homeCM.jsp">Corsi Professionali</a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample05" aria-controls="navbarsExample05" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse navbar-light" id="navbarsExample05">
            <ul class="navbar-nav mx-auto">
              <li class="nav-item">
                <a class="nav-link active" href="homeCM.jsp">Home</a>
              </li>
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="corsiCM.jsp" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color: #626262">Corsi</a>
                <div class="dropdown-menu" aria-labelledby="dropdown04">
                  <a class="dropdown-item" href="corsiCM.jsp">Corso ${Estetica}</a>
                  <a class="dropdown-item" href="corsiCM.jsp">Corso ${Informatica}</a>
                  <a class="dropdown-item" href="corsiCM.jsp">Corso ${Lingue}</a>
                  
                </div>
              </li>

              <li class="nav-item">
                <a class="nav-link" href="contattaciCM.jsp" style="color: #626262">Contattaci &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&#8286 </a>
              
              </li>
            </ul>
            
            <ul class="navbar-nav absolute-right">
              <li class="nav-item">
                <a href="loginCM.jsp" class="nav-link" style="color: #626262"> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Accedi</a>
              </li>
              <li class="nav-item">
                <a href="registrazioneCM.jsp" class="nav-link" style="color: #626262">Registrazione</a>
              </li>
            </ul>
            
          </div>
        </div>
      </nav>
    </header>
    <!-- END header -->