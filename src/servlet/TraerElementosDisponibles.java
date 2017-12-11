package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
 * Servlet implementation class ValidarReserva
 */
@WebServlet({ "/TraerElementosDisponibles", "/traerelementosdisponibles.servlet" })
public class TraerElementosDisponibles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraerElementosDisponibles() {
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
		
		ArrayList<Elemento> listaelementos = new ArrayList<Elemento>();
		ReservasLogic reservalogic = new ReservasLogic();
		java.util.Date data = null;
		SimpleDateFormat simple= new SimpleDateFormat("dd/MM/yyyy");
		
		
		
		
		PersonaLogic personaLogic = new PersonaLogic();
		Persona persona = new Persona();
		
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
			
			
			int idtipoelemento = Integer.parseInt(request.getParameter("idtipoelemento"));
			int idpersona = Integer.parseInt(request.getParameter("idpersona"));
			
			
			listaelementos = reservalogic.getElementosSinReserva(fechainicio, fechafin,fecharegistro, idtipoelemento,idpersona);
			persona = personaLogic.GetById(idpersona);
			
			if (persona == null || fechainicio == null || fecharegistro == null || fechafin == null || listaelementos == null) {
				request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
				request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
				
			}else{
			
			request.setAttribute("persona", persona);
			request.setAttribute("fechainicio", fechainicio);
			request.setAttribute("fecharegistro", fecharegistro);
			request.setAttribute("fechafin", fechafin);
			request.setAttribute("listaElementos",listaelementos);
			
			}
		} catch (SQLException e) {
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
		
		
		
		
		request.getRequestDispatcher("WEB-INF/elegirelementosdisponibles.jsp").forward(request, response);
	}
		
		
		
		
		
	}


