package servlet;

import java.io.IOException;
import java.sql.SQLException;
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
		
		
		
			
			
			try {
			
			String fecha = request.getParameter("fecharegistro");
			data = simple.parse(fecha);
			java.sql.Date sqlDate = new java.sql.Date(data.getTime());
			
			int idpersona = Integer.parseInt(request.getParameter("idpersona"));
			int idelemento = Integer.parseInt(request.getParameter("idelemento"));
			reserva = reservalogic.GetOne(idpersona, idelemento, sqlDate);
			
			if (reserva  == null) {
				request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
				request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			
			}else{
			
			request.setAttribute("reserva", reserva);
			
			}
			}catch (SQLException e) {
				request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
				System.out.println(e.getMessage());
				request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			} catch (AppDataException ade) {
				request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
				System.out.println(ade.getMessage());
				request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			}
			catch (Exception e) {
				request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
				System.out.println(e.getMessage());
				request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			}
			
			
		
		request.getRequestDispatcher("/WEB-INF/frmmodificar.jsp").forward(request, response);

		}
	

}
