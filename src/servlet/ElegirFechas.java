package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;

import entidades.Elemento;
import entidades.Persona;
import entidades.Reserva;
import entidades.Tipo_Elemento;
import negocio.ElementosLogic;
import negocio.PersonaLogic;
import negocio.Tipo_ElementosLogic;
import util.AppDataException;

/**
 * Servlet implementation class ElegirFechas
 */
@WebServlet({ "/ElegirFechas", "/elegirfechas.servlet" })
public class ElegirFechas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ElegirFechas() {
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
		
		int idpersona;
		
		
		Tipo_ElementosLogic tipoelementoslogic = new Tipo_ElementosLogic();
		Tipo_Elemento tipoelemento = new Tipo_Elemento();
		Elemento elemento = new Elemento();
		
		PersonaLogic personaLogic = new PersonaLogic();
		Persona persona = new Persona();
		
		Reserva reserva = new Reserva();
		
		try {
			
			int idtipoelemento = Integer.parseInt(request.getParameter("eleccion"));
			
			
				idpersona = Integer.parseInt(request.getParameter("idpersona"));
			
			
			
			tipoelemento = tipoelementoslogic.GetById(idtipoelemento);
			boolean val = tipoelementoslogic.ValidarCantidadReservasPendientes(idpersona, tipoelemento);

			
			persona = personaLogic.GetById(idpersona);
			
			if (persona == null || tipoelemento == null) {
				request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
				request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
				
			}else{
				elemento.setTipo_Elemento(tipoelemento);
				reserva.setElemento(elemento);
				reserva.setPersona(persona);
				
				request.setAttribute("reserva", reserva);
				
				
				if (val) {
					request.setAttribute("tipoelemento", tipoelemento);	
				}else{
					tipoelemento=null;
					request.setAttribute("tipoelemento",tipoelemento);
				}
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
		
		request.getRequestDispatcher("WEB-INF/fechasaltareserva.jsp").forward(request, response);
	}

}
