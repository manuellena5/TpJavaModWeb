package entidades;

public class Categoria {
	
	private int id_categoria;
	private String descripcion;
	
	public int getId_Categoria() {
		return id_categoria;
	}
	public void setId_Categoria(int id_Categoria) {
		this.id_categoria = id_Categoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString(){
		return this.getDescripcion();
	}
	
	@Override
	public boolean equals(Object o){
		return (o instanceof Categoria && ((Categoria)o).getId_Categoria()==this.getId_Categoria());
	}
	
	@Override
	public int hashCode(){
		return ((Integer)this.getId_Categoria()).hashCode();
	}
}
