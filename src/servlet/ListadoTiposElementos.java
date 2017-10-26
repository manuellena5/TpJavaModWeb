package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.Tipo_ElementosLogic;
import util.AppDataException;

/**
 * Servlet implementation class ListadoTiposElementos
 */
@WebServlet({ "/ListadoTiposElementos", "/ListadoTiposElementos.servlet" })
public class ListadoTiposElementos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoTiposElementos() {
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

		Tipo_ElementosLogic tiposElementosLogic = new Tipo_ElementosLogic();
		
		try {

			request.setAttribute("listaTiposElementos", tiposElementosLogic.GetAll());
		} catch (AppDataException ade) {
			request.setAttribute("Error", ade.getMessage());
		}
		catch (Exception e) {
			response.setStatus(502);
		}
		
		request.getRequestDispatcher("WEB-INF/vertiposelementos.jsp").forward(request, response);
	
	}

}
