package negocio;

import java.util.ArrayList;

import data.DataCategorias;
import data.DataPersona;
import entidades.Categoria;
import entidades.Persona;

public class PersonaLogic {

	
	private Persona per;
	private DataPersona perD;
	private DataCategorias perC;
	ArrayList<Persona> lista = new ArrayList<Persona>();
	
	
	public PersonaLogic(){
	
		per = new Persona(); 
		perD = new DataPersona();
		perC = new DataCategorias();
		
	}


	public void add(Persona p) throws Exception{
	
		
		perD.add(p);
	
	}
	
	public void delete(Persona p)throws Exception{
		
		this.perD.delete(p);
	}
	
	public void update(Persona p)throws Exception{
		
		this.perD.update(p);
	}

	
	public Persona GetByDni(Persona p) throws Exception{
		
		
		return perD.getByDni(p);
		
		
	}
	
	
	public Persona GetByDni (String doc) throws Exception{
		
		Persona p=new Persona();
		p.setDni(doc);
		return GetByDni(p);
		
		
	}
	

	
	public Persona GetById(Persona p) throws Exception{
		
		
		return perD.getById(p);
		
		
	
	}
	
	
	public Persona GetById(int id) throws Exception{
		
		Persona p=new Persona();
		p.setId_persona(id);
		return GetById(p);
		
		
	}





		public ArrayList<Persona> GetAll() throws Exception{
	
			return perD.getAll();
			
		}
		
		public ArrayList<Categoria> getCategorias() throws Exception{
			return perC.getAll();
		}
		
		public Persona login(Persona per) throws Exception{
			return perD.getLogedUser(per);
		}
	
}
