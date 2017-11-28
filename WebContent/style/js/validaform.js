
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
		if(isNaN(dni))    
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
	if(isNaN(dni))    
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
	
	if (fecharegistro > fechainicio) {
		alert("La fecha de registro no puede ser mayor a la fecha de inicio");
		document.getElementById("datepicker1").value = fechainicio;
		return false;
		
	}else
	if (fechainicio >= fechafin) {
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
	
	if (fecharegistro > fechainicio) {
		alert("La fecha de registro no puede ser mayor a la fecha de inicio");
		document.getElementById("datepicker1").value = fechainicio;
		
	}
	else
	if (fechainicio >= fechafin) {
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
		return true;
	
	
	
}




