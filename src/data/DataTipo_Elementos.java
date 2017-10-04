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
					
					e.printStackTrace();
				}
				
					return tipoElementos;
					
					
				}
 
	
	public Tipo_Elemento getByNombre(Tipo_Elemento tipoElementos) throws Exception{
	
			Tipo_Elemento te = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				 /*al poner el signo de pregunta el driver se da cuenta que en ese lugar va a ir un parametro*/
				stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
						"select id_tipoelemento, nombre, cantMaxReservasPend from tipo_elementos where nombre=?");
						
				stmt.setString(1, tipoElementos.getNombre());
				rs = stmt.executeQuery();
				
				if (rs!=null && rs.next()) {
					te = new Tipo_Elemento();
					te.setId_tipoelemento(rs.getInt("id_tipoelemento"));   /* el dato que va como argumento tiene que ser igual al que esta en la base? */
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
					e.printStackTrace();
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
				if(keyResultSet!=null)keyResultSet.close();  /* preguntar que hace esta linea */ 
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
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
			
				stmt.execute();
				
				
			} catch (SQLException | AppDataException e) {
				
				throw e;
			} 
			try {
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		
		public void delete(Tipo_Elemento te) throws Exception{
			PreparedStatement stmt=null;
			
			try {
				stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
						"delete from tipo_elementos where id_tipoelemento=?");
				
				stmt.setInt(1, te.getId_tipoelemento());
				stmt.execute();
				
				
			} catch (SQLException | AppDataException e) {
				
				throw e;
			} 
			try {
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		 
}

