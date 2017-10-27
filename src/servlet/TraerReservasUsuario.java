package servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Persona;
import negocio.ElementosLogic;
import negocio.ReservasLogic;
import util.AppDataException;

/**
 * Servlet implementation class TraerReservasUsuario
 */
@WebServlet({ "/TraerReservasUsuario", "/reservasusuario.servlet" })
public class TraerReservasUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraerReservasUsuario() {
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
		
		
		Persona usuario = new Persona();
		ReservasLogic reservaslogic = new ReservasLogic();
		
		usuario = (Persona)request.getSession().getAttribute("user");
		
		

		try {
			request.setAttribute("listareservasusuario", reservaslogic.getByUsuario(usuario));
			
			
		} catch (AppDataException ade) {
			request.setAttribute("Error", ade.getMessage());
		}
		catch (Exception e) {
			response.setStatus(502);
		}
		
		request.getRequestDispatcher("WEB-INF/reservasusuario.jsp").forward(request, response);
	}		
		
		
		
		
	

}
