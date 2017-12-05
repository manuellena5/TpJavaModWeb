<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidades.Persona"%>
<%@page import="entidades.Elemento"%>
<%@page import="entidades.Reserva"%>
<%@page import="entidades.Tipo_Elemento"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="entidades.Categoria"%>
<%@page import="java.util.Date"%>


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
    
    
     
	 <title>Modificar reserva</title>
	 
	 
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
		
		<div class="cuerpo">
		
		<% Tipo_Elemento tipoelemento = (Tipo_Elemento)request.getAttribute("tipoelemento");
		Persona persona = (Persona)request.getAttribute("persona");
		Elemento elemento = (Elemento)request.getAttribute("elemento");
		Reserva r = (Reserva)request.getAttribute("reserva"); 
		
		if(r != null){
		Date FechaDelSistema = new Date(); /*Tomo la fecha del sistema*/
		
			java.util.Date data = null;
		    SimpleDateFormat simple= new SimpleDateFormat("yy-MM-dd");
			String fechainicio = r.getFecha_inicio().toString();
			data = simple.parse(fechainicio);
			simple = new SimpleDateFormat("dd/MM/yyyy");
			fechainicio = simple.format(data);

			
			data = null;
		    simple= new SimpleDateFormat("yy-MM-dd");
			String fecharegistro = r.getFecha_registro().toString();
			data = simple.parse(fecharegistro);
			simple = new SimpleDateFormat("dd/MM/yyyy");
			fecharegistro = simple.format(data);
			
			data = null;
		    simple= new SimpleDateFormat("yy-MM-dd");
			String fechafin = r.getFecha_fin().toString();
			data = simple.parse(fechafin);
			simple = new SimpleDateFormat("dd/MM/yyyy");
			fechafin = simple.format(data);
			
			String fechaActual;
			simple = new SimpleDateFormat("dd/MM/yyyy");
			fechaActual = simple.format(FechaDelSistema);%>
		
		
	<!-- FORMULARIO RESERVA -->
				<form action="FinalizarModificacionReserva.servlet" method="post" style="width:50%;">
				
				
				<div class="form-group">
					<label for="txtid">Tipo de elemento a reservar: </label>
					<input type="text" class="form-control" id="nombretipoelemento" name="nombretipoelemento" value="<%=r.getElemento().getTipo_Elemento().getNombre()%>" readonly="true">
					<input type="text" class="form-control" style="display:none;" id="idtipoelemento" name="idtipoelemento" value="<%=r.getElemento().getTipo_Elemento().getId_tipoelemento()%>">
					
				</div>
				
				<div class="form-group">
					<label for="txtid">Elemento a reservar: </label>
					<input type="text" class="form-control" id="nombreelemento" name="nombreelemento" value="<%=r.getElemento().getNombre()%>" readonly="true">
					<input type="text" class="form-control" style="display:none;" id="idelemento" name="idelemento" value="<%=r.getElemento().getId_elemento()%>">
				</div>
				
				<div class="form-group">
					<label for="txtid">Persona: </label>
					<input type="text" class="form-control" id="nombrepersona" name="nombrepersona" value="<%=r.getPersona().getNombre()%>" readonly="true">
					<input type="text" class="form-control" style="display:none;" id="idpersona" name="idpersona" value="<%=r.getPersona().getId_persona()%>">
				</div>
				
				<div class="form-group">
					<label for="txtid">Fecha de registro: </label>
					<input type="text" class="form-control" id="fecharegistro" name="fecharegistro" value="<%=fecharegistro%>" readonly="true">
				</div>
				
				<div class="form-group">
				<label>Fecha de inicio la reserva:</label>	
				<input id="fechainicio" class="form-control" name="fechainicio" type="text" value="<%=fechainicio%>" readonly="true">
				</div>
				
				
				<div class="form-group">
				<label>Fecha de fin de la reserva:</label>	
				<input type="text" class="form-control" id="fechafin" name="fechafin"  value="<%=fechafin%>" readonly="true">
				</div>
					
				<div class="form-group">
				    <label for="exampleFormControlTextarea1">Detalle:</label>
				    <textarea class="form-control detalle" name="detalle" value="<%=r.getDetalle()%>" ><%=r.getDetalle()%></textarea>
			  	</div>
			  	
			  	<div class="form-group">
				    <label for="exampleFormControlSelect1">Estado: </label>
				    <select class="form-control" name="estado" id="exampleFormControlSelect1">
				      <option selected value="<%=r.getEstado()%>"><%=r.getEstado()%></option>
				      <option value="Activa">Activa</option>
				      <option value="Cancelada">Cancelada</option>
				      <option value="Terminada">Terminada</option>
				      <option value="Sin devolver">Sin devolver</option>
				    </select>
				 </div>
				 
				 <button type="button" class="btn btn-primary" name="btnmodificar" data-toggle="modal" data-target="#frmModal">
							  				Modificar
											</button>
			  	<a class="btn btn-outline-secondary" href="Start">Cancelar</a>
			  	
			  			<!-- Modal -->
								<div class="modal fade" id="frmModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
								  <div class="modal-dialog" role="document">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="exampleModalLabel">Modificar reserva</h5>
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
								          <span aria-hidden="true">&times;</span>
								        </button>
								      </div>
								      <div class="modal-body">
								        ¿Está seguro que desea modificar esta reserva?
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
								        <button type="submit" class="btn btn-primary">Aceptar</button>
								      </div>
								    </div>
								  </div>
								</div>
						<!--Fin Modal -->
			  	
			  	
			  	</form>
		
	<!-- FIN FORMULARIO RESERVA -->
			
		<%}else
		if(persona != null){%>
				
	<!-- FORMULARIO PERSONA -->
		
			<form name="frm" action="FinalizarModificacionPersona.servlet" method="post" style="width:50%;" >
				
						<div class="form-group">
						    <label for="txtid">ID</label>
						    <input type="text" class="form-control" id="txtid" name="txtid" value="<%=persona.getId_persona()%>" readonly="true">
						  </div>
						  <div class="form-group">
						    <label for="txtusuario">*Nombre</label>
						    <input type="text" class="form-control" id="txtnombre" name="txtnombre" value="<%=persona.getNombre()%>">
						  </div>	
	
						  <div class="form-group">
						    <label for="txtnombre">*Apellido</label>
						    <input type="text" class="form-control" id="txtapellido" name="txtapellido" value="<%=persona.getApellido() %>">
						  </div>
						  
						  <div class="form-group">
						    <label for="txtapellido">*Dni</label>
						    <input type="text" class="form-control" id="txtdni" name="txtdni" value="<%=persona.getDni() %>">
						  </div>
						  
						   <div class="form-group">
						    <label for="txtapellido">*Usuario</label>
						    <input type="text" size="20" class="form-control" id="txtusuario" name="txtusuario" value="<%=persona.getUsuario() %>">
						  </div>
						  
						  <div class="form-group">
						    <label for="txtpass">*Password</label>
						    <input type="text" size="20" class="form-control" id="txtpass" name="txtpass" value="<%=persona.getPassword() %>">
						  </div>
						  
						  <div class="form-group">
						    <label for="txtpass2">*Repita el password</label>
						    <input type="text" size="20" class="form-control" id="txtpass2" name="txtpass2" value="<%=persona.getPassword() %>">
						  </div>
						  
						  <%if(((Persona)request.getSession().getAttribute("user")).getCategoria().getDescripcion().equals("Administrador")){ %>
						  
						  <div class="form-group" style="display:none;">
							<label for="exampleFormControlInput1">*Habilitado: </label>
							<div class="form-check form-check-inline">
							  <label class="form-check-label">
				    			<input class="form-check-input" type="radio" name="estado" id="inlineRadio1" value="true" <%if(persona.isHabilitado()){%> checked<%}%>>Si
								  </label>
								</div>
								<div class="form-check form-check-inline">
								  <label class="form-check-label">
								    <input class="form-check-input" type="radio" name="estado" id="inlineRadio2" value="false" <%if(!persona.isHabilitado()){%> checked<%}%>>No
								  </label>
							 </div>
						 </div>
						 <%} %>
						 
						 <div>
						 <small class="form-text text-muted">* Campos obligatorios</small>
						 <br>
						 </div>
						  
						  <div class="btn-group" role="group" aria-label="Basic example">
									  <button type="button" class="btn btn-primary" name="btnmodificar" onclick="return validarfrmModificarPersona();">
						  				Modificar
										</button>
							  		  <a class="btn btn-secondary" href="Start">Cancelar</a>
						  </div>
						  
						  
						   <!-- Modal -->
							<div class="modal fade" id="frmModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
							  <div class="modal-dialog" role="document">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h5 class="modal-title" id="exampleModalLabel">Modificar elemento</h5>
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
							          <span aria-hidden="true">&times;</span>
							        </button>
							      </div>
							      <div class="modal-body">
							        ¿Está seguro que desea modificar este elemento?
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
							        <button type="submit" class="btn btn-primary" name="btneleccion" value="<%=persona.getId_persona()%>">Aceptar</button>
							      </div>
							    </div>
							  </div>
							</div>
						<!--Fin Modal -->
						
				</form>
		
	<!-- FIN FORMULARIO PERSONA -->
		<%}else if(tipoelemento != null){%>
			
	<!-- FORMULARIO TIPO ELEMENTO -->
			
			<form action="FinalizarModificacionTipoElemento.servlet" method="post" style="width:50%;">
			
					<div class="form-group">
					    <label for="txtid">ID</label>
					    <input type="text" class="form-control" id="txtid" name="txtid" value="<%=tipoelemento.getId_tipoelemento() %>" readonly="true">
					  </div>
					  <div class="form-group">
					    <label for="txtusuario">Nombre</label>
					    <input type="text" class="form-control" id="txtnombre" name="txtnombre" value="<%=tipoelemento.getNombre()%>" readonly="true">
					  </div>	

					  <div class="form-group">
					    <label for="txtnombre">Cantidad maxima reservas pendientes</label>
					    <input type="text" class="form-control" id="txtcantmax" name="txtcantmax" required="" value="<%=tipoelemento.getCantMaxReservasPend() %>">
					  </div>
					  
					  
					  <div class="btn-group" role="group" aria-label="Basic example">
					  		<button type="button" class="btn btn-primary" name="btnmodificar" onclick="return validarModificacionTipoElemento();">
			  				Modificar
							</button>
							<a class="btn btn-secondary" href="Start">Cancelar</a>
					  </div>
					  
					  <!-- Modal -->
						<div class="modal fade" id="frmModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLabel">Modificar tipo de elemento</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      <div class="modal-body">
						        ¿Está seguro que desea modificar este tipo de elemento?
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
						        <button type="submit" class="btn btn-primary" name="btneleccion">Aceptar</button>
						      </div>
						    </div>
						  </div>
						</div>
					<!--Fin Modal -->
					
			</form>
			
			
	<!-- FIN FORMULARIO TIPO ELEMENTO -->
	
		<%}else if(elemento != null){%>
			
	<!-- FORMULARIO ELEMENTO -->		
			
			<form action="FinalizarModificacionElemento.servlet" method="post" style="width:50%;">
			
					<div class="form-group formulario">
					    <label for="txtid">ID</label>
					    <input type="text" class="form-control" id="txtid" name="txtid" value="<%=elemento.getId_elemento()%>" readonly="true">
					  </div>
					  <div class="form-group formulario">
					    <label for="txtusuario">*Nombre</label>
					    <input type="text" class="form-control" id="txtnombre" name="txtnombre" required="" value="<%=elemento.getNombre()%>">
					  </div>	

					  
					  
					  <div class="form-group">
					    <label for="txtapellido">Autor</label>
					    <input type="text" class="form-control" id="txtautor" name="txtautor" value="<%=elemento.getAutor() %>">
					  </div>
					  
					   <div class="form-group">
					    <label for="txtapellido">G&eacute;nero</label>
					    <input type="text" class="form-control" id="txtgenero" name="txtgenero" value="<%=elemento.getGenero() %>">
					  </div>
					  
					  <div class="form-group">
					    <label for="txtnombre">Descripci&oacute;n</label>
					    <input type="text" class="form-control" id="txtdescripcion" name="txtdescripcion" value="<%=elemento.getDescripcion() %>">
					 	<small class="form-text text-muted">*Campos obligatorios</small>
					  </div>
					  
					  <div class="form-group" style="display:none;">
					    <label for="txtapellido">Stock</label>
					    <input type="text" class="form-control" id="txtstock" name="txtstock" value="<%=elemento.getStock() %>">
					  	
					  </div>
					  
					  
					  <div class="btn-group" role="group" aria-label="Basic example">
							
						  		  <button type="button" class="btn btn-primary" name="btnmodificar" onclick="return validarModificacionElemento();">
					  				Modificar
									</button>
						  		  <a class="btn btn-outline-secondary" href="Start">Cancelar</a>
					  </div>
					  
					  
					  <!-- Modal -->
						<div class="modal fade" id="frmModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLabel">Modificar elemento</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      <div class="modal-body">
						        ¿Está seguro que desea modificar este elemento?
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
						         <button type="submit" class="btn btn-outline-primary" name="btneleccion" value="<%=elemento.getId_elemento()%>">Aceptar</button>
						      </div>
						    </div>
						  </div>
						</div>
					<!--Fin Modal -->
					  
					  
					  
					
			</form>
			
			
			
	<!-- FIN FORMULARIO ELEMENTO -->		
		<%}%>
		
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
							  <li class="nav-item">
							    <a class="nav-link itemmapa" href="#">Ayuda</a>
							  </li>
							  <li class="nav-item">
							    <a class="nav-link itemmapa" href="#">Contacto</a>
							  </li>
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
    	$("#datepicker1").datepicker($.datepicker.regional["es"]);
    	$("#datepicker2").datepicker($.datepicker.regional["es"]);
    	$("#datepicker3").datepicker($.datepicker.regional["es"]);
    	});
	</script>

  </body>
</html>