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
			int idpersona = ((Persona)request.getSession().getAttribute("user")).getId_persona();
			
			request.setAttribute("fechainicio", fechainicio);
			request.setAttribute("fecharegistro", fecharegistro);
			request.setAttribute("fechafin", fechafin);
			
			listaelementos = reservalogic.getElementosSinReserva(fechainicio, fechafin,fecharegistro, idtipoelemento,idpersona);
			
		}catch (SQLException sqle) {
				System.out.println(sqle.getMessage());
			}
			
				
		 catch (AppDataException ade) {
			request.setAttribute("Error", ade.getMessage());
		}
		catch (Exception e) {
			response.setStatus(502);
		}
		
		request.setAttribute("listaElementos",listaelementos);
		
		
		request.getRequestDispatcher("WEB-INF/elegirelementosdisponibles.jsp").forward(request, response);
	}
		
		
		
		
		
	}

