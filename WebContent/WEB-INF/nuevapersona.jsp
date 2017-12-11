<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.Persona"%>
<%@page import="entidades.Categoria"%>
<%@page import="entidades.Tipo_Elemento"%>
<%@page import="java.util.ArrayList"%>

<% Categoria cat=((Persona)session.getAttribute("user")).getCategoria();
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
    
    
     
	 <title>Alta persona</title>
	 
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
		
			<form name="frm" action="validarpersona.servlet" method="post" style="width:50%;" >
			
			<div class="form-group">
		    	<label>*Categoria </label>
		    	<select id="txtidcategoria" name="txtidcategoria" class="form-control">
			        <option selected>Elija una categoria...</option>
			        <% ArrayList<Categoria> lista = (ArrayList<Categoria>)request.getAttribute("listadocategorias"); 
				    for(Categoria categ : lista){ %> 
			        <option value="<%=categ.getId_Categoria()%>"><%=categ.getDescripcion()%></option> 
			         <%} %>
		    	 </select>
		    </div>
		    
		    <div class="form-group">
			    <label for="exampleFormControlInput1">*Nombre: </label>
			    <input type="text" name="txtnombre" id="txtnombre" class="form-control" id="exampleFormControlInput1" placeholder="Ingrese su nombre">
			</div>
			
			 <div class="form-group">
			    <label for="exampleFormControlInput1">*Apellido: </label>
			    <input type="text" name="txtapellido" id="txtapellido" class="form-control" id="exampleFormControlInput1" placeholder="Ingrese su apellido">
			</div>
		    
		    <div class="form-group">
			    <label for="exampleFormControlInput1">*Dni: </label>
			    <input type="text" name="txtdni" id="txtdni" class="form-control" id="exampleFormControlInput1" placeholder="Ingrese dni">
			</div>
		    
		     <div class="form-group">
			    <label for="exampleFormControlInput1">*Usuario: </label>
			    <input type="text" name="txtusuario" id="txtusuario" class="form-control" id="exampleFormControlInput1" placeholder="Ingrese nombre de usuario">
			    <small class="form-text text-muted">El nombre de usuario debe contener entre 4 y 20 caracteres</small>
			</div>	
			
			<div class="form-group">
			    <label for="exampleFormControlInput1">*Contraseña: </label>
			    <input type="password" name="txtpass" id="txtpass" class="form-control" id="exampleFormControlInput1" placeholder="Ingrese una contraseña">
			    <small class="form-text text-muted">Su contraseña debe contener entre 8 y 20 caracteres</small>
			</div>
			
			<div class="form-group">
			    <label for="exampleFormControlInput1">*Repita su contraseña: </label>
			    <input type="password" name="txtpass2" id="txtpass2" class="form-control" id="exampleFormControlInput1" placeholder="Repita su contraseña">
			    <small class="form-text text-muted">Su contraseña debe contener entre 8 y 20 caracteres</small>
			</div>
			
			<div class="form-group">
				<label for="exampleFormControlInput1">*Habilitado: </label>
				<div class="form-check form-check-inline">
				  <label class="form-check-label">
	    			<input class="form-check-input" type="radio" name="estado" id="inlineRadio1" value="true" checked>Si
					  </label>
					</div>
					<div class="form-check form-check-inline">
					  <label class="form-check-label">
					    <input class="form-check-input" type="radio" name="estado" id="inlineRadio2" value="false">No
					  </label>
				 </div>
			 <small class="form-text text-muted">* Campos obligatorios</small>
			 </div>
			 
			 <button type="button" class="btn btn-primary" name="btnagregar" id="btnagregar" onclick="return validarfrmNuevapersona();">
			  				Agregar
							</button>
			 <a class="btn btn-primary" href="Start">Cancelar</a>
			
			
			<!-- Modal -->
						<div class="modal fade" id="frmModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLabel">Agregar persona</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      <div class="modal-body">
						        ¿Está seguro que desea agregar esta persona?
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
						        <button type="submit" class="btn btn-primary" name="btnagregar">Aceptar</button>
						      </div>
						    </div>
						  </div>
						</div>
				<!--Fin Modal -->
			
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