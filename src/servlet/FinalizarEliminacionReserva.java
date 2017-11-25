package servlet;

import java.io.IOException;
import java.sql.SQLException;
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
 * Servlet implementation class FinalizarEliminacionReserva
 */
@WebServlet({ "/FinalizarEliminacionReserva", "/FinalizarEliminacionReserva.servlet" })
public class FinalizarEliminacionReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalizarEliminacionReserva() {
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
			
			int idpersona = Integer.parseInt(request.getParameter("btneleccion"));
			int idelemento = Integer.parseInt(request.getParameter("txtidelemento"));
			
			persona = personalogic.GetById(idpersona);
			elemento = elementologic.GetOne(idelemento);
			
			
			String fecha = request.getParameter("txtfecharegistro");
			data = simple.parse(fecha);
			java.sql.Date sqlDate = new java.sql.Date(data.getTime()); 
			
			reserva = reservalogic.GetOne(idpersona, idelemento, sqlDate);
			
			reservalogic.delete(reserva);
			
			request.setAttribute("reserva", reserva);
			
		}catch (SQLException e) {
			request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
			System.out.println(e.getMessage());
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		} catch (AppDataException ade) {
			request.setAttribute("Error", ade.getMessage());
			System.out.println(ade.getMessage());
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		catch (Exception e) {
			response.setStatus(502);
		}
		
		request.getRequestDispatcher("/WEB-INF/principal.jsp").forward(request, response);
	}

}
