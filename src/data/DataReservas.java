package data;

import java.sql.*;
import java.util.ArrayList;
import entidades.*;
import util.AppDataException;
import javax.swing.JOptionPane;
import java.security.KeyStore.ProtectionParameter;

public class DataReservas {
	

	
	public ArrayList<Reserva> getAll() throws Exception{
				
				Statement stmt=null;
				ResultSet rs=null;
				ArrayList<Reserva> reservas = new ArrayList<Reserva>();
				try {
					  stmt = FactoryConexion.getInstancia().getConn().createStatement();
					  rs = stmt.executeQuery("select r.`id_persona`,r.`id_elemento`, r.`fecha_inicio`,r.`fecha_fin`,r.`detalle`,r.`estado`,"
					  		+ " r.`fecha_registro`,p.`nombre` nombrePersona,p.`apellido`,p.`dni`,p.`usuario`,e.`nombre` nombreElemento,"
							+ " e.`autor`,e.`genero`, te.nombre tipoelemento, te.id_tipoelemento, te.cantMaxReservasPend from reservas r inner join personas p on p.`id_persona` = r.`id_persona`" 
					  		+ " inner join elementos e on e.`id_elemento` = r.`id_elemento`"
							+ " inner join tipo_elementos te on te.`id_tipoelemento` = e.`id_tipoelemento` "
							+ " order by p.apellido, r.fecha_registro, r.fecha_inicio, r.fecha_fin");
					  
					  
					  
					  
				if (rs != null) {
						while (rs.next()) {
								Reserva res = new Reserva();
								Tipo_Elemento te = new Tipo_Elemento();
								res.setElemento(new Elemento());
								res.setPersona(new Persona());
																
								te.setId_tipoelemento(rs.getInt("id_tipoelemento"));
								te.setNombre(rs.getString("tipoelemento"));
								te.setCantMaxReservasPend(rs.getInt("cantMaxReservasPend"));
								
								res.setFecha_registro(rs.getDate("fecha_registro"));
								res.setFecha_inicio(rs.getDate("fecha_inicio"));
								res.setFecha_fin(rs.getDate("fecha_fin"));
								res.setDetalle(rs.getString("detalle"));
								res.setEstado(rs.getString("estado"));
								
								
								
								res.getElemento().setId_elemento(rs.getInt("id_elemento"));
								res.getElemento().setNombre(rs.getString("nombreElemento"));
								res.getElemento().setAutor(rs.getString("autor"));
								res.getElemento().setGenero(rs.getString("genero"));
								res.getElemento().setTipo_Elemento(te);
								 
								
								res.getPersona().setId_persona(rs.getInt("id_persona"));
								res.getPersona().setNombre(rs.getString("nombrePersona"));
								res.getPersona().setApellido(rs.getString("apellido"));
								res.getPersona().setDni(rs.getString("dni"));
								res.getPersona().setUsuario(rs.getString("usuario"));
								
								
								
								reservas.add(res);
										}
					}
				
				}catch (SQLException e) {
					
					throw e;
				}
				catch (AppDataException ade){
					throw ade;
				}
				
				try {
					if(rs!=null) rs.close();
					if(stmt!=null) stmt.close();
					FactoryConexion.getInstancia().releaseConn();
				} catch (SQLException e) {
					
					throw e;
				}
				
					return reservas;
					
					
				}
 
	public Reserva getByIdPersona(Reserva reservas) throws Exception{
	
			Reserva res = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				 
				stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
						"select p.id_persona, e.id_elemento, r.fecha_registro, r.fecha_inicio, r.fecha_fin, r.detalle, r.estado from reservas r "
						+ "inner join elementos e on e.id_elemento=r.id_elemento inner join personas p on p.id_persona=r.id_persona where r.id_persona=?");
						
				stmt.setInt(1, reservas.getPersona().getId_persona());
				rs = stmt.executeQuery();
				
				if (rs!=null && rs.next()) {
					res = new Reserva();
					res.setElemento(new Elemento());
					res.setPersona(new Persona());
					res.setFecha_registro(rs.getDate("fecha_registro"));
					res.setFecha_inicio(rs.getDate("fecha_inicio"));
					res.setFecha_fin(rs.getDate("fecha_fin"));
					res.setDetalle(rs.getString("detalle"));
					res.setEstado(rs.getString("estado"));
					
					res.getElemento().setId_elemento(rs.getInt("id_elemento"));
					res.getElemento().setNombre(rs.getString("nombreElemento"));
					res.getElemento().setAutor(rs.getString("autor"));
					res.getElemento().setGenero(rs.getString("genero"));
					
					res.getPersona().setId_persona(rs.getInt("id_persona"));
					res.getPersona().setNombre(rs.getString("nombrePersona"));
					res.getPersona().setApellido(rs.getString("apellido"));
					res.getPersona().setDni(rs.getString("dni"));
					res.getPersona().setUsuario(rs.getString("usuario"));
				
				}
				
				
			
			
			} catch (Exception e) {
				
				throw e;
			}
			
				try {
					if (rs != null) {rs.close();}
					if (stmt != null) {stmt.close();}
					FactoryConexion.getInstancia().releaseConn();	
				} catch (SQLException e) {
					throw e;
				}
			
			return res;
		}
	
	public void add(Reserva res) throws Exception{
			PreparedStatement stmt=null;
			ResultSet keyResultSet=null;
			try {
				stmt=FactoryConexion.getInstancia().getConn()
						.prepareStatement(
						"insert into reservas(id_elemento,id_persona,fecha_registro, fecha_inicio, fecha_fin, detalle, estado) values (?,?,?,?,?,?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS
						);
				
				stmt.setInt(1,res.getElemento().getId_elemento());
				stmt.setInt(2, res.getPersona().getId_persona());
				stmt.setDate(3,(Date) res.getFecha_registro());
				stmt.setDate(4,(Date)res.getFecha_inicio());
				stmt.setDate(5,(Date) res.getFecha_fin());
				stmt.setString(6, res.getDetalle());
				stmt.setString(7, res.getEstado());
				
				stmt.executeUpdate();
				
				
			} catch (SQLException | AppDataException e) {
				throw e;
			}
			try {
				if(keyResultSet!=null)keyResultSet.close(); 
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw e;
			}
		}
		
	public void update(Reserva res) throws Exception{
			PreparedStatement stmt=null;
			
			try {
				stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
						"update reservas set fecha_inicio=?, fecha_fin=?, detalle=?, estado=? where id_persona=? and id_elemento=? and fecha_registro=?");
				
				
				stmt.setDate(1,(Date) res.getFecha_inicio());
				stmt.setDate(2,(Date) res.getFecha_fin());
				stmt.setString(3, res.getDetalle());
				stmt.setString(4, res.getEstado());
				stmt.setInt(5, res.getPersona().getId_persona());
				stmt.setInt(6, res.getElemento().getId_elemento());
				stmt.setDate(7,(Date) res.getFecha_registro());
			
				stmt.executeUpdate();
				
				
			} catch (SQLException | AppDataException e) {
				
				throw e;
			} 
			try {
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw e;
			}
		} 
		
	public void delete(Reserva res) throws Exception{
			PreparedStatement stmt=null;
			
			try {
				stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
						"delete from reservas where id_persona=? and id_elemento=? and fecha_registro=?");
				
				stmt.setInt(1, res.getPersona().getId_persona());
				stmt.setInt(2, res.getElemento().getId_elemento());
				stmt.setDate(3, (Date) res.getFecha_registro());
				stmt.executeUpdate();
				
				
			} catch (SQLException | AppDataException e) {
				
				throw e;
			} 
			try {
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw e;
			}
		} 
		 
	public int getReservasPendientes(Reserva res) throws Exception{
			
			PreparedStatement stmt = null;
			ResultSet rs = null;
			int cantReservasPendientesPersona=0;
		
			try {
				stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select count(*) cantReservasPendientesPersona from reservas r" 
				  		+ " inner join elementos e on e.`id_elemento` = r.`id_elemento`"
						+ " inner join `tipo_elementos` te on te.id_tipoelemento = e.id_tipoelemento"
						+ " where r.id_persona=? and e.id_tipoelemento=? and r.estado='Activa' ");
				  stmt.setInt(1, res.getPersona().getId_persona());
				  stmt.setInt(2, res.getElemento().getTipo_Elemento().getId_tipoelemento());
				  
				  
			
			
			rs = stmt.executeQuery();  
			if(rs!=null && rs.next()) {
				
				cantReservasPendientesPersona = rs.getInt("cantReservasPendientesPersona");
			}
			
			
			}catch (SQLException e) {
				
				throw e;
			}
			catch (AppDataException ade){
				throw ade;
			}
			
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				
				throw e;
			}
			
				return cantReservasPendientesPersona;
		}


	public Reserva GetOne(int id_persona, int id_elemento, Date fecharegistro) throws Exception {
			
			Tipo_Elemento te = null;
			Reserva res = null;
			Categoria cat = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				 
				stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
						"select r.`id_elemento`,r.`id_persona`,r.`fecha_registro`,r.`fecha_inicio`,r.`fecha_fin`,r.`detalle`,r.`detalle`,r.estado,"
								+ "e.`nombre` nombreelemento,e.`autor`,e.`genero`,e.`descripcion`,e.`stock`,e.`id_tipoelemento`,"
								+ "te.`nombre` nombretipoelemento,te.`cantMaxReservasPend`,"
								+ "p.`nombre` nombrepersona,p.`apellido`,p.`dni`,p.`usuario`,p.`password`,p.`estado` estadopersona,p.`dni`,p.`id_categoria` "
								+ "from reservas r "
								+ " inner join elementos e on e.id_elemento=r.id_elemento"
								+ " inner join tipo_elementos te on te.`id_tipoelemento` = e.`id_tipoelemento`"
								+ " inner join personas p on p.id_persona=r.id_persona"
								+ " where r.id_persona=? and r.id_elemento=? and r.fecha_registro=?");
						
				stmt.setInt(1, id_persona);
				stmt.setInt(2, id_elemento);
				stmt.setDate(3, fecharegistro);
				rs = stmt.executeQuery();
				
				if (rs!=null && rs.next()) {
					res = new Reserva();
					te = new Tipo_Elemento();
					cat = new Categoria();
					res.setElemento(new Elemento());
					res.setPersona(new Persona());
					res.setFecha_registro(rs.getDate("fecha_registro"));
					res.setFecha_inicio(rs.getDate("fecha_inicio"));
					res.setFecha_fin(rs.getDate("fecha_fin"));
					res.setDetalle(rs.getString("detalle"));
					res.setEstado(rs.getString("estado"));
					
					te.setId_tipoelemento(rs.getInt("id_tipoelemento"));
					te.setCantMaxReservasPend(rs.getInt("cantMaxReservasPend"));
					te.setNombre(rs.getString("nombretipoelemento"));
					
					res.getElemento().setTipo_Elemento(te);
					res.getElemento().setId_elemento(rs.getInt("id_elemento"));
					res.getElemento().setNombre(rs.getString("nombreelemento"));
					res.getElemento().setAutor(rs.getString("autor"));
					res.getElemento().setGenero(rs.getString("genero"));
					res.getElemento().setDescripcion(rs.getString("descripcion"));
					res.getElemento().setStock(rs.getInt("stock"));
					
					cat.setId_Categoria(rs.getInt("id_categoria"));
					res.getPersona().setId_persona(rs.getInt("id_persona"));
					res.getPersona().setNombre(rs.getString("nombrepersona"));
					res.getPersona().setApellido(rs.getString("apellido"));
					res.getPersona().setDni(rs.getString("dni"));
					res.getPersona().setUsuario(rs.getString("usuario"));
					res.getPersona().setPassword(rs.getString("password"));
					res.getPersona().setHabilitado(rs.getBoolean("estadopersona"));
					res.getPersona().setCategoria(cat);
					
				
				}
				
			
			
			} catch (Exception e) {
				
				throw e;
			}
			
				try {
					if (rs != null) {rs.close();}
					if (stmt != null) {stmt.close();}
					FactoryConexion.getInstancia().releaseConn();	
				} catch (SQLException e) {
					throw e;
				}
			
			return res;
			
			
		}
		
	
	public ArrayList<Reserva> getByUsuario(Persona per) throws Exception{
		
		
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		Tipo_Elemento te = null;
		Categoria cat = null;
		Reserva res = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			 
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select r.`id_elemento`,r.`id_persona`,r.`fecha_registro`,r.`fecha_inicio`,r.`fecha_fin`,r.`detalle`,r.`detalle`,r.estado,"
								+ "e.`nombre` nombreelemento,e.`autor`,e.`genero`,e.`descripcion`,e.`stock`,e.`id_tipoelemento`,"
								+ "te.`nombre` nombretipoelemento,te.`cantMaxReservasPend`,"
								+ "p.`nombre` nombrepersona,p.`apellido`,p.`dni`,p.`usuario`,p.`password`,p.`estado` estadopersona,p.`dni`,p.`id_categoria` "
								+ "from reservas r "
								+ " inner join elementos e on e.id_elemento=r.id_elemento"
								+ " inner join tipo_elementos te on te.`id_tipoelemento` = e.`id_tipoelemento`"
								+ " inner join personas p on p.id_persona=r.id_persona"
								+ " where r.id_persona=? order by r.fecha_registro");
					
			stmt.setInt(1, per.getId_persona());
			rs = stmt.executeQuery();
			
			if (rs != null) {
				while (rs.next()) {
				res = new Reserva();
				te = new Tipo_Elemento();
				cat = new Categoria();
				res.setElemento(new Elemento());
				res.setPersona(new Persona());
				res.setFecha_registro(rs.getDate("fecha_registro"));
				res.setFecha_inicio(rs.getDate("fecha_inicio"));
				res.setFecha_fin(rs.getDate("fecha_fin"));
				res.setDetalle(rs.getString("detalle"));
				res.setEstado(rs.getString("estado"));
				
				te.setId_tipoelemento(rs.getInt("id_tipoelemento"));
				te.setCantMaxReservasPend(rs.getInt("cantMaxReservasPend"));
				te.setNombre(rs.getString("nombretipoelemento"));
				
				res.getElemento().setTipo_Elemento(te);
				res.getElemento().setId_elemento(rs.getInt("id_elemento"));
				res.getElemento().setNombre(rs.getString("nombreelemento"));
				res.getElemento().setAutor(rs.getString("autor"));
				res.getElemento().setGenero(rs.getString("genero"));
				res.getElemento().setDescripcion(rs.getString("descripcion"));
				res.getElemento().setStock(rs.getInt("stock"));
				
				cat.setId_Categoria(rs.getInt("id_categoria"));
				res.getPersona().setId_persona(rs.getInt("id_persona"));
				res.getPersona().setNombre(rs.getString("nombrepersona"));
				res.getPersona().setApellido(rs.getString("apellido"));
				res.getPersona().setDni(rs.getString("dni"));
				res.getPersona().setUsuario(rs.getString("usuario"));
				res.getPersona().setPassword(rs.getString("password"));
				res.getPersona().setHabilitado(rs.getBoolean("estadopersona"));
				res.getPersona().setCategoria(cat);
			
				reservas.add(res);
				
			}
			}
			
		
		
		} catch (Exception e) {
			
			throw e;
		}
		
			try {
				if (rs != null) {rs.close();}
				if (stmt != null) {stmt.close();}
				FactoryConexion.getInstancia().releaseConn();	
			} catch (SQLException e) {
				throw e;
			}
		
		return reservas;
		
		
	}
	
	
	
	public ArrayList<Elemento> getElementosSinReserva(Reserva reserva) throws Exception{
		
		ArrayList<Elemento> lista = new ArrayList<Elemento>();
		Tipo_Elemento te = null;
		Reserva res = null;
		Elemento ele = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
			" SELECT DISTINCT e.`id_elemento`,e.`nombre`,e.`autor`,e.`genero`,e.`descripcion`,e.`id_tipoelemento`,e.`stock`, te.`nombre` nombretipoelemento,te.`cantMaxReservasPend` "
		+	" FROM elementos e"
		+	" INNER JOIN `tipo_elementos` te on te.`id_tipoelemento` = e.`id_tipoelemento`"
		+	" LEFT JOIN reservas r on r.`id_elemento` = e.`id_elemento`"
		+	" WHERE e.`id_tipoelemento`=? "
		+ 	" and e.`id_elemento` not IN "
		+ 	" (SELECT ee.`id_elemento`"
		+ 	" FROM reservas r "
		+ 	" INNER JOIN elementos ee on r.`id_elemento` = ee.`id_elemento` "
		+	" INNER JOIN tipo_elementos te on te.`id_tipoelemento` = ee.`id_tipoelemento`"
		+ 	" WHERE (ee.`id_tipoelemento`=? and r.`fecha_inicio` between ? and ? "
		+ 	" or r.`fecha_fin` BETWEEN  ? and ?) and (r.`estado`='Activa' or r.`estado` = 'Sin devolver')) "
		+ 	" and e.id_elemento NOT IN "
		+ 	" (SELECT eee.id_elemento "
		+ 	" FROM elementos eee "
		+ 	" INNER JOIN reservas r on r.id_elemento=eee.id_elemento"
		+ 	" WHERE r.fecha_registro=? and r.`id_persona`=? and (r.`estado`='Terminada' or r.`estado`='Cancelada'))"); 
					
			stmt.setInt(1, reserva.getElemento().getTipo_Elemento().getId_tipoelemento());
			stmt.setInt(2, reserva.getElemento().getTipo_Elemento().getId_tipoelemento());
			stmt.setDate(3, (Date) reserva.getFecha_inicio());
			stmt.setDate(4,(Date) reserva.getFecha_fin());
			stmt.setDate(5, (Date) reserva.getFecha_inicio());
			stmt.setDate(6, (Date) reserva.getFecha_fin());
			stmt.setDate(7,(Date) reserva.getFecha_registro());
			stmt.setInt(8, reserva.getPersona().getId_persona());
			rs = stmt.executeQuery();
			
			if (rs != null) {
				while (rs.next()) {
				ele = new Elemento();
				te = new Tipo_Elemento();
				
				te.setId_tipoelemento(rs.getInt("id_tipoelemento"));
				te.setCantMaxReservasPend(rs.getInt("cantMaxReservasPend"));
				te.setNombre(rs.getString("nombretipoelemento"));
				
				ele.setTipo_Elemento(te);
				ele.setId_elemento(rs.getInt("id_elemento"));
				ele.setNombre(rs.getString("nombre"));
				ele.setAutor(rs.getString("autor"));
				ele.setGenero(rs.getString("genero"));
				ele.setDescripcion(rs.getString("descripcion"));
				ele.setStock(rs.getInt("stock"));
			
				lista.add(ele);
				
			}
			}
			
		
		
		} catch (Exception e) {
			
			throw e;
		}
		
			try {
				if (rs != null) {rs.close();}
				if (stmt != null) {stmt.close();}
				FactoryConexion.getInstancia().releaseConn();	
			} catch (SQLException e) {
				throw e;
			}
		
		return lista;
	}
	
	
	
	
	
		}

