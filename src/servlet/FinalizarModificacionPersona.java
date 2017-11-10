package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


		int idpersona = Integer.parseInt(request.getParameter("btneleccion"));
		String nombre = request.getParameter("txtnombre");
		String apellido = request.getParameter("txtapellido");
		String dni = request.getParameter("txtdni");
		String usuario = request.getParameter("txtusuario");
		String pass = request.getParameter("txtpass");
		
		PersonaLogic personaLogic = new PersonaLogic();
		
		Persona per = new Persona();
		
		
		try {
		per = personaLogic.GetById(idpersona);
		per.setNombre(nombre);
		per.setApellido(apellido);
		per.setDni(dni);
		per.setUsuario(usuario);
		per.setPassword(pass);


		personaLogic.update(per);
			
		request.setAttribute("persona", per);
		} catch (AppDataException ade) {
			request.setAttribute("Error", ade.getMessage());
		}
		catch (Exception e) {
			response.setStatus(502);
		}
		
		request.getRequestDispatcher("WEB-INF/modificacionexitosapersona.jsp").forward(request, response);
	
	}

}
