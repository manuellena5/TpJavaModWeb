package negocio;

import java.util.ArrayList;

import data.DataTipo_Elementos;
import entidades.Tipo_Elemento;

public class Tipo_ElementosLogic {

	
	private Tipo_Elemento tipoElementos;
	private DataTipo_Elementos tipoElementosD;
	ArrayList<Tipo_Elemento> lista = new ArrayList<Tipo_Elemento>();
	
	
	public Tipo_ElementosLogic(){
	
		tipoElementos = new Tipo_Elemento(); 
		tipoElementosD = new DataTipo_Elementos();
		
	}


	public void add(Tipo_Elemento te) throws Exception{
	
		
		tipoElementosD.add(te);
	
	}
	
	public void delete(Tipo_Elemento te)throws Exception{
		//this.pers.remove(this.getByNombre(te));
		this.tipoElementosD.delete(te);
	}
	
	public void update(Tipo_Elemento te)throws Exception{
		
		this.tipoElementosD.update(te);
	}

	public Tipo_Elemento GetOne (String doc){
	
		for (Tipo_Elemento tipoElementos : lista) {
			if (tipoElementos.getNombre() == doc ) {
				return tipoElementos;
			}
			
		}
		return null;
	}	

	
	public Tipo_Elemento GetByNombre(Tipo_Elemento te) throws Exception{
		
		
		return tipoElementosD.getByNombre(te);
		
	
	}
	
	
	public Tipo_Elemento GetByNombre (String nombre) throws Exception{
		
		Tipo_Elemento te=new Tipo_Elemento();
		te.setNombre(nombre);
		return GetByNombre(te);
		
		
		
	}
	
	public Tipo_Elemento GetById(Tipo_Elemento te) throws Exception{
		
		
		return tipoElementosD.getById(te);
		
	
	}
	
	public Tipo_Elemento GetById(int id) throws Exception{
		
		Tipo_Elemento te=new Tipo_Elemento();
		te.setId_tipoelemento(id);
		return GetById(te);
				
	}
	
		public ArrayList<Tipo_Elemento> GetAll() throws Exception{
	
			return tipoElementosD.getAll();
			
		}


		
	
}
