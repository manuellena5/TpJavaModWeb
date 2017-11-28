<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.Persona"%>
<%@page import="entidades.Elemento"%>
<%@page import="entidades.Tipo_Elemento"%>
<%@page import="entidades.Categoria"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>

<% Categoria cat=((Persona)session.getAttribute("user")).getCategoria();

Date FechaDelSistema = new Date(); /*Tomo la hora del sistema*/

String fechaActual;
SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
fechaActual = simple.format(FechaDelSistema);

%>

    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    
    
     
	 <title>Pagina principal</title>
	 
	 
	 <!-- Bootstrap CSS -->
	  <!-- <link href="style/css/jquery-ui.structure.min.css" rel="stylesheet">
     <link href="style/css/jquery-ui.theme.min.css" rel="stylesheet"> -->
	 
	 <link href="style/css/jquery-ui.min.css" rel="stylesheet">
	 <link href="style/css/bootstrap.min.css" rel="stylesheet">
     <link href="style/css/estilo1.css" rel="stylesheet">
   
    
    
    
    
  </head>
  <body>
    
       <div class="contenedorprincipal container-fluid">
		
		<div class="cabeza">	
				<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
					  <a class="navbar-brand" href="Start">Biblioteca</a>
					<ul class="nav nav-pills">
					<%if ((cat.getDescripcion().equals("Usuario"))) 
					  {%>
					  <li class="nav-item dropdown">
					    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Reservas</a>
					    <div class="dropdown-menu">
					    	<a class="dropdown-item" href="reservasusuario.servlet">Mis reservas</a>
					        <a class="dropdown-item" href="TraerTipoElementos.servlet">Nueva reserva</a>    
					    </div>
					  </li>
					  <li class="nav-item dropdown">
					    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Mis datos</a>
					    <div class="dropdown-menu">
					    	<a class="dropdown-item" href="#">Modificar</a>   
					    </div>
					  </li>
					   <p class="usulogueado"> Bienvenido: <%=((Persona)session.getAttribute("user")).getUsuario() %>
					  			<a href="CerrarSesion" style="color: blue;text-decoration: underline;">(Cerrar sesion) </a>
					  						</p>
					  <%}else
					  if ((cat.getDescripcion().equals("Administrador"))) 
					  {%>
					  <li class="nav-item dropdown">
					    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Personas</a>
					    <div class="dropdown-menu">
					      <a class="dropdown-item" href="ListadoPersonas.servlet">Gestionar personas</a>
					      <a class="dropdown-item" href="altapersona.servlet">Nueva persona</a>
					    </div>
					  </li>
					  <li class="nav-item dropdown">
					    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Reservas</a>
					    <div class="dropdown-menu">
					      <a class="dropdown-item" href="ListadoReservas.servlet">Gestionar reservas</a>
					      <a class="dropdown-item" href="TraerTipoElementos.servlet">Nueva reserva</a>
					    </div>
					  </li>
					  <li class="nav-item dropdown">
					    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Elementos</a>
					    <div class="dropdown-menu">
					      <a class="dropdown-item" href="ListadoElementos.servlet">Gestionar elementos</a>
					      <a class="dropdown-item" href="altaelemento.servlet">Nuevo elemento</a>
					    </div>
					  </li>
					   <li class="nav-item dropdown">
					    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Tipos de elementos</a>
					    <div class="dropdown-menu">
					      <a class="dropdown-item" href="ListadoTiposElementos.servlet">Gestionar tipos de elementos</a>
					      <a class="dropdown-item" href="altatipoelemento.servlet">Nuevo tipo de elemento</a>
					    </div>
					  </li>
					  
					  <p class="usulogueado"> Bienvenido: <%=((Persona)session.getAttribute("user")).getUsuario() %>
					  			<a href="CerrarSesion" style="color: blue;text-decoration: underline;">(Cerrar sesion) </a>
					  						</p>
					 <%
					 } %>
					</ul>
				 
				</nav>
		</div>
		
		<div class="cuerpo">
		
		
		<form action="traerelementosdisponibles.servlet" method="post" onsubmit="return validarfrmFechasAltaReserva();">
		
		<% Tipo_Elemento tipoelemento = (Tipo_Elemento)request.getAttribute("tipoelemento"); 
		if(tipoelemento == null){%>
			<div class="alert alert-danger" role="alert">
			  Ha excedido la cantidad de elementos pendientes de devolver de este tipo, por favor devuelva los ejemplares.
			  Puede seleccionar otro tipo de elemento para reservar
			</div>	
			<a href="javascript:window.history.back();">Volver a la pagina anterior</a>
		
		
		<% }else{   %>
		
		<input id="idpersona" name="idpersona" style="display:none;" value="<%=((Persona)request.getAttribute("persona")).getId_persona()%>">
		      <label>Reserva a nombre de: </label>
		      <br>
		      <label><%=((Persona)request.getAttribute("persona")).getNombre()+" "+((Persona)request.getAttribute("persona")).getApellido()%></label>
		      
		<p>Tipo de elemento a reservar: <%=tipoelemento.getNombre()%></p>
	
		<input id="idtipoelemento" name="idtipoelemento" type="text" style="display:none;" value="<%=((Tipo_Elemento)request.getAttribute("tipoelemento")).getId_tipoelemento()%>">
		
		
		<div class="form-group">
		<label>Fecha de registro:</label>	
		<input id="datepicker1" name="fecharegistro" type="text" required="" value="<%=fechaActual%>"> <!-- fechaActual es la fecha del sistema, la seteo arriba de todo --> 
		</div>
		
		<div class="form-group">
		<label>Fecha de inicio la reserva:</label>	
		<input id="datepicker2" name="fechainicio" type="text" required="">
		</div>
		
		<div class="form-group">
		<label>Fecha de fin de la reserva:</label>	
		<input id="datepicker3" name="fechafin" type="text" required="" onchange="validarfecha();">
		</div>
	  	
	  	<button type="submit" class="btn btn-primary">Siguiente</button>

	  	<%} %>

	  	<button type="submit" class="btn btn-primary" name="btnatras" onclick = "javascript:window.history.back();">Atras</button>

	  	</form>
			
			
		</div> 
		
		<footer class="pie container-fluid">
				
				<div class="contacto">
						<p>Atenci&oacute;n al cliente: 03406-497464</p>
						<p>Direcci&oacute;n: Landeta - Lisandro De La Torre 1195</p>
						<p>Horario de atenci&oacute;n: lunes a viernes de 8 hs a 20 hs</p>
				</div>


				<div class="row">
					<div class="politicas col-xl-4 col-lg-4 col-md-4 col-sm-4">
						<ul class="nav flex-column">
							<li class="nav-item">
							    <a href="politicas.php">Pol&iacute;ticas</a>
							 </li>
							<li class="nav-item">
							    <a href="terminosycondiciones.php">T&eacute;rminos y condiciones</a>
							 </li>
						</ul>
					</div>
	
					<div class="mapa col-xl-4 col-lg-4 col-md-4 col-sm-4">
						<ul class="nav flex-column">
							   <%if ((cat.getDescripcion().equals("Usuario"))) 
					  {%>
							  
							  <li class="nav-item">
							   <a class="nav-link itemmapa" href="reservasusuario.servlet">Mis reservas</a>
							  </li>
							  <li class="nav-item">
							   <a class="nav-link itemmapa" href="#">Modificar mis datos</a>
							  </li>
							  <li class="nav-item">
							    <a class="nav-link itemmapa" href="#">Ayuda</a>
							  </li>
							  <li class="nav-item">
							    <a class="nav-link itemmapa" href="#">Contacto</a>
							  </li>
							  <%}else if ((cat.getDescripcion().equals("Administrador"))) 
					  {%>	  
					  		  <li class="nav-item">
							     <a class="nav-link itemmapa" href="ListadoReservas.servlet">Gestionar reservas</a>
							  </li>
							  <li class="nav-item">
							    <a class="nav-link itemmapa" href="ListadoPersonas.servlet">Ver personas</a>
							  </li>
							  <li class="nav-item">
							    <a class="nav-link itemmapa" href="ListadoElementos.servlet">Ver elementos</a>
							  </li>
							  <li class="nav-item">
							    <a class="nav-link itemmapa" href="ListadoTiposElementos.servlet">Ver tipos de elementos</a>
							  </li>
							  <li class="nav-item">
							    <a class="nav-link itemmapa" href="#">Ayuda</a>
							  </li>
							  <li class="nav-item">
							    <a class="nav-link itemmapa" href="#">Contacto</a>
							  </li>
							  <%} %>
							  
						</ul>
					</div>

					

					
				</div>
				
				<div class="dr">
					<p>Copyright &copy; 1999-2017 Biblioteca - Todos los derechos reservados.</p>
				</div>
				
		</footer>

	</div> <!-- fin div container general -->

    <!-- jQuery first, then Tether, then Bootstrap JS. -->
    <script type="text/javascript" src="style/js/jquery.js"></script>
    <script type="text/javascript" src="style/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="style/js/jquery-ui.js"></script>
     <script type="text/javascript" src="style/js/validaform.js"></script>
    <script src="style/js/ie10-viewport-bug-workaround.js"></script>
    <script type="text/javascript" src="style/js/datepicker-es.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script type="text/javascript" src="style/js/bootstrap.min.js"></script>
   
	<script type="text/javascript">
    $(function () {
    	var today = new Date();
    	$("#datepicker1").datepicker({ minDate: 0 });
    	$("#datepicker2").datepicker({ minDate: 0 });
    	$("#datepicker3").datepicker({ minDate: 0 });
    	$("#datepicker").datepicker($.datepicker.regional["es"]);
 
    });
    
   
	</script>
	

  </body>
</html>