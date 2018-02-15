package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;

import entidades.Elemento;
import entidades.Persona;
import entidades.Reserva;
import negocio.ElementosLogic;
import negocio.PersonaLogic;
import negocio.ReservasLogic;
import util.AppDataException;

/**
 * Servlet implementation class FinalizarAltaReservaUsuario
 */
@WebServlet({ "/ValidarAltaReservaUsuario", "/validaraltareservausuario.servlet" })
public class ValidarAltaReservaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarAltaReservaUsuario() {
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
		
		
		Elemento elemento = new Elemento();
		java.util.Date data = null;
		SimpleDateFormat simple= new SimpleDateFormat("dd/MM/yyyy");
		ReservasLogic reservalogic = new ReservasLogic();
		Reserva reserva = new Reserva();
		PersonaLogic personaLogic = new PersonaLogic();
		Persona persona = new Persona();
		
		
		ElementosLogic elementoslogic = new ElementosLogic();
		
		
			String detalle = "Sin detalle";
			String estado = "Activa";
			
			
			try {
				int idelemento = Integer.parseInt(request.getParameter("btnenviar"));
				
				String fecha = request.getParameter("fechainicio");					
				data = simple.parse(fecha);						
				java.sql.Date fechainicio = new java.sql.Date(data.getTime());						
				
				fecha = request.getParameter("fecharegistro");
				data = simple.parse(fecha);
				java.sql.Date fecharegistro = new java.sql.Date(data.getTime());
				
				fecha = request.getParameter("fechafin");
				data = simple.parse(fecha);
				java.sql.Date fechafin = new java.sql.Date(data.getTime());
				
				
				int idpersona = Integer.parseInt(request.getParameter("idpersona"));
				persona = personaLogic.GetById(idpersona);
				
				elemento = elementoslogic.GetOne(idelemento);
				
				reserva.setElemento(elemento);
				reserva.setFecha_registro(fecharegistro);
				reserva.setFecha_inicio(fechainicio);
				reserva.setFecha_fin(fechafin);
				reserva.setPersona(persona);
				reserva.setDetalle(detalle);
				reserva.setEstado(estado);
				
				reservalogic.add(reserva);
				
				if (reserva == null || reserva.getElemento() == null || reserva.getPersona() == null) {
					request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
					request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
				}else{
				
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

			request.getRequestDispatcher("WEB-INF/finalizaraltareserva.jsp").forward(request, response);
		
		
	}	
	

}
