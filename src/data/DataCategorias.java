package data;

import java.sql.*;
import java.util.ArrayList;
import entidades.*;
import util.AppDataException;
import javax.swing.JOptionPane;
import java.security.KeyStore.ProtectionParameter;

public class DataCategorias {
	
	
	public ArrayList<Categoria> getAll() throws Exception{
				
				Statement stmt=null;
				ResultSet rs=null;
				ArrayList<Categoria> categorias = new ArrayList<Categoria>();
				try {
					  stmt = FactoryConexion.getInstancia().getConn().createStatement();
					  rs = stmt.executeQuery("select * from categorias");
					  
				if (rs != null) {
						while (rs.next()) {
								Categoria cat = new Categoria();
								cat.setId_Categoria(rs.getInt("id_categoria"));
								cat.setDescripcion(rs.getString("descripcion"));
								
								categorias.add(cat);
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
				
					return categorias;
					
					
				}
 
	
	public Categoria getByDescripcion(Categoria categorias) throws Exception{
	
			Categoria cat = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				 
				stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
						"select id_categoria, descripcion from categorias where descripcion=?");
						
				stmt.setString(1, categorias.getDescripcion());
				rs = stmt.executeQuery();
				
				if (rs!=null && rs.next()) {
					cat = new Categoria();
					cat.setId_Categoria(rs.getInt("id_categoria"));   
					cat.setDescripcion(rs.getString("descripcion"));
				
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
			
			return cat;
		}
	
	
	public Categoria getById(Categoria categorias) throws Exception{
		
		Categoria cat = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			 
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_categoria, descripcion from categorias where id_categoria=?");
					
			stmt.setInt(1, categorias.getId_Categoria());
			rs = stmt.executeQuery();
			
			if (rs!=null && rs.next()) {
				cat = new Categoria();
				cat.setId_Categoria(rs.getInt("id_categoria")); 
				cat.setDescripcion(rs.getString("descripcion"));
			
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
		
		return cat;
	}
	
	
		
		public void add(Categoria cat) throws Exception{
			PreparedStatement stmt=null;
			ResultSet keyResultSet=null;
			try {
				stmt=FactoryConexion.getInstancia().getConn()
						.prepareStatement(
						"insert into categorias(id_categoria, descripcion) values (?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS
						);
				
				stmt.setString(1, cat.getDescripcion());
				
				stmt.executeUpdate();
				
				keyResultSet=stmt.getGeneratedKeys();
				if(keyResultSet!=null && keyResultSet.next()){
					cat.setId_Categoria(keyResultSet.getInt(1));
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
		
		public void update(Categoria cat) throws Exception{
			PreparedStatement stmt=null;
			
			try {
				stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
						"update categorias set descripcion=? where id_categoria=?");
				
				stmt.setString(1, cat.getDescripcion());
			
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
		
		public void delete(Categoria cat) throws Exception{
			PreparedStatement stmt=null;
			
			try {
				stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
						"delete from categorias where id_categoria=?");
				
				stmt.setInt(1, cat.getId_Categoria());
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
		 
}

