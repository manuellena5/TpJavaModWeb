package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Reserva;
import negocio.ReservasLogic;
import util.AppDataException;

/**
 * Servlet implementation class ListadoReservas
 */
@WebServlet({ "/ListadoReservas.servlet"})
public class ListadoReservas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoReservas() {
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
		ArrayList<Reserva> listadoreservas;		
		
		try {
			
			listadoreservas = new ArrayList<>();
			
			listadoreservas = reservalogic.GetAll();
			
			listadoreservas = reservalogic.actualizarlistado(listadoreservas);
			
			
			
			request.setAttribute("listaReservas", listadoreservas);
			
			
			
		}catch (SQLException e) {
			request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
			System.out.println(e.getMessage());
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		} 
		catch (AppDataException ade) {
			request.setAttribute("Error", ade.getMessage());
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		catch (Exception e) {
			response.setStatus(502);
		}
		
		request.getRequestDispatcher("WEB-INF/verreservas.jsp").forward(request, response);
	}
	
	

}
