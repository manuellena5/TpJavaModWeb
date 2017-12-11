<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.Persona"%>
<%@page import="entidades.Reserva"%>
<%@page import="entidades.Elemento"%>
<%@page import="entidades.Tipo_Elemento"%>
<%@page import="entidades.Categoria"%>
<%@page import="java.util.Date"%>
<%@page import="entidades.Reserva"%>
<%@page import="java.text.SimpleDateFormat"%>

<% Categoria cat=((Persona)session.getAttribute("user")).getCategoria();

Date FechaDelSistema = new Date(); /*Tomo la hora del sistema*/
java.sql.Date fechaActual = new java.sql.Date(FechaDelSistema.getTime());

Reserva r = (Reserva)request.getAttribute("reserva"); 
java.util.Date data = null;


SimpleDateFormat simple= new SimpleDateFormat("yy-MM-dd");
String rfecharegistro = r.getFecha_registro().toString();
data = simple.parse(rfecharegistro);
simple = new SimpleDateFormat("dd/MM/yyyy");
rfecharegistro = simple.format(data);
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
		
		<div class="cuerpo container">
		
		
		<form action="finalizaraltareserva.servlet" method="post">
		
			
			<input id="fecharegistro" name="fecharegistro" type="text" style="display:none;"  value="<%=rfecharegistro%>">
			<input id="idtipoelemento" name="idtipoelemento" type="text" style="display:none;"  value="<%=r.getElemento().getTipo_Elemento().getId_tipoelemento()%>">
			<input id="idelemento" name="idelemento" type="text" style="display:none;"  value="<%=r.getElemento().getId_elemento()%>">
			<input id="idpersona" name="idpersona" type="text" style="display:none;" value="<%=r.getPersona().getId_persona()%>">
			
			<div class="alert alert-success" role="alert">
			  <h4 class="alert-heading">Su reserva se ha registrado correctamente</h4>
			  <p><strong>Datos de la reserva: </strong></p>
			  <hr>
			    <p><%=r.getElemento().getTipo_Elemento().getNombre() %></p>
				<p><%=r.getElemento().getNombre() %></p>
				<p>Fecha inicio de la reserva: <em><%=r.getFecha_inicio() %></em></p>
				<p>Fecha fin de la reserva: <em><%=r.getFecha_fin() %></em></p>
				<p><strong>Datos de la persona</strong></p>
				<hr>
				<p>Nombre: <em><%=r.getPersona().getNombre() %></em></p>
				<p>Apellido: <em><%=r.getPersona().getApellido() %></em></p>
				<p>Dni: <em><%=r.getPersona().getDni() %></em></p>
			</div>
			
			<div class="form-group">
			    <label for="exampleFormControlTextarea1">¿Desea agregar algun detalle?</label>
			    <textarea class="form-control detalle" name="txtdetalle" placeholder="Sin detalle" value="Sin detalle"></textarea>
			    <small class="form-text text-muted">*Dejar vacio en caso de no querer agregar un detalle</small>
		  	</div>
							  	
		  	<button type="submit" class="btn btn-primary">Finalizar</button>
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
    
    function validarfecha(){
    	var fechainicio = document.getElementById("datepicker2").value;
    	var fechafin = document.getElementById("datepicker3").value;
    	
    	if (fechainicio > fechafin) {
			alert("La fecha de fin debe ser mayor a la fecha de inicio");
		}
    }
	</script>
	

  </body>
</html>