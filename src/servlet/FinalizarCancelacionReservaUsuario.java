package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;

import entidades.Reserva;
import negocio.ReservasLogic;
import util.AppDataException;
import util.Emailer;

/**
 * Servlet implementation class FinalizarCancelacionReservaUsuario
 */
@WebServlet({ "/FinalizarCancelacionReservaUsuario.servlet", "/finalizarcancelacionreservausuario" })
public class FinalizarCancelacionReservaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalizarCancelacionReservaUsuario() {
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
				
			int idpersona = Integer.parseInt(request.getParameter("idpersona"));
			int idelemento = Integer.parseInt(request.getParameter("idelemento"));
				
			String fecha = request.getParameter("fecharegistro");
			data = simple.parse(fecha);
			java.sql.Date sqlDate = new java.sql.Date(data.getTime());
			
			String mail = request.getParameter("txtmail");
			
			reserva = reservalogic.GetOne(idpersona, idelemento, sqlDate);
			
			if (reserva == null) {
				
				request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
				request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			
			}else{
			
				reserva.setEstado("Cancelada");
			
			reservalogic.update(reserva);
			
			if (!mail.equals("")) {
				
				Emailer.getInstance().send(mail,"Su reserva se ha cancelado correctamente",reservalogic.getDatosReserva(reserva));
			}
			
			request.setAttribute("reserva", reserva);
			
			}
			
			}catch (SQLException e) {
				request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
				new AppDataException(e, e.getMessage(),Level.ERROR);
				System.out.println(e.getMessage());
				request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			} catch (AppDataException ade) {
				request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
				new AppDataException(ade, ade.getMessage(),Level.ERROR);
				System.out.println(ade.getMessage());
				request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			}
			catch (Exception e) {
				request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
				new AppDataException(e, e.getMessage(),Level.ERROR);
				System.out.println(e.getMessage());
				request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			}
			
		
		request.getRequestDispatcher("/WEB-INF/cancelarreservaexitosa.jsp").forward(request, response);
	}

}
