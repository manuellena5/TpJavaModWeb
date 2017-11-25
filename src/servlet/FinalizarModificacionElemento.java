package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.ElementosLogic;
import entidades.Elemento;
import util.AppDataException;

/**
 * Servlet implementation class FinalizarModificacionElemento
 */
@WebServlet({ "/FinalizarModificacionElemento", "/FinalizarModificacionElemento.servlet" })
public class FinalizarModificacionElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalizarModificacionElemento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int idelemento = Integer.parseInt(request.getParameter("btneleccion"));
		String nombre = request.getParameter("txtnombre");
		String descripcion = request.getParameter("txtdescripcion");
		String autor = request.getParameter("txtautor");
		String genero = request.getParameter("txtgenero");
		/*int stock = Integer.parseInt(request.getParameter("txtstock"));*/
		
		int stock = 1;
		
		
		ElementosLogic elementoLogic = new ElementosLogic();
		
		Elemento el = new Elemento();
		
		
		try {
		el = elementoLogic.GetOne(idelemento);
		el.setNombre(nombre);
		el.setDescripcion(descripcion);
		el.setAutor(autor);
		el.setGenero(genero);
		el.setStock(stock);
		elementoLogic.update(el);
			
		request.setAttribute("elemento", el);
		} catch (SQLException e) {
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
		
		request.getRequestDispatcher("WEB-INF/modificacionexitosaelemento.jsp").forward(request, response);
	
	}

}
