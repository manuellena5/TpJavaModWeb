package data;
import java.sql.*;
import util.AppDataException;


public class FactoryConexion {

	
	
	
	private Connection conn;
	private int cantConn=0;
	private String driver = "com.mysql.jdbc.Driver()";
	private String host ="localhost";
	private String user="root";
	private String port = "3306";
	private String password="";
	private String db="biblioteca";

	private static FactoryConexion instancia;

	
	
	private FactoryConexion(){
		
		try {
			/*new com.mysql.jdbc.Driver();*/
			Class.forName(driver);
			
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();  ESTA PARTE TIRA ERROR Y ABRE LA CONSOLA
			
			
		}
		
	}
	
	public static FactoryConexion getInstancia(){
				
		if (FactoryConexion.instancia == null) {
		
			FactoryConexion.instancia= new FactoryConexion();
		}
		return FactoryConexion.instancia;
		
	}
	/* Pregunta si ya hay una conexion, si hay la devuelve, sino la crea
	 * solo tiene que haber una unica instancia de la conexion por eso es singleton */
	
	
	public Connection getConn() throws SQLException,AppDataException{
		
		try {if (conn == null || conn.isClosed()) {
			conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+password);
			
			
		}
			}catch (SQLException e) {
			
				throw new AppDataException(e, "Error al conectar a la base de datos");
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
