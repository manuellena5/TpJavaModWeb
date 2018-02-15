package data;
import java.sql.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;

import util.AppDataException;


public class FactoryConexion {

	
	private Logger logger;
	
	/*private Connection conn;
	private int cantConn=0;
	private String driver = "com.mysql.jdbc.Driver";
	private String host ="localhost";
	private String user="root";
	private String port = "3306";
	private String password="";
	private String db="biblioteca";*/
	
	private Connection conn;
	private int cantConn=0;
	private String driver = "com.mysql.jdbc.Driver";
	private String host ="mysql34375-biblioteca.jl.serv.net.mx";
	private String user="root";
	private String password="MFNaoe31869";
	private String db="biblioteca";


	private static FactoryConexion instancia;

	
	
	private FactoryConexion() throws ClassNotFoundException{
		
		try {
			/*new com.mysql.jdbc.Driver();*/
			Class.forName(driver);
			
		} catch (ClassNotFoundException e) {
			
			throw e;
			
			
		}
		
	}
	
	public static FactoryConexion getInstancia() throws ClassNotFoundException{
				
		if (FactoryConexion.instancia == null) {
		
			FactoryConexion.instancia= new FactoryConexion();
		}
		return FactoryConexion.instancia;
		
	}
	/* Pregunta si ya hay una conexion, si hay la devuelve, sino la crea
	 * solo tiene que haber una unica instancia de la conexion por eso es singleton */
	
	
	public Connection getConn() throws SQLException,AppDataException{
		
		try {if (conn == null || conn.isClosed()) {
			conn = DriverManager.getConnection("jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+password);
			
			/*conn = DriverManager.getConnection(host+"?user="+user+"&password="+password);*/
			
		}
			}catch (SQLException e) {
				
				/*throw new AppDataException(e, "Error al conectar a la base de datos");*/
				throw e;
			}
		cantConn++; 	/*contador de cantidades de conexiones abiertas*/
		return conn;
	}
	
	public void releaseConn() throws SQLException{
		try {
			cantConn--;
			if (cantConn==0) {
				conn.close();	
			}
			
		} catch (SQLException e) {
			
			throw e;
		}
		
	}
}
