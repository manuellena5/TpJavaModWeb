
function validarfrm(){			
		var pass1 = document.getElementById("txtpass").value;
		var pass2 = document.getElementById("txtpass2").value;
		var dni = document.getElementById("txtdni").value;
		var usuario = document.getElementById("txtusuario").value;
		
		
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


/*function dni_solonumeros(dni){

	var numbers = /^[0-9]+$/;  
	if(dni.match(numbers))  
	{  
		
	return true;  
	}  
	else  
	{  
	alert('Ingrese un numero de dni valido');   
	return false;  
	}  
	
	
}
function usuario_validacion(usu,min,max){
	var usu_len = usu.length;  
	if (usu_len == 0 || usu_len >= max || usu_len < min)  
	{  
	alert('Nombre de usuario incorrecto');  
	return false;  
	}  
	
	return true;  
	}

function password_validacionlongitud(pass1,pass2,min,max){
	
	var pass1_len = pass1.length;
	var pass2_len = pass2.length;

	if (pass1_len == 0 || pass2_len == 0 || pass1_len >= max || pass2_len >= max || pass1_len < min || pass2_len < min)  
	{  
	alert('Longitud de password incorrecta');  

	return false;  
	}  
	
	return true; 
}

function password_validacionequals(pass1,pass2)
{
	if(pass1 != pass2){
		
		alert('Sus password deben coincidir');

		return false;
		}

	return true;

}*/





