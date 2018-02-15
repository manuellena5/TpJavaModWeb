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
import org.apache.logging.log4j.Logger;

import entidades.Persona;
import entidades.Reserva;
import negocio.ReservasLogic;
import util.AppDataException;
import util.Emailer;

/**
 * Servlet implementation class ValidarAltaReserva
 */
@WebServlet({ "/FinalizarAltaReserva", "/finalizaraltareserva.servlet" })
public class FinalizarAltaReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger;
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
		Persona persona =  new Persona();
		
		
			
			
			
			try {
				int idelemento = Integer.parseInt(request.getParameter("idelemento"));
				int idpersona = Integer.parseInt(request.getParameter("idpersona"));
				String detalle = request.getParameter("txtdetalle");
				
				
				
				String fecha = request.getParameter("fecharegistro");
				data = simple.parse(fecha);
				java.sql.Date fecharegistro = new java.sql.Date(data.getTime());
				
				reserva = reservaslogic.GetOne(idpersona, idelemento, fecharegistro);
				
				if (reserva  == null) {
					request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
					request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
					
				}else{
				
				if (detalle.isEmpty()) {
					detalle="Sin detalle";
				}
				
				reserva.setDetalle(detalle);
				
				reservaslogic.update(reserva);
				
				if (((Persona)request.getSession().getAttribute("user")).getCategoria().getDescripcion().equals("Usuario") || ((Persona)request.getSession().getAttribute("user")).getCategoria().getDescripcion().equals("Encargado")) {
					String mail = request.getParameter("txtmail");
					
				if (!mail.isEmpty()) {
				
					Emailer.getInstance().send(mail,"Su reserva se ha registrado correctamente",reservaslogic.getDatosReserva(reserva));
				}}
				
				
				
				/*em.SendMail(mail,reservaslogic.getDatosReserva(reserva));*/
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
		
			if (((Persona)request.getSession().getAttribute("user")).getCategoria().getDescripcion().equals("Usuario") || ((Persona)request.getSession().getAttribute("user")).getCategoria().getDescripcion().equals("Encargado")) {
				
				request.getRequestDispatcher("traerreservasusuario.servlet").forward(request, response);
			}else
				 {
					request.getRequestDispatcher("ListadoReservas.servlet").forward(request, response);
				}
		
		
		
		
	}

}
