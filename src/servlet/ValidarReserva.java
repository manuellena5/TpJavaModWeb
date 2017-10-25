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
import negocio.ReservasLogic;
import util.AppDataException;

/**
 * Servlet implementation class ValidarReserva
 */
@WebServlet({ "/ValidarReserva", "/validarreserva.servlet" })
public class ValidarReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarReserva() {
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
		
		/*Reserva reserva = new Reserva();
		ReservasLogic reservalogic = new ReservasLogic();
		java.util.Date data = null;
		
		try {
			String fecha = request.getParameter("fechainicio");
			SimpleDateFormat simple= new SimpleDateFormat("dd/MM/yyyy"); 
			
			data = simple.parse(fecha);
		}catch (AppDataException ade) {
				request.setAttribute("Error", ade.getMessage());
			
			
		} catch (Exception e) {
			response.setStatus(502);
		}
		
		request.getRequestDispatcher("WEB-INF/verreservas.jsp").forward(request, response);
		*/
		
		ReservasLogic reservalogic = new ReservasLogic();
		ElementosLogic elementologic = new ElementosLogic();
		Reserva reserva = new Reserva();
		java.util.Date data = null;
		SimpleDateFormat simple= new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			String fecha = request.getParameter("fechainicio");
			data = simple.parse(fecha);
			java.sql.Date sqlDate = new java.sql.Date(data.getTime()); 
			reserva.setFecha_inicio(sqlDate);
			
			
			fecha = request.getParameter("fecharegistro");
			data = simple.parse(fecha);
			sqlDate = new java.sql.Date(data.getTime());
			reserva.setFecha_registro(sqlDate);
			

			
			fecha = request.getParameter("fechafin");
			data = simple.parse(fecha);
			sqlDate = new java.sql.Date(data.getTime());
			reserva.setFecha_fin(sqlDate);
			

			
			String detalle = request.getParameter("detalle");
			reserva.setDetalle(detalle);
			

			
			String estado = "Activa";
			reserva.setEstado(estado);
			

			
			int idelemento = Integer.parseInt(request.getParameter("idelemento"));
			
			Elemento elemento = elementologic.GetOne(idelemento);
			
			
			reserva.setElemento(elemento);
			
			Persona persona = (Persona)request.getSession().getAttribute("user");
			
			
			reserva.setPersona(persona);
			
			reservalogic.add(reserva);
			
			request.setAttribute("reserva", reserva);
			
		} catch (AppDataException ade) {
			request.setAttribute("Error", ade.getMessage());
		}
		catch (Exception e) {
			response.setStatus(502);
		}
		
		request.getRequestDispatcher("WEB-INF/reservaexitosa.jsp").forward(request, response);
	}
		
		
		
		
		
	}


