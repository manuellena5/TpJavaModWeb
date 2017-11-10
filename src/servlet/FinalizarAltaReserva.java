package servlet;

import java.io.IOException;
import java.sql.SQLException;
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
 * Servlet implementation class ValidarAltaReserva
 */
@WebServlet({ "/FinalizarAltaReserva", "/finalizaraltareserva.servlet" })
public class FinalizarAltaReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalizarAltaReserva() {
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
		
		java.util.Date data = null;
		SimpleDateFormat simple= new SimpleDateFormat("dd/MM/yyyy");
		Reserva reserva = new Reserva();
		ReservasLogic reservaslogic = new ReservasLogic();
		
			
			int idelemento = Integer.parseInt(request.getParameter("idelemento"));
			int idpersona = Integer.parseInt(request.getParameter("idpersona"));
			String detalle = request.getParameter("txtdetalle");
			
			try {
				
				String fecha = request.getParameter("fecharegistro");
				data = simple.parse(fecha);
				java.sql.Date fecharegistro = new java.sql.Date(data.getTime());
				
				reserva = reservaslogic.GetOne(idpersona, idelemento, fecharegistro);
				
				if (detalle.isEmpty()) {
					detalle="Sin detalle";
				}
				
				reserva.setDetalle(detalle);
				
				reservaslogic.update(reserva);
			
		}catch (SQLException sql) {
			
			System.out.println(sql.getMessage());
			
		}catch (AppDataException ade) {
			request.setAttribute("Error", ade.getMessage());
		}
		catch (Exception e) {
			response.setStatus(502);
		}
		
		request.getRequestDispatcher("WEB-INF/principal.jsp").forward(request, response);
		
		
		
	}

}
