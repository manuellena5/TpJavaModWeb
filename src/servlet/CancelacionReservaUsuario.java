package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Reserva;
import negocio.ReservasLogic;
import util.AppDataException;

/**
 * Servlet implementation class ModificacionReservaUsuario
 */
@WebServlet({ "/CancelacionReservaUsuario", "/cancelacionreservausuario.servlet","/CancelacionReservaUsuario.servlet" })
public class CancelacionReservaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelacionReservaUsuario() {
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
			
			reserva.setEstado("Cancelada");
			
			reservalogic.update(reserva);
			
			request.setAttribute("reserva", reserva);
			}catch (AppDataException ade) {
					request.setAttribute("Error", ade.getMessage());
				}
			catch (Exception e) {
				response.setStatus(502);
			}
			
		
		request.getRequestDispatcher("/WEB-INF/cancelarreservaexitosa.jsp").forward(request, response);
		
		}
		
	}

