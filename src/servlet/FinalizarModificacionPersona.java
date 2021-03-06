package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;

import entidades.Persona;
import negocio.PersonaLogic;
import util.AppDataException;

/**
 * Servlet implementation class FinalizarModificacionPersona
 */
@WebServlet({ "/FinalizarModificacionPersona", "/FinalizarModificacionPersona.servlet" })
public class FinalizarModificacionPersona extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalizarModificacionPersona() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		
		
		PersonaLogic personaLogic = new PersonaLogic();
		
		Persona per = new Persona();
		
		
		try {
			int idpersona = Integer.parseInt(request.getParameter("btneleccion"));
			String nombre = request.getParameter("txtnombre");
			String apellido = request.getParameter("txtapellido");
			String dni = request.getParameter("txtdni");
			String usuario = request.getParameter("txtusuario");
			String pass = request.getParameter("txtpass");
			boolean estado = Boolean.valueOf(request.getParameter("estado"));
			
		per = personaLogic.GetById(idpersona);
		
		if (per == null) {
			request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			
		}else{
		
		per.setNombre(nombre);
		per.setApellido(apellido);
		per.setDni(dni);
		per.setUsuario(usuario);
		per.setPassword(pass);
		per.setHabilitado(estado);


		personaLogic.update(per);
			
		request.setAttribute("persona", per);
		}
		}catch (SQLException e) {
			request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
			new AppDataException(e, e.getMessage(),Level.ERROR);
			System.out.println(e.getMessage());
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		} catch (AppDataException ade) {
			request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
			new AppDataException(ade, ade.getMessage(),Level.ERROR);
			System.out.println(ade.getMessage());
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		catch (Exception e) {
			request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
			new AppDataException(e, e.getMessage(),Level.ERROR);
			System.out.println(e.getMessage());
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		
		request.getRequestDispatcher("WEB-INF/modificacionexitosa.jsp").forward(request, response);
	
	}

}
