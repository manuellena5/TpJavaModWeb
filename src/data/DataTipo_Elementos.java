package data;

import java.sql.*;
import java.util.ArrayList;
import entidades.*;
import util.AppDataException;
import javax.swing.JOptionPane;
import java.security.KeyStore.ProtectionParameter;

public class DataTipo_Elementos {
	
	
	public ArrayList<Tipo_Elemento> getAll() throws Exception{
				
				Statement stmt=null;
				ResultSet rs=null;
				ArrayList<Tipo_Elemento> tipoElementos = new ArrayList<Tipo_Elemento>();
				try {
					  stmt = FactoryConexion.getInstancia().getConn().createStatement();
					  rs = stmt.executeQuery("select * from tipo_elementos");
					  
				if (rs != null) {
						while (rs.next()) {
								Tipo_Elemento te = new Tipo_Elemento();
								te.setId_tipoelemento(rs.getInt("id_tipoelemento"));
								te.setNombre(rs.getString("nombre"));
								te.setCantMaxReservasPend(rs.getInt("cantMaxReservasPend"));
								
								tipoElementos.add(te);
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
				
					return tipoElementos;
					
					
				}
 
	
	public Tipo_Elemento getByNombre(Tipo_Elemento tipoElementos) throws Exception{
	
			Tipo_Elemento te = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				 
				stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
						"select id_tipoelemento, nombre, cantMaxReservasPend from tipo_elementos where nombre=?");
						
				stmt.setString(1, tipoElementos.getNombre());
				rs = stmt.executeQuery();
				
				if (rs!=null && rs.next()) {
					te = new Tipo_Elemento();
					te.setId_tipoelemento(rs.getInt("id_tipoelemento")); 
					te.setNombre(rs.getString("nombre"));
					te.setCantMaxReservasPend(rs.getInt("cantMaxReservasPend"));
				
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
			
			return te;
		}
	
	public Tipo_Elemento getById(Tipo_Elemento tipoElementos) throws Exception{
		
		Tipo_Elemento te = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			 
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_tipoelemento, nombre, cantMaxReservasPend from tipo_elementos where id_tipoelemento=?");
					
			stmt.setInt(1, tipoElementos.getId_tipoelemento());
			rs = stmt.executeQuery();
			
			if (rs!=null && rs.next()) {
				te = new Tipo_Elemento();
				te.setId_tipoelemento(rs.getInt("id_tipoelemento")); 
				te.setNombre(rs.getString("nombre"));
				te.setCantMaxReservasPend(rs.getInt("cantMaxReservasPend"));
			
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
		
		return te;
	}
	
	public void add(Tipo_Elemento te) throws Exception{
			PreparedStatement stmt=null;
			ResultSet keyResultSet=null;
			try {
				stmt=FactoryConexion.getInstancia().getConn()
						.prepareStatement(
						"insert into tipo_elementos(nombre,cantMaxReservasPend) values (?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS
						);
				
				stmt.setString(1, te.getNombre());
				stmt.setInt(2, te.getCantMaxReservasPend());
				
				stmt.executeUpdate();
				keyResultSet=stmt.getGeneratedKeys();
				if(keyResultSet!=null && keyResultSet.next()){
					te.setId_tipoelemento(keyResultSet.getInt(1));
				}
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
		
	public void update(Tipo_Elemento te) throws Exception{
			PreparedStatement stmt=null;
			
			try {
				stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
						"update tipo_elementos set nombre=?, cantMaxReservasPend=? where id_tipoelemento=?");
				
				stmt.setString(1, te.getNombre());
				stmt.setInt(2, te.getCantMaxReservasPend());
				stmt.setInt(3, te.getId_tipoelemento());
			
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
		
	public void delete(Tipo_Elemento te) throws Exception{
			PreparedStatement stmt=null;
			
			try {
				stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
						"delete from `tipo_elementos` where id_tipoelemento = ?");
				
				stmt.setInt(1, te.getId_tipoelemento());
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
		 
	public int getcantidadreservaspendientes(int idpersona,Tipo_Elemento tipoelemento) throws Exception{
		
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int cantidadpendientes = 0;
		
		try {
			 
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"SELECT count(*)Cant_pendientes "
				+	"FROM reservas r "
				+	"INNER JOIN `elementos` e on r.`id_elemento` = e.`id_elemento` "
				+ 	"INNER JOIN `tipo_elementos` t on t.`id_tipoelemento` = e.`id_tipoelemento` "
				+	" WHERE t.`id_tipoelemento`=? and r.`id_persona`=? and r.`estado`='Activa'");
					
			stmt.setInt(1, tipoelemento.getId_tipoelemento());
			stmt.setInt(2, idpersona);
			rs = stmt.executeQuery();
			
			if (rs!=null && rs.next()) {
				
				cantidadpendientes = rs.getInt("Cant_pendientes");
			
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
		
		return cantidadpendientes;
	
	}
	





}

