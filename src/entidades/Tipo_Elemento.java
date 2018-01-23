package entidades;

import java.io.Serializable;

public class Tipo_Elemento implements Serializable{

	private int id_tipoelemento;
	private String nombre;
	private int cantMaxReservasPend;
	private String acceso;
	
	
	
	public int getId_tipoelemento() {
		return id_tipoelemento;
	}
	public void setId_tipoelemento(int id_tipoelemento) {
		this.id_tipoelemento = id_tipoelemento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantMaxReservasPend() {
		return cantMaxReservasPend;
	}
	public void setCantMaxReservasPend(int cantMaxReservasPend) {
		this.cantMaxReservasPend = cantMaxReservasPend;
	}
	
	public String getAcceso() {
		return acceso;
	}
	public void setAcceso(String acceso) {
		this.acceso = acceso;
	}
	
	public Tipo_Elemento(int id_tipoelemento, String nombre, int cantMaxReservasPend, String acceso) {
		super();
		this.id_tipoelemento = id_tipoelemento;
		this.nombre = nombre;
		this.cantMaxReservasPend = cantMaxReservasPend;
		this.acceso = acceso;
	}
	
	public Tipo_Elemento()
	{
		
	}
	
	@Override
	public String toString(){
		return this.getNombre();
	}
	
	@Override
	public boolean equals(Object o){
		return (o instanceof Tipo_Elemento && ((Tipo_Elemento)o).getId_tipoelemento()==this.getId_tipoelemento());
	}
	
	@Override
	public int hashCode(){
		return ((Integer)this.getId_tipoelemento()).hashCode();
	}
	
}
