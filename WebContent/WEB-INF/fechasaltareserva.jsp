<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.Persona"%>
<%@page import="entidades.Elemento"%>
<%@page import="entidades.Reserva"%>
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
					<%if ((cat.getDescripcion().equals("Usuario")) || (cat.getDescripcion().equals("Encargado"))) 
					  {%>
					  <li class="nav-item dropdown">
					    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Reservas</a>
					    <div class="dropdown-menu">
					    	<a class="dropdown-item" href="traerreservasusuario.servlet">Mis reservas</a>
					        <a class="dropdown-item" href="TraerTipoElementos.servlet">Nueva reserva</a>    
					    </div>
					  </li>
					  <li class="nav-item dropdown">
					    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Mis datos</a>
					    <div class="dropdown-menu">
					    	<a class="dropdown-item" href="modificarmisdatos.servlet">Modificar</a>   
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
					      <a class="dropdown-item" href="elegirpersona.servlet">Nueva reserva</a>
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
		
		<input id="idpersona" name="idpersona" style="display:none;" value="<%=((Reserva)request.getAttribute("reserva")).getPersona().getId_persona()%>">
		<input id="categoria" name="categoria" style="display:none;" value="<%=((Persona)session.getAttribute("user")).getCategoria().getDescripcion()%>">
		<p>Reserva a nombre de: <%=((Reserva)request.getAttribute("reserva")).getPersona().getNombre()+" "+((Reserva)request.getAttribute("reserva")).getPersona().getApellido()%></p>
		      
		      
		<p >Tipo de elemento a reservar: <%=tipoelemento.getNombre()%></p>
		<input id="nombretipoelemento" name="nombretipoelemento" type="text" style="display:none;" value="<%=tipoelemento.getNombre()%>">
		
		
		<input id="idtipoelemento" name="idtipoelemento" type="text" style="display:none;" value="<%=((Tipo_Elemento)request.getAttribute("tipoelemento")).getId_tipoelemento()%>">
		
		
		<div class="form-group">
		<label>Fecha de registro:</label>	
		<input id="datepicker1" name="fecharegistro" type="text" required="" value="<%=fechaActual%>" onchange="setearfechas();"> <!-- fechaActual es la fecha del sistema, la seteo arriba de todo --> 
		<div id="smalltext1">
			<small class="form-text text-muted">Seleccione una fecha de registro</small>
		</div>
		</div>
		
		
		
		
		<div id="divfechainicio" class="form-group" style="display:none;">
		<label>Fecha de inicio la reserva:</label>	
		<input id="datepicker2" name="fechainicio" type="text" value="<%=fechaActual%>" required="" onchange="setearfechas();">
		<div id="smalltext2">
			<small class="form-text text-muted">Seleccione una fecha de inicio</small>
		</div>
		</div>
		
		<div id="divfechafin" class="form-group" style="display:none;">
		<label>Fecha de fin de la reserva:</label>	
		<input id="datepicker3" name="fechafin" type="text" required="" onchange="setearfechas();">
		<div id="smalltext3">
			<small class="form-text text-muted">Seleccione una fecha de fin</small>
		</div>
		</div>
	  	
	  	<button type="submit" class="btn btn-primary">Siguiente</button>
		<button type="button" class="btn btn-primary" name="btnatras" onclick="javascript:window.history.back();">Atras</button>
	  	<%} %>

	  	

	  	</form>
			
			
		</div> 
		
		<footer class="pie container-fluid">
				
				<div class="contacto">
						<p>Atenci&oacute;n al cliente: 03406-497464</p>
						<p>Direcci&oacute;n: Landeta - Lisandro De La Torre 1195</p>
						<p>Horario de atenci&oacute;n: lunes a viernes de 8 hs a 20 hs</p>
				</div>


				<div class="mapa col-xl-4 col-lg-4 col-md-4 col-sm-4">
						<ul class="nav flex-column">
							 <%if(cat.getDescripcion().equals("Administrador")){ %>
							  <li class="nav-item">
							    <a class="nav-link itemmapa" href="ListadoReservas.servlet">Ver reservas</a>
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
							  <%}else if(cat.getDescripcion().equals("Usuario"))
							  {%>
							  		<li class="nav-item">
									    <a class="nav-link itemmapa" href="traerreservasusuario.servlet">Mis reservas</a>
									  </li>
									  <li class="nav-item">
									    <a class="nav-link itemmapa" href="TraerTipoElementos.servlet">Nueva reserva</a>
									  </li>
									  <li class="nav-item">
									    <a class="nav-link itemmapa" href="modificarmisdatos.servlet">Mis datos</a>
									 </li>
									 
					
							 <% } %>
						</ul>
					</div>
					

					
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
    	

    	
    	$("#datepicker1").datepicker({ minDate: 0,maxDate:"1D", beforeShowDay: $.datepicker.noWeekends, onClose: function (selectedDate) {
    		
    			 $("#datepicker2").datepicker("option", "minDate", selectedDate);
    			
    		 
    	}  });
    	
    	$("#datepicker2").datepicker({ minDate: 0, beforeShowDay: $.datepicker.noWeekends, onClose: function (selectedDate) {
    		
    		$("#datepicker3").datepicker("option", "minDate", selectedDate);
    		
    		
    	} });
    	
    	$("#datepicker3").datepicker({ minDate: 0, beforeShowDay: $.datepicker.noWeekends });
    	
    	
    	$("#datepicker").datepicker($.datepicker.regional["es"]);
 
    });

	</script>

  </body>
</html>