package servlet;

import java.io.IOException;
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
import entidades.Tipo_Elemento;
import negocio.ElementosLogic;
import negocio.PersonaLogic;
import negocio.ReservasLogic;
import negocio.Tipo_ElementosLogic;
import util.AppDataException;

/**
 * Servlet implementation class FinalizarModificacionReserva
 */
@WebServlet({ "/FinalizarModificacionReserva", "/FinalizarModificacionReserva.servlet" })
public class FinalizarModificacionReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalizarModificacionReserva() {
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
		ElementosLogic elementologic = new ElementosLogic();
		PersonaLogic personalogic = new PersonaLogic();
		Tipo_ElementosLogic tipoelementoslogic = new Tipo_ElementosLogic();
		
		Reserva reserva = new Reserva();
		Elemento elemento = new Elemento();
		Persona persona = new Persona();
		Tipo_Elemento tipoelemento = new Tipo_Elemento();
		
		java.util.Date data = null;
		SimpleDateFormat simple= new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			
			int idpersona = Integer.parseInt(request.getParameter("idpersona"));
			int idelemento = Integer.parseInt(request.getParameter("idelemento"));
			int idtipoelemento = Integer.parseInt(request.getParameter("idtipoelemento"));
			
			persona = personalogic.GetById(idpersona);
			reserva.setPersona(persona);
			
			tipoelemento = tipoelementoslogic.GetById(idtipoelemento);
			
			elemento = elementologic.GetOne(idelemento);
			elemento.setTipo_Elemento(tipoelemento);
			
			reserva.setElemento(elemento);
			
			
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
			
			if (detalle.isEmpty()) {
				detalle = "Sin detalle";
			}
			
			reserva.setDetalle(detalle);
			
			String estado = request.getParameter("estado");
			reserva.setEstado(estado);
			
			reservalogic.update(reserva);
			
			request.setAttribute("reserva", reserva);
		
		}catch (SQLException e) {
			request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
			System.out.println(e.getMessage());
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		} catch (AppDataException ade) {
			request.setAttribute("Error", ade.getMessage());
			System.out.println(ade.getMessage());
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		catch (Exception e) {
			response.setStatus(502);
		}
		
		request.getRequestDispatcher("/WEB-INF/modificacionexitosa.jsp").forward(request, response);
		
		
		
		
		
		
	}

}
