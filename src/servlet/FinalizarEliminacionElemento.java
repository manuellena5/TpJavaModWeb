package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Elemento;
import negocio.ElementosLogic;
import util.AppDataException;

/**
 * Servlet implementation class FinalizarEliminacionElemento
 */
@WebServlet({ "/FinalizarEliminacionElemento", "/FinalizarEliminacionElemento.servlet" })
public class FinalizarEliminacionElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalizarEliminacionElemento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idelemento = Integer.parseInt(request.getParameter("txtid"));
		
		ElementosLogic elementoLogic = new ElementosLogic();
		
		Elemento el = new Elemento();
		try {
			
			el = elementoLogic.GetOne(idelemento);
			
			request.setAttribute("elemento", el);
			
			elementoLogic.delete(el);
				
			
			
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
			
			request.getRequestDispatcher("WEB-INF/eliminacionexitosa.jsp").forward(request, response);
	}

}
