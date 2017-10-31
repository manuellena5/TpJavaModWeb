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

import entidades.Elemento;
import entidades.Persona;
import entidades.Reserva;
import negocio.ElementosLogic;
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
		
		int idelemento = Integer.parseInt(request.getParameter("btneleccion"));
		Elemento elemento = new Elemento();
		java.util.Date data = null;
		SimpleDateFormat simple= new SimpleDateFormat("dd/MM/yyyy");
		ReservasLogic reservalogic = new ReservasLogic();
		Reserva reserva = new Reserva();
		Persona per = new Persona();
		
		
		ElementosLogic elementoslogic = new ElementosLogic();
		
		
			
			
			
			try {
				
				String fecha = request.getParameter("fechainicio");					
				data = simple.parse(fecha);						
				java.sql.Date fechainicio = new java.sql.Date(data.getTime());						
				
				fecha = request.getParameter("fecharegistro");
				data = simple.parse(fecha);
				java.sql.Date fecharegistro = new java.sql.Date(data.getTime());
				
				fecha = request.getParameter("fechafin");
				data = simple.parse(fecha);
				java.sql.Date fechafin = new java.sql.Date(data.getTime());
				
				
				per = (Persona)request.getSession().getAttribute("user");
				
				elemento = elementoslogic.GetOne(idelemento);
				
				reserva.setElemento(elemento);
				reserva.setFecha_registro(fecharegistro);
				reserva.setFecha_inicio(fechainicio);
				reserva.setFecha_fin(fechafin);
				reserva.setPersona(per);
				
				
				reservalogic.add(reserva);
			} catch (SQLException sqle) {
				System.out.println(sqle.getMessage());
			}catch (AppDataException ade) {
				request.setAttribute("Error", ade.getMessage());
			}
			catch (Exception e) {
				response.setStatus(502);
			}
		
			
			
			request.setAttribute("reserva", reserva);
			
			request.getRequestDispatcher("WEB-INF/finalizaraltareserva.jsp").forward(request, response);
		
		
	}	
	

}
