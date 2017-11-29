package data;

import java.sql.*;
import java.util.ArrayList;
import entidades.*;
import util.AppDataException;
import javax.swing.JOptionPane;
import java.security.KeyStore.ProtectionParameter;

public class DataElementos {
	
	
	public ArrayList<Elemento> getAll() throws Exception{
				
				Statement stmt=null;
				ResultSet rs=null;
				ArrayList<Elemento> elementos = new ArrayList<Elemento>();
				try {
					  stmt = FactoryConexion.getInstancia().getConn().createStatement();
					  rs = stmt.executeQuery("select e.id_elemento,e.nombre,e.stock,e.autor,e.genero,e.descripcion,te.id_tipoelemento,te.cantMaxReservasPend,te.nombre nombreIdTipoElemento from elementos e inner join tipo_elementos te on te.id_tipoelemento = e.id_tipoelemento");
					  
				if (rs != null) {
						while (rs.next()) {
								Elemento el = new Elemento();
								el.setTipo_Elemento(new Tipo_Elemento());
								el.setId_elemento(rs.getInt("id_elemento"));
								el.setNombre(rs.getString("nombre"));
								el.setStock(rs.getInt("stock"));
								el.setAutor(rs.getString("autor"));
								el.setGenero(rs.getString("genero"));
								el.setDescripcion(rs.getString("descripcion"));
								
								el.getTipo_Elemento().setId_tipoelemento(rs.getInt("id_tipoelemento"));
								el.getTipo_Elemento().setCantMaxReservasPend(rs.getInt("cantMaxReservasPend"));
								el.getTipo_Elemento().setNombre((rs.getString("nombreIdTipoElemento")));
								
								
								
								elementos.add(el);
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
				
					return elementos;
					
					
				}
 
	
	public Elemento getByNombre(Elemento elementos) throws Exception{
	
			Elemento el = null;
			Tipo_Elemento te = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				 
				stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
						"select e.id_elemento, e.nombre, e.stock, e.autor, e.genero, e.descripcion, e.`id_tipoelemento`, te.`cantMaxReservasPend`,te.`nombre` nombretipoelemento"
						+ " from elementos e "
						+ "inner join tipo_elementos te on e.id_tipoelemento=te.id_tipoelemento where e.nombre=?");
						
				stmt.setString(1, elementos.getNombre());
				rs = stmt.executeQuery();
				
				if (rs!=null && rs.next()) {
					el = new Elemento();
					te = new Tipo_Elemento();
					
					te.setId_tipoelemento(rs.getInt("id_tipoelemento"));
					te.setNombre(rs.getString("nombretipoelemento"));
					te.setCantMaxReservasPend(rs.getInt("cantMaxReservasPend"));
					
					
					el.setId_elemento(rs.getInt("id_elemento"));   
					el.setNombre(rs.getString("nombre"));
					el.setStock(rs.getInt("stock"));
					el.setAutor(rs.getString("autor"));
					el.setGenero(rs.getString("genero"));
					el.setDescripcion(rs.getString("descripcion"));
					el.setTipo_Elemento(te);
				
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
			
			return el;
		}
	
	public Elemento GetOne(int id) throws Exception{
			Tipo_Elemento te=null;
			Elemento el = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				 
				stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
						"select e.`id_elemento`,e.`nombre`,e.`autor`,e.`genero`,e.`descripcion`,e.`stock`,te.`id_tipoelemento`,te.`nombre` nombretipoelemento,te.`cantMaxReservasPend` from elementos e inner join `tipo_elementos` te on te.`id_tipoelemento` = e.`id_tipoelemento` where e.`id_elemento`=?");
						
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				
				if (rs!=null && rs.next()) {
					el = new Elemento();
					te = new Tipo_Elemento();
					el.setId_elemento(rs.getInt("id_elemento"));   
					el.setNombre(rs.getString("nombre"));
					el.setAutor(rs.getString("autor"));
					el.setGenero(rs.getString("genero"));
					el.setDescripcion(rs.getString("descripcion"));
					el.setStock(rs.getInt("stock"));
					
					te.setId_tipoelemento(rs.getInt("id_tipoelemento"));
					te.setNombre(rs.getString("nombretipoelemento"));
					te.setCantMaxReservasPend(rs.getInt("cantMaxReservasPend"));
					
					el.setTipo_Elemento(te);
					
				
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
			
			return el;
			
			
			
		}
		
	public void add(Elemento el) throws Exception{
			PreparedStatement stmt=null;
			ResultSet keyResultSet=null;
			try {
				stmt=FactoryConexion.getInstancia().getConn()
						.prepareStatement(
						"insert into elementos(nombre, stock, autor, genero, descripcion, id_tipoelemento) values (?,?,?,?,?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS
						);
				
				stmt.setString(1, el.getNombre());
				stmt.setInt(2, el.getStock());
				stmt.setString(3, el.getAutor());
				stmt.setString(4, el.getGenero());
				stmt.setString(5, el.getDescripcion());
				stmt.setInt(6, el.getTipo_Elemento().getId_tipoelemento());
				
				stmt.executeUpdate();
				
				keyResultSet=stmt.getGeneratedKeys();
				if(keyResultSet!=null && keyResultSet.next()){
					el.setId_elemento(keyResultSet.getInt(1));
				}
			} catch (SQLException | AppDataException e) {
				throw e;
			}
			try {
				if(keyResultSet!=null)keyResultSet.close();  /* preguntar que hace esta linea */ 
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw e;
			}
		}
		
	public void update(Elemento el) throws Exception{
			PreparedStatement stmt=null;
			
			try {
				stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
						"update elementos set nombre=?, stock=?, autor=?, genero=?, descripcion=?, id_tipoelemento=? where id_elemento=?");
				
				stmt.setString(1, el.getNombre());
				stmt.setInt(2, el.getStock());
				stmt.setString(3, el.getAutor());
				stmt.setString(4, el.getGenero());
				stmt.setString(5, el.getDescripcion());
				stmt.setInt(6, el.getTipo_Elemento().getId_tipoelemento());
				stmt.setInt(7, el.getId_elemento());
			
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
		
	public void delete(Elemento el) throws Exception{
			PreparedStatement stmt=null;
			
			try {
				stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
						"delete from elementos where id_elemento=?");
				
				stmt.setInt(1, el.getId_elemento());
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

	public ArrayList<Elemento> getByTipoElemento(Tipo_Elemento tipoelemento)throws Exception{
			
			PreparedStatement stmt=null;
			ResultSet rs=null;
			ArrayList<Elemento> elementos = new ArrayList<Elemento>();
			try {
				  stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select e.id_elemento,e.nombre,e.stock,e.autor,e.genero,e.descripcion,te.id_tipoelemento,te.cantMaxReservasPend,te.nombre nombreIdTipoElemento from elementos e inner join tipo_elementos te on te.id_tipoelemento = e.id_tipoelemento where e.id_tipoelemento =? ");
				  

				     stmt.setString(1, String.valueOf(tipoelemento.getId_tipoelemento()));
				     
					rs = stmt.executeQuery();
				  
				  
			if (rs != null) {
					while (rs.next()) {
							Elemento el = new Elemento();
							el.setTipo_Elemento(new Tipo_Elemento());
							el.setId_elemento(rs.getInt("id_elemento"));
							el.setNombre(rs.getString("nombre"));
							el.setStock(rs.getInt("stock"));
							el.setAutor(rs.getString("autor"));
							el.setGenero(rs.getString("genero"));
							el.setDescripcion(rs.getString("descripcion"));
							
							el.getTipo_Elemento().setId_tipoelemento(rs.getInt("id_tipoelemento"));
							el.getTipo_Elemento().setCantMaxReservasPend(rs.getInt("cantMaxReservasPend"));
							el.getTipo_Elemento().setNombre((rs.getString("nombreIdTipoElemento")));
							
							
							
							elementos.add(el);
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
			
				return elementos;
			
		}
}

