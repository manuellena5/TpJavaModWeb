package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Persona;
import entidades.Reserva;
import negocio.ElementosLogic;
import negocio.ReservasLogic;
import util.AppDataException;

/**
 * Servlet implementation class TraerReservasUsuario
 */
@WebServlet({ "/TraerReservasUsuario", "/reservasusuario.servlet","/traerreservasusuario.servlet" })
public class TraerReservasUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraerReservasUsuario() {
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
		
		
		Persona usuario = new Persona();
		ReservasLogic reservaslogic = new ReservasLogic();
		ArrayList<Reserva> listadoreservas;
		usuario = (Persona)request.getSession().getAttribute("user");
		
		try {
			
			listadoreservas = new ArrayList<Reserva>();
			
			listadoreservas = reservaslogic.getByUsuario(usuario);
			listadoreservas = reservaslogic.actualizarlistado(listadoreservas);
			
			request.setAttribute("listareservasusuario", listadoreservas);
			
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
		
		request.getRequestDispatcher("WEB-INF/reservasusuario.jsp").forward(request, response);
	}		
		
		
		
		
	

}
