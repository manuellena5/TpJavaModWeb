package entidades;


import java.util.*;

public class Reserva{

	private Persona persona;
	private Elemento elemento;
	private Date fecha_registro;
	private Date fecha_inicio;
	private Date fecha_fin;
	private String detalle;
	private String estado;
	
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Elemento getElemento() {
		return elemento;
	}
	public void setElemento(Elemento elemento) {
		this.elemento = elemento;
	}
	
	
	public Date getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public Date getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Date getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	
	public Reserva(int id_persona, int id_elemento, Date fecha_registro, Date fecha_inicio,
			Date fecha_fin, String detalle, String estado) {
		super();
		this.fecha_registro = fecha_registro;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.detalle = detalle;
		this.estado = estado;
	}
	
	public Reserva(){
		
	}
	
	
	
	
}
