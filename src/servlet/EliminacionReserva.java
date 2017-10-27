package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Elemento;
import entidades.Persona;
import entidades.Reserva;
import negocio.ElementosLogic;
import negocio.PersonaLogic;
import negocio.ReservasLogic;
import util.AppDataException;

/**
 * Servlet implementation class EliminacionReserva
 */
@WebServlet({ "/EliminacionReserva", "/EliminacionReserva.servlet" })
public class EliminacionReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminacionReserva() {
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


		ReservasLogic reservalogic = new ReservasLogic();
		ElementosLogic elementologic = new ElementosLogic();
		PersonaLogic personalogic = new PersonaLogic();
		Reserva reserva = new Reserva();
		Elemento elemento = new Elemento();
		Persona persona = new Persona();
		java.util.Date data = null;
		SimpleDateFormat simple= new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			
			int idpersona = Integer.parseInt(request.getParameter("idpersona"));
			int idelemento = Integer.parseInt(request.getParameter("idelemento"));
			
			persona = personalogic.GetById(idpersona);
			elemento = elementologic.GetOne(idelemento);
	
			
			String fecha = request.getParameter("txtfecharegistro");
			data = simple.parse(fecha);
			java.sql.Date sqlDate = new java.sql.Date(data.getTime()); 
			
			reserva = reservalogic.GetOne(idpersona, idelemento, sqlDate);
			System.out.println(reserva.getPersona().getId_persona());
			request.setAttribute("reserva", reserva);
		}catch (AppDataException ade) {
				request.setAttribute("Error", ade.getMessage());
			}
		catch (Exception e) {
			response.setStatus(502);
		}
		
		request.getRequestDispatcher("/WEB-INF/eliminarreserva.jsp").forward(request, response);
		

	}

}
