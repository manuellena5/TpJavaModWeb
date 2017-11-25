package negocio;

import java.util.ArrayList;

import data.DataElementos;
import data.DataTipo_Elementos;
import entidades.Elemento;
import entidades.Tipo_Elemento;

public class ElementosLogic {

	
	private Elemento elementos;
	private DataElementos elementosD;
	private DataTipo_Elementos ted;
	ArrayList<Elemento> lista = new ArrayList<Elemento>();
	
	
	public ElementosLogic(){
	
		elementos = new Elemento(); 
		elementosD = new DataElementos();
		ted = new DataTipo_Elementos();
		
	}


	public void add(Elemento el) throws Exception{
	
		
		elementosD.add(el);
	
	}
	
	public void delete(Elemento el)throws Exception{
		
		this.elementosD.delete(el);
	}
	
	public void update(Elemento el)throws Exception{
		
		this.elementosD.update(el);
	}

	public Elemento GetOne (int id) throws Exception{
	
		return elementosD.GetOne(id);
	}	

	
	public Elemento GetByNombre(Elemento el) throws Exception{
		
		
		return elementosD.getByNombre(el);
		
	
	}
	
	
	public Elemento GetByNombre (String nombre) throws Exception{
		
		Elemento el=new Elemento();
		el.setNombre(nombre);
		return GetByNombre(el);
		
		
		
	}
		
	
	public ArrayList<Elemento> getByTipoElemento(int idtipoelemento) throws Exception{
			
			Tipo_Elemento te = new Tipo_Elemento();
			te.setId_tipoelemento(idtipoelemento);
			
			
			return getByTipoElemento(te);
			
		}
		
		public ArrayList<Elemento> getByTipoElemento(Tipo_Elemento te) throws Exception{
			
			
			return elementosD.getByTipoElemento(te);
			
		}

		public ArrayList<Elemento> GetAll() throws Exception{
	
			return elementosD.getAll();
			
		}


		
		
		public ArrayList<Tipo_Elemento> getTipo_Elementos() throws Exception{
			return ted.getAll();
			
		}
	
}
