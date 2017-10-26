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
 * Servlet implementation class EliminacionElemento
 */
@WebServlet({ "/EliminacionElemento", "/EliminacionElemento.servlet" })
public class EliminacionElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminacionElemento() {
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

		int idelemento = Integer.parseInt(request.getParameter("id"));
		
		ElementosLogic elementologic = new ElementosLogic();
		Elemento elemento = new Elemento();
		try {
			elemento = elementologic.GetOne(idelemento);

			request.setAttribute("elemento", elemento);
		} catch (AppDataException ade) {
			request.setAttribute("Error", ade.getMessage());
		}
		catch (Exception e) {
			response.setStatus(502);
		}
		
		request.getRequestDispatcher("WEB-INF/eliminarelemento.jsp").forward(request, response);
	}

}
