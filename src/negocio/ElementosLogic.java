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
		//this.pers.remove(this.getByDni(el));
		this.elementosD.delete(el);
	}
	
	public void update(Elemento el)throws Exception{
		
		this.elementosD.update(el);
	}

	public Elemento GetOne (String doc){
	
		for (Elemento elementos : lista) {
			if (elementos.getNombre() == doc ) {
				return elementos;
			}
			
		}
		return null;
	}	

	
	public Elemento GetByNombre(Elemento el) throws Exception{
		
		
		return elementosD.getByNombre(el);
		
		//return this.lista.get(this.lista.indexOf(el));
		
		/*if(elementos.Equals(el)){
	  return this.GetByNombre(el.getNombre());
		}
		return null;*/
	
	}
	
	
	public Elemento GetByNombre (String nombre) throws Exception{
		
		Elemento el=new Elemento();
		el.setNombre(nombre);
		return GetByNombre(el);
		
		/*for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getNombre() == nombre) {
				return lista.get(i);
			}
			
		}
		return null;*/
		
	}
	
	
	
	
	/*public void EliminarElemento(Elemento el) throws Exception{
	
	  elementosD.delete(el);
		
	
	}	
*/



		public ArrayList<Elemento> GetAll() throws Exception{
	
			return elementosD.getAll();
			
		}


		/*public void ModificarElemento(Elemento el) throws Exception {
			

			elementosD.update(el);
			
		}*/
		
		public ArrayList<Tipo_Elemento> getTipo_Elementos() throws Exception{
			return ted.getAll();
			
		}
	
}
