package entidades;

public class Elemento {
	
	private int id_elemento;
	private String nombre;
	private int stock;
	private String descripcion;
	private String autor;
	private String genero;
	private int id_tipoelemento;
	private Tipo_Elemento Tipo_Elemento;

		


	
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	
	
	public int getId_elemento() {
		return id_elemento;
	}
	public void setId_elemento(int id_clase) {
		this.id_elemento = id_clase;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Tipo_Elemento getTipo_Elemento() {
		return Tipo_Elemento;
	}

	public void setTipo_Elemento(Tipo_Elemento tipo_Elemento) {
		Tipo_Elemento = tipo_Elemento;
	}
	
	

	public Elemento(int id_elemento, String nombre, int stock, String descripcion, String autor,String genero) {
		super();
		this.id_elemento = id_elemento;
		this.nombre = nombre;
		this.stock = stock;
		this.descripcion = descripcion;
		this.autor = autor;
		this.genero = genero;
	}
	
	public Elemento(){
		
	}
	
	/*@Override
	public String toString(){
		return this.getNombre();
	}
	
	
	
	@Override
	public boolean equals(Object o){
		return (o instanceof Elemento && ((Elemento)o).getId_elemento()==this.getId_elemento());
	}
	
	@Override
	public int hashCode(){
		return ((Integer)this.getId_elemento()).hashCode();
	}*/

	
	
	

}
