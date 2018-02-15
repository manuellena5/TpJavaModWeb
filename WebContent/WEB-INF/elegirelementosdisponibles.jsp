<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.Persona"%>
<%@page import="entidades.Elemento"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Categoria"%>
<%@page import="java.sql.Date"%>
<%@page import="entidades.Reserva"%>
<%@page import="java.text.SimpleDateFormat"%>

<% Categoria cat=((Persona)session.getAttribute("user")).getCategoria();


			java.util.Date data = null;
		    SimpleDateFormat simple= new SimpleDateFormat("yy-MM-dd");
			String fechainicio = ((Reserva)request.getAttribute("reserva")).getFecha_inicio().toString();
			data = simple.parse(fechainicio);
			simple = new SimpleDateFormat("dd/MM/yyyy");
			fechainicio = simple.format(data);

			
			data = null;
		    simple= new SimpleDateFormat("yy-MM-dd");
			String fecharegistro = ((Reserva)request.getAttribute("reserva")).getFecha_registro().toString();
			data = simple.parse(fecharegistro);
			simple = new SimpleDateFormat("dd/MM/yyyy");
			fecharegistro = simple.format(data);
			
			data = null;
		    simple= new SimpleDateFormat("yy-MM-dd");
			String fechafin = ((Reserva)request.getAttribute("reserva")).getFecha_fin().toString();
			data = simple.parse(fechafin);
			simple = new SimpleDateFormat("dd/MM/yyyy");
			fechafin = simple.format(data);
			
			
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


		<div class="cuerpo">
		
			<form action="validaraltareservausuario.servlet" method="post">
				<input style="display:none;" name="fecharegistro" value="<%=fecharegistro%>" >
				<input style="display:none;" name="fechainicio" value="<%=fechainicio%>" >
				<input style="display:none;" name="fechafin" value="<%=fechafin%>" >
				
				<input id="idpersona" name="idpersona" style="display:none;" value="<%=((Reserva)request.getAttribute("reserva")).getPersona().getId_persona()%>">
		      <label>Reserva a nombre de: </label>
		      <br>
		      <label><%=((Reserva)request.getAttribute("reserva")).getPersona().getNombre()+" "+((Reserva)request.getAttribute("reserva")).getPersona().getApellido()%></label>
					
					<table class="table table-striped">
					
						<thead>
						    <tr>
						      <th scope="col">Elemento</th>
						      <th scope="col">Id elemento</th>
						      <th scope="col">Nombre</th>
						      <th scope="col">Autor</th>
						      <th scope="col">Genero</th>
						      <th scope="col">Descripcion</th>
						      <th scope="col"></th>
						    </tr>
						 </thead>
						  
						
						<%
						int count=0;
						ArrayList<Elemento> listaElementos = (ArrayList<Elemento>)request.getAttribute("listaElementos");
						if(listaElementos.isEmpty())
						{%>
						
						<div class="alert alert-danger" role="alert">
						  No se han encontrado elementos disponibles para esa fecha! Por favor seleccione otra
						</div>	
						
						 	
						<!-- window.history.go(-2); -->
						<%}else{
						for(Elemento e : listaElementos){
							count++;
						%>
					   <tr>
					      <th scope="row"><%=count%></th>
					      <td><%=e.getId_elemento()%></td>
					      <td><%=e.getNombre()%></td>
					      <td><%=e.getAutor()%></td>
					      <td><%=e.getGenero()%></td>
					      <td><%=e.getDescripcion()%></td>
					
					      <td><div class="btn-group" role="group" aria-label="Basic example">
								  
								  <button type="button" class="btn btn-secondary" name="btneleccion" onclick="tomariddelboton(this);" id="<%=e.getId_elemento()%>">
			  					  Elegir
								  </button>
								  
								  <!-- Modal -->
										<div class="modal fade" id="frmModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
										  <div class="modal-dialog" role="document">
										    <div class="modal-content">
										      <div class="modal-header">
										        <h5 class="modal-title" id="exampleModalLabel">Eleccion de elemento</h5>
										        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
										          <span aria-hidden="true">&times;</span>
										        </button>
										      </div>
										      <div class="modal-body">
										        ¿Está seguro que desea elegir este elemento?
										      </div>
										      <div class="modal-footer">
										        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
										        <button type="submit" id="btnenviar" name="btnenviar" value="" class="btn btn-primary">Aceptar</button>
										      </div>
										    </div>
										  </div>
										</div>
								<!--Fin Modal -->
								  
								  
							</div>
						  </td>
					    </tr>
					    <% }} %>
					  </tbody>
					</table>
					<button type="button" class="btn btn-primary" name="btnatras" onclick="javascript:window.history.back();">Atras</button>
			
				 
				
			
			
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
    <script type="text/javascript" src="style/js/validaform.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script type="text/javascript" src="style/js/bootstrap.min.js"></script>
    <script src="style/js/ie10-viewport-bug-workaround.js"></script>
    
   
		
	

  </body>
</html>