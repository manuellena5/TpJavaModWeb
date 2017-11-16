package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Persona;
import negocio.PersonaLogic;

/**
 * Servlet implementation class ModificarMisDatos
 */
@WebServlet({ "/ModificarMisDatos", "/modificarmisdatos.servlet" })
public class ModificarMisDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarMisDatos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idpersona = ((Persona)request.getSession().getAttribute("user")).getId_persona();
		
		
		PersonaLogic personaLogic = new PersonaLogic();
		Persona persona = new Persona();
		try {
			persona = personaLogic.GetById(idpersona);
			
			request.setAttribute("persona", persona);
			
		} catch (Exception e) {
			response.setStatus(502);
		}
		request.getRequestDispatcher("WEB-INF/modificarpersona.jsp").forward(request, response);
	}
	
}

