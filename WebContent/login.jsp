<!DOCTYPE html>
<html lang="en"><head>

	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    

    <title>Login</title>

    <!-- Bootstrap core CSS -->
    <link href="style/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="style/css/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="style/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="container">

      <form class="form-signin" name="signin" action="Start" method="post">
        <h2 class="form-signin-heading">Login</h2>
        <label for="inputUser" class="sr-only">User</label>
        <input name="user" id="inputUser" class="form-control" placeholder="Usuario" required="" autofocus="" type="">
        <label for="inputPass" class="sr-only">Password</label>
        <input name="pass" id="inputPass" class="form-control" placeholder="Contrase�a" required="" type="Password">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
        
        <br style="color:red"><label style="color:red">${requestScope.errores}</label><br>
      
      </form>

    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="style/js/ie10-viewport-bug-workaround.js"></script>
  

</body></html>