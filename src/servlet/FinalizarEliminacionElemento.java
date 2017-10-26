package servlet;

import java.io.IOException;
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

		int idelemento = Integer.parseInt(request.getParameter("btneleccion"));
		
		ElementosLogic elementoLogic = new ElementosLogic();
		
		Elemento el = new Elemento();
		try {
			
			el = elementoLogic.GetOne(idelemento);
			elementoLogic.delete(el);
				
			request.setAttribute("elemento", el);
			
			} catch (AppDataException ade) {
				request.setAttribute("Error", ade.getMessage());
			}
			catch (Exception e) {
				response.setStatus(502);
			}
			
			request.getRequestDispatcher("WEB-INF/principal.jsp").forward(request, response);
	}

}
