package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * Servlet implementation class ModificacionReserva
 */
@WebServlet({ "/ModificacionReserva", "/ModificacionReserva.servlet" })
public class ModificacionReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificacionReserva() {
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
		Reserva reserva = new Reserva();
		java.util.Date data = null;
		SimpleDateFormat simple= new SimpleDateFormat("yy-MM-dd");
		
		
		
			int idpersona = Integer.parseInt(request.getParameter("idpersona"));
			int idelemento = Integer.parseInt(request.getParameter("idelemento"));
			
			try {
			
			String fecha = request.getParameter("fecharegistro");
			data = simple.parse(fecha);
			java.sql.Date sqlDate = new java.sql.Date(data.getTime());
			
			
			reserva = reservalogic.GetOne(idpersona, idelemento, sqlDate);
			
			
			request.setAttribute("reserva", reserva);
			}catch (AppDataException ade) {
					request.setAttribute("Error", ade.getMessage());
				}
			catch (Exception e) {
				response.setStatus(502);
			}
			
		
		request.getRequestDispatcher("/WEB-INF/modificarreserva.jsp").forward(request, response);
		
		}
	
}
