package negocio;

import java.util.ArrayList;

import data.DataCategorias;
import data.DataTipo_Elementos;
import entidades.Categoria;
import entidades.Tipo_Elemento;

public class CategoriasLogic {

	
	private Categoria categorias;
	private DataCategorias categoriasD;
	
	ArrayList<Categoria> lista = new ArrayList<Categoria>();
	
	
	public CategoriasLogic(){
	
		categorias = new Categoria(); 
		categoriasD = new DataCategorias();
		
	}


	public void add(Categoria cat) throws Exception{
	
		
		categoriasD.add(cat);
	
	}
	
	public void delete(Categoria cat)throws Exception{
		//this.pers.remove(this.getByDescripcion(el));
		this.categoriasD.delete(cat);
	}
	
	public void update(Categoria cat)throws Exception{
		
		this.categoriasD.update(cat);
	}

	public Categoria GetOne (String doc){
	
		for (Categoria categorias : lista) {
			if (categorias.getDescripcion() == doc ) {
				return categorias;
			}
			
		}
		return null;
	}	

	
	public Categoria GetByDescripcion(Categoria cat) throws Exception{
		
		
		return categoriasD.getByDescripcion(cat);
		
		//return this.lista.get(this.lista.indexOf(cat));
		
		/*if(categorias.Equals(cat)){
	  return this.GetByDescripcion(cat.getDescripcion());
		}
		return null;*/
	
	}
	
	
	public Categoria GetByDescripcion (String descripcion) throws Exception{
		
		Categoria cat=new Categoria();
		cat.setDescripcion(descripcion);
		return GetByDescripcion(cat);
		
		/*for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getDescripcion() == descripcion) {
				return lista.get(i);
			}
			
		}
		return null;*/
		
	}
	
	
	
	
	public void EliminarCategoria(Categoria cat) throws Exception{
	
	 lista.remove(this.GetByDescripcion(cat));
		
	
	}	




		public ArrayList<Categoria> GetAll() throws Exception{
	
			return categoriasD.getAll();
			
		}


		public void ModificarCategoria(Categoria cat) throws Exception {
			
			this.EliminarCategoria(cat);
			this.add(cat);
			
		}
		
		
	
}
