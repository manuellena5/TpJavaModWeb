package negocio;

import java.util.ArrayList;

import data.DataReservas;
import entidades.Elemento;
import entidades.Reserva;
import entidades.Persona;
import entidades.Tipo_Elemento;

public class ReservasLogic {

	
	private Reserva reservas;
	private DataReservas reservasD;
	ArrayList<Reserva> lista = new ArrayList<Reserva>();
	


	public ReservasLogic(){
	
		reservas = new Reserva(); 
		reservasD = new DataReservas();
		
	}


	public void add(Reserva res) throws Exception{
	
		
		reservasD.add(res);
	
	}
	
	public void delete(Reserva res)throws Exception{
		//this.pers.remove(this.getByDni(el));
		this.reservasD.delete(res);
	}
	
	public void update(Reserva res)throws Exception{
		
		this.reservasD.update(res);
	}

	public Reserva GetOne (int id_persona, int id_elemento){
	
		for (Reserva reservas : lista) {
			if (reservas.getPersona().getId_persona() == id_persona && reservas.getElemento().getId_elemento() == id_elemento) {
				return reservas;
			}
			
		}
		return null;
	}	

	
	public Reserva GetByIdPersona(Reserva res) throws Exception{
		
		
		return reservasD.getByIdPersona(res);
		
		
	
	}
	
	
	public Reserva GetByNombre (int id_persona, int id_elemento) throws Exception{
		
		Reserva res=new Reserva();
		res.getPersona().setId_persona(id_persona);
		res.getElemento().setId_elemento(id_elemento);
		return GetByIdPersona(res);
		
		
		
	}

	
	public ArrayList<Reserva> GetAll() throws Exception{
	
			return reservasD.getAll();
			
		}


	
		
		
		public boolean ValidarCantidadReservasPendientes(Reserva res) throws Exception{
			
			
			int cantReservasPendPersona = reservasD.getReservasPendientes(res);
			if(cantReservasPendPersona < res.getElemento().getTipo_Elemento().getCantMaxReservasPend())
			{return true;}
			else
			{return false;}
		
		
		}
}
