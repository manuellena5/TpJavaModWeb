package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.ElementosLogic;
import util.AppDataException;

/**
 * Servlet implementation class TraerElementos
 */
@WebServlet("/TraerElementos.servlet")
public class TraerElementos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraerElementos() {
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
		
		 // Integer.parseInt(request.getParameter("eleccion"));
		
		
		int idtipoelemento = Integer.parseInt(request.getParameter("eleccion"));
		
		
		ElementosLogic elementoslogic = new ElementosLogic();
		try {
			request.setAttribute("listaElementos", elementoslogic.getByTipoElemento(idtipoelemento));
			
			
		} catch (AppDataException ade) {
			request.setAttribute("Error", ade.getMessage());
		}
		catch (Exception e) {
			response.setStatus(502);
		}
		
		request.getRequestDispatcher("WEB-INF/elegirelemento.jsp").forward(request, response);
	}

}
