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
		
		this.categoriasD.delete(cat);
	}
	
	public void update(Categoria cat)throws Exception{
		
		this.categoriasD.update(cat);
	}

	
	public Categoria GetByDescripcion(Categoria cat) throws Exception{
		
		
		return categoriasD.getByDescripcion(cat);
		
	
	
	}
	
	
	public Categoria GetByDescripcion (String descripcion) throws Exception{
		
		Categoria cat=new Categoria();
		cat.setDescripcion(descripcion);
		return GetByDescripcion(cat);
		
		
	}
	
	public Categoria GetById(int id) throws Exception{
		
		Categoria categoria = new Categoria();
		categoria.setId_Categoria(id);
		
		return GetById(categoria);
		
	}
	
	public Categoria GetById(Categoria cat) throws Exception{
		
		
		return categoriasD.getById(cat);
		
	}
		

	public ArrayList<Categoria> GetAll() throws Exception{
	
			return categoriasD.getAll();
			
		}

		
		
	
}
