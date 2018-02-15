
function validarfrmModificarPersona(){			
		var pass1 = document.getElementById("txtpass").value;
		var pass2 = document.getElementById("txtpass2").value;
		var dni = document.getElementById("txtdni").value;
		var usuario = document.getElementById("txtusuario").value;
		var nombre = document.getElementById("txtnombre").value;
		var apellido = document.getElementById("txtapellido").value;
		
		
		if (nombre == null || nombre.length == 0) {
			alert('Ingrese un nombre valido');   
			return false; 
			
		}else
		if (apellido == null || apellido.length == 0) {
				alert('Ingrese un apellido valido');   
				return false; 
				
		}else
		if(isNaN(dni) || dni == null || dni.length == 0 || dni.length < 7 || dni.length > 9)    
		{  
			alert('Ingrese un numero de dni valido');   
			return false;  
		}
		else      
		if (usuario.length == 0 || usuario.length >= 20 || usuario.length < 4)  
		{  
		alert('Nombre de usuario incorrecto');  
		return false;  
		}else
		
		if (pass1.length == 0 || pass2.length == 0 || pass1.length >= 20 || pass2.length >= 20 || pass1.length < 7 || pass2.length < 7)  
		{  
		alert('Longitud de password incorrecta');  

		return false;  
		}else 
		 if(pass1 != pass2){
			
			alert('Sus password deben coincidir');

			return false;
			}
		$('#frmModal').modal('show');
		return true;

	}

function validarfrmNuevapersona(){			
	var pass1 = document.getElementById("txtpass").value;
	var pass2 = document.getElementById("txtpass2").value;
	var dni = document.getElementById("txtdni").value;
	var usuario = document.getElementById("txtusuario").value;
	var categoria = document.getElementById("txtidcategoria").selectedIndex;
	var nombre = document.getElementById("txtnombre").value;
	var apellido = document.getElementById("txtapellido").value;
	
	if(categoria == null || categoria == 0){
		alert('Seleccione una categoria');   
		return false;  
	}else
	if (nombre == null || nombre.length == 0) {
		alert('Ingrese un nombre valido');   
		return false; 
		
	}else
	if (apellido == null || apellido.length == 0) {
			alert('Ingrese un apellido valido');   
			return false; 
			
	}else	
	if(isNaN(dni) || dni == null || dni.length == 0 || dni.length < 7 || dni.length > 9)    
	{  
		alert('Ingrese un numero de dni valido');   
		return false;  
	}
	else      
	if (usuario.length == 0 || usuario.length >= 20 || usuario.length < 4)  
	{  
	alert('Nombre de usuario incorrecto');  
	return false;  
	}else
	
	if (pass1.length == 0 || pass2.length == 0 || pass1.length >= 20 || pass2.length >= 20 || pass1.length < 7 || pass2.length < 7)  
	{  
	alert('Longitud de password incorrecta');  

	return false;  
	}else 
	 if(pass1 != pass2){
		
		alert('Sus password deben coincidir');

		return false;
		}
	$('#frmModal').modal('show');
	return true;

}

function validarfrmNuevaReserva(){
	
	var tipoelemento = document.getElementById("eleccion").selectedIndex;
	if(tipoelemento == null || tipoelemento == 0){
		alert('Seleccione un tipo de elemento');   
		return false;  
	}
		return true;
}


function validarfrmFechasAltaReserva(){
	
	var fecharegistro = document.getElementById("datepicker1").value;
	var fechainicio = document.getElementById("datepicker2").value;
	var fechafin = document.getElementById("datepicker3").value;
	
	if ($.datepicker.parseDate('dd/mm/yy', fecharegistro) > $.datepicker.parseDate('dd/mm/yy', fechainicio)) {
		alert("La fecha de registro no puede ser mayor a la fecha de inicio");
		document.getElementById("datepicker1").value = fechainicio;
		return false;
		
	}else
	if ($.datepicker.parseDate('dd/mm/yy', fechainicio) >= $.datepicker.parseDate('dd/mm/yy', fechafin)) {
		alert("La fecha de fin debe ser mayor a la fecha de inicio");
		document.getElementById("datepicker3").value = fechainicio;
		return false;
	}
	
	
	return true;
	
}

function validarfecha(){
	var fecharegistro = document.getElementById("datepicker1").value;
	var fechainicio = document.getElementById("datepicker2").value;
	var fechafin = document.getElementById("datepicker3").value;
	
	if ($.datepicker.parseDate('dd/mm/yy', fecharegistro) > $.datepicker.parseDate('dd/mm/yy', fechainicio)) {
		alert("La fecha de registro no puede ser mayor a la fecha de inicio");
		document.getElementById("datepicker1").value = fechainicio;
		
	}
	else
	if ($.datepicker.parseDate('dd/mm/yy', fechainicio) >= $.datepicker.parseDate('dd/mm/yy', fechafin)) {
		alert("La fecha de fin debe ser mayor a la fecha de inicio");
		document.getElementById("datepicker3").value = fechainicio;
	}
}


function validarNuevoElemento(){
	
	var tipoelemento = document.getElementById("txtidtipoelemento").selectedIndex;
	var nombre = document.getElementById("txtnombre").value;
	var autor = document.getElementById("txtautor").value;
	var genero = document.getElementById("txtgenero").value;
	var descripcion = document.getElementById("txtdescripcion").value;
	
	
	if(tipoelemento == null || tipoelemento == 0){
		alert('Seleccione un tipo de elemento'); 
		return false;  
	}else
	if (nombre == null || nombre == 0) {
		alert('Debe ingresar un nombre');   
		return false; 
	}
		$('#frmModal').modal('show');
		return true;
	
	
	
}

function validarNuevoTipoElemento(){
	var acceso = document.getElementById("acceso").selectedIndex;
	var nombre = document.getElementById("txtnombre").value;
	var maximaspendientes = document.getElementById("txtcantmaxreservaspend").value;
	
	if(nombre == null || nombre == 0){
		alert('Debe ingresar un nombre'); 
		return false;  
	}else
	if (maximaspendientes == null || maximaspendientes == 0) {
		alert('Debe ingresar cantidad maxima de reservas pendientes');   
		return false; 
	}else
	if (acceso == null || acceso == 0) {
		alert('Debe seleccionar que tipo de acceso va a tener el tipo de elemento');
		return false;
	}
		$('#frmModal').modal('show');
		return true;
	
}

function validarModificacionTipoElemento(){
	var acceso = document.getElementById("acceso").selectedIndex;
	var maximaspendientes = document.getElementById("txtcantmax").value;
	if (maximaspendientes == null || maximaspendientes == 0) {
		alert('Debe ingresar cantidad maxima de reservas pendientes');   
		return false; 
	}else
		if (acceso == null || acceso == 0) {
			alert('Debe seleccionar que tipo de acceso va a tener el tipo de elemento');
			return false;
		}
		$('#frmModal').modal('show');
		return true;
	
}

function validarModificacionElemento(){
	
	var nombre = document.getElementById("txtnombre").value;
	
	if (nombre == null || nombre == 0) {
		alert('Debe ingresar un nombre');   
		return false; 
	}
		$('#frmModal').modal('show');
		return true;
	
	
}


function tomariddelboton(boton){
	var id = boton.id;
	var botonenviar = document.getElementById("btnenviar");
	botonenviar.value = id;
	$('#frmModal').modal('show');
	
}

function mostrarmodal(){
	$('#frmModal').modal('show');
	
}

function setearfechas(){
	
	var divfechainicio = document.getElementById("divfechainicio");
	var divfechafin = document.getElementById("divfechafin");
	var smalltext1 = document.getElementById("smalltext1");
	var smalltext2 = document.getElementById("smalltext2");
	
	
	
	if (divfechainicio.style.display == "block") {
		divfechafin.style.display = "block";
		smalltext2.style.display = "none";
	}
	
	divfechainicio.style.display = "block";
	smalltext1.style.display = "none";
	
	var fechafin = document.getElementById("datepicker3").value;
    var tipoelemento = document.getElementById("nombretipoelemento").value;
    var fecharegistro = $.datepicker.parseDate('dd/mm/yy', document.getElementById("datepicker1").value);
    var fechainicio = $.datepicker.parseDate('dd/mm/yy', document.getElementById("datepicker2").value);
    let dias = ["L", "M", "X", "J", "V", "S", "D"];
    
    
    if (dias[fecharegistro.getDay()-1] == "V") {
    			 
    			 var fecha = $.datepicker.parseDate('dd/mm/yy', document.getElementById("datepicker1").value);
    			 var duracion = 3;
    			 $("#datepicker2").datepicker("option", "minDate", fecharegistro);
    			 fecha.setDate(fecharegistro.getDate() + duracion);
    			 $("#datepicker2").datepicker("option", "maxDate", fecha);

	}
    else{
    			 var duracion = 1;
    			 var fecha = $.datepicker.parseDate('dd/mm/yy', document.getElementById("datepicker1").value);
    			 
    			 $("#datepicker2").datepicker("option", "minDate", fecharegistro);
    			 fecha.setDate(fecharegistro.getDate() + duracion);
    			 $("#datepicker2").datepicker("option", "maxDate", fecha);
    }
    
    
    
	
}






