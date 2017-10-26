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
 * Servlet implementation class EliminacionPersona
 */
@WebServlet({ "/EliminacionPersona", "/EliminacionPersona.servlet" })
public class EliminacionPersona extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminacionPersona() {
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

		int idpersona = Integer.parseInt(request.getParameter("id"));
		
		PersonaLogic personaLogic = new PersonaLogic();
		Persona persona = new Persona();
		try {
			persona = personaLogic.GetById(idpersona);

			request.setAttribute("persona", persona);
			
		} catch (Exception e) {
			response.setStatus(502);
		}
		request.getRequestDispatcher("WEB-INF/eliminarpersona.jsp").forward(request, response);
	}

}
