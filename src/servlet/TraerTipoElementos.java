package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Persona;
import entidades.Reserva;
import entidades.Tipo_Elemento;
import negocio.PersonaLogic;
import negocio.ReservasLogic;
import negocio.Tipo_ElementosLogic;
import util.AppDataException;

/**
 * Servlet implementation class TraerTipoElementos
 */
@WebServlet("/TraerTipoElementos.servlet")
public class TraerTipoElementos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraerTipoElementos() {
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
		
		Tipo_ElementosLogic tipoelementoslogic = new Tipo_ElementosLogic();
		ArrayList<Tipo_Elemento> listadotipoelementos = new ArrayList<>();
		Reserva reserva = new Reserva();
		
		int idpersona;
		
		
		PersonaLogic personaLogic = new PersonaLogic();
		Persona persona = new Persona();
		
		try {
			
			persona = (Persona)request.getSession().getAttribute("user");
			
			if (persona.getCategoria().getDescripcion().equals("Usuario") || persona.getCategoria().getDescripcion().equals("Encargado")) {
				idpersona= persona.getId_persona();
				
			}else{
				idpersona = Integer.parseInt(request.getParameter("idpersona"));
			}
			
			
			
			persona = personaLogic.GetById(idpersona);
			listadotipoelementos =  tipoelementoslogic.GetAll();
			
			if (persona == null || listadotipoelementos == null) {
				
				request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
				request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
				
			}else{
			
				reserva.setPersona(persona);
				
				request.setAttribute("reserva", reserva);
				request.setAttribute("listaTipoElementos",listadotipoelementos);
				
				
			}
		}catch (SQLException e) {
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
		
		request.getRequestDispatcher("WEB-INF/nuevareserva.jsp").forward(request, response);
	}
		
	}


